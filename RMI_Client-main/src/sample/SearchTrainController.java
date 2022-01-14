package sample;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.rmi.BienLaiModel;
import com.rmi.RMI;
import com.rmi.TauModel;
import com.rmi.ToaModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SearchTrainController implements Initializable{
	RMI rmi;
    Registry req;
    int index = -1;
    @FXML
    private Button btnsearchTic;
    @FXML
    private Button btnRecall;

    @FXML
    private TableColumn<BienLaiModel, String> colAddr;

    @FXML
    private TableColumn<BienLaiModel, String> colDepartDay;

    @FXML
    private TableColumn<BienLaiModel, Integer> colId;

    @FXML
    private TableColumn<BienLaiModel, String> colName;

    @FXML
    private TableColumn<BienLaiModel, String> colPhNum;

    @FXML
    private TableColumn<BienLaiModel, Long> colPrice;

    @FXML
    private TableColumn<BienLaiModel, Integer> colSeat;

    @FXML
    private TableColumn<BienLaiModel, Byte> colSex;

    @FXML
    private TableColumn<BienLaiModel, String> colStat;

    @FXML
    private TableColumn<BienLaiModel, String> colTicCode;

    @FXML
    private TableColumn<BienLaiModel, String> colTrain;

    @FXML
    private TextField lbhoneNumm;

    @FXML
    private TableView<BienLaiModel> tableSearchTic;
    //
    @FXML
    void searchTrainMoved(MouseEvent event) {
    	btnsearchTic.setTextFill(Color.BLACK);
    	btnsearchTic.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void searchTrainExited(MouseEvent event) {
    	btnsearchTic.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    @FXML
    void recallMoved(MouseEvent event) {
    	btnRecall.setTextFill(Color.BLACK);
    	btnRecall.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void recallExited(MouseEvent event) {
    	btnRecall.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    //
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
            req = LocateRegistry.getRegistry(2000);
            rmi= (RMI) req.lookup("quanlyvetau");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
	}
	public void load(String sdt){
        ObservableList<BienLaiModel> listMenu = FXCollections.observableArrayList();
        try {
            List<BienLaiModel> tau = rmi.findBienLai(sdt);
            for (BienLaiModel item:tau){
                listMenu.add(item);
            }
            tableSearchTic.setItems(listMenu);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
	public void showTicket(ActionEvent event){
    	try {
    		 	String sdt = lbhoneNumm.getText().toString();
    		 	colId.setCellValueFactory(new PropertyValueFactory<>("id"));;
    			colName.setCellValueFactory(new PropertyValueFactory<>("hoTenKH"));;
    			colSex.setCellValueFactory(new PropertyValueFactory<>("gioiTinhKH"));;
    			colAddr.setCellValueFactory(new PropertyValueFactory<>("diaChi"));;
    			colPhNum.setCellValueFactory(new PropertyValueFactory<>("sdtKH"));;
    			colTrain.setCellValueFactory(new PropertyValueFactory<>("tenTauKH"));;
    			colStat.setCellValueFactory(new PropertyValueFactory<>("ga"));;
    			colDepartDay.setCellValueFactory(new PropertyValueFactory<>("ngayDi"));;
    			colSeat.setCellValueFactory(new PropertyValueFactory<>("soGhe"));;
    			colPrice.setCellValueFactory(new PropertyValueFactory<>("giaVe"));;
    			colTicCode.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));;
    	        load(sdt);
		} catch (Exception e) {
			// TODO: handle exception
		}
       
    }
	public int convertNametoId(String name){
        try {
            List<TauModel> list = rmi.findAll();
            for (TauModel item:list){
                if (name.equals(item.getTenTau())){
                    return item.getId();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int convertToatoId(int seat, int train){
        try {
            List<ToaModel> list = rmi.findAllToa();
            for (ToaModel item:list){
                if (seat == item.getSoGhe() && train == item.getIdtau()){
                    return item.getId();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }
	@FXML
    void ReCall(ActionEvent event) {
		index = tableSearchTic.getSelectionModel().getSelectedIndex();
    	if (index <= -1) {
			return ;
		} 
		try {
			int i = colId.getCellData(index);
			int a = convertNametoId(colTrain.getCellData(index));
	    	int b = colSeat.getCellData(index);
	    	int j = convertToatoId(b, a);
			rmi.deleteReceip(i, j);
			showTicket(null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
    }

}
