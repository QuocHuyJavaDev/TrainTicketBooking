package sample;

import com.rmi.GaModel;
import com.rmi.LichTrinhModel;
import com.rmi.RMI;
import com.rmi.TauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

public class TrainStationController implements Initializable {
    RMI rmi;
    Registry req;
    @FXML
    private ChoiceBox<GaModel> choice;
    @FXML
    private Button btnsearch;
    @FXML
    private TableView<TauModel> tabletrainstation;
    @FXML
    private TableColumn<TauModel,Integer> clid;
    @FXML
    private TableColumn<TauModel,String> clname;
    @FXML
    private TableColumn<TauModel,String> clnumber;
    @FXML
    private TableColumn<TauModel, LichTrinhModel> clschedule;
    //
    @FXML
    void searchMoved(MouseEvent event) {
    	btnsearch.setTextFill(Color.BLACK);
    	btnsearch.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void searchExited(MouseEvent event) {
    	btnsearch.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            req = LocateRegistry.getRegistry(2000);
            rmi= (RMI) req.lookup("quanlyvetau");
            loadTrainStation();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }
    public void loadTrainStation(){

        ObservableList<GaModel> listMenu = FXCollections.observableArrayList();
        try {
            List<GaModel> list = rmi.findAllGa();
            for (GaModel item:list){
                listMenu.add(item);
            }
            choice.setItems(listMenu);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void load(int id){
        ObservableList<TauModel> listMenu = FXCollections.observableArrayList();
        try {
            List<TauModel> tau = rmi.findTauByGa(id);
            for (TauModel item:tau){
                listMenu.add(item);
            }
            tabletrainstation.setItems(listMenu);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public void searchTrainStation(ActionEvent event){
    	try {
    		 int choi = choice.getValue().getId();
    	        clid.setCellValueFactory(new PropertyValueFactory<>("id"));
    	        clname.setCellValueFactory(new PropertyValueFactory<>("tenTau"));
    	        clnumber.setCellValueFactory(new PropertyValueFactory<>("soToa"));
    	        clschedule.setCellValueFactory(new PropertyValueFactory<>("lichTrinh"));
    	        load(choi);
		} catch (Exception e) {
			// TODO: handle exception
		}
       
    }
   
}
