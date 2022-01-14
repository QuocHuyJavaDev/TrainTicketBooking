import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.rmi.GaModel;
import com.rmi.LichTrinhModel;
import com.rmi.TauModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.Style;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.AbstractDB;

public class SeatsManagementController implements Initializable{
		private Connection conn;
		private PreparedStatement ps;
		private Statement st;
		private ResultSet rs;
		int index = -1;
		@FXML
	    private Button btnEdit;
	 	@FXML
	    private Button btnSearch;
	 	@FXML
	    private Button btnDel;
	 	 @FXML
	     private Button btnAdd;
	     @FXML
	     private ChoiceBox<GaModel> cbGa;
	     @FXML
	     private ChoiceBox<LichTrinhModel> cbNgayDi;
	     @FXML
	     private TextField lbSeats;
	     @FXML
	     private TextField trainName;
	    @FXML
	    private ChoiceBox<GaModel> choice;

	    @FXML
	    private TableView<TauModel> tableTrain;

	    @FXML
	    private TableColumn<TauModel, Integer> tbtId;

	    @FXML
	    private TableColumn<TauModel, String> tbtName;

	    @FXML
	    private TableColumn<TauModel, String> tbtNum;

	    @FXML
	    private TableColumn<TauModel, LichTrinhModel> tbtSche;
	    // Mouse Event
	    
	    @FXML
	    void editMoved(MouseEvent event) {
	    	btnEdit.setTextFill(Color.WHITE);
	    	btnEdit.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
	    }
	    @FXML
	    void editExited(MouseEvent event) {
	    	btnEdit.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
	    			+ "-fx-border-width: 2;");
	    }
	    
	    @FXML
	    void searchMoved(MouseEvent event) {
	    	btnSearch.setTextFill(Color.WHITE);
	    	btnSearch.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
	    }
	    @FXML
	    void searchExited(MouseEvent event) {
	    	btnSearch.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
	    			+ "-fx-border-width: 2;");
	    }
	    
	    @FXML
	    void delMoved(MouseEvent event) {
	    	btnDel.setTextFill(Color.WHITE);
	    	btnDel.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
	    }
	    @FXML
	    void delExited(MouseEvent event) {
	    	btnDel.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
	    			+ "-fx-border-width: 2;");
	    }
	    
	    @FXML
	    void addMoved(MouseEvent event) {
	    	btnAdd.setTextFill(Color.WHITE);
	    	btnAdd.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
	    }
	    @FXML
	    void addExited(MouseEvent event) {
	    	btnAdd.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
	    			+ "-fx-border-width: 2;");
	    }
	    //
	    public List<GaModel> findAllGa() throws RemoteException {
	        List<GaModel> gas = new ArrayList<>();
	        conn = AbstractDB.getConnection();

	        String sql = "select * from Ga;";
	        try {
	            ps =conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                GaModel ga = new GaModel(rs.getInt("gid"),rs.getString("tenGa"));
	                gas.add(ga);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null && st != null && conn != null) {
	                try {
	                    rs.close();
	                    st.close();
	                    conn.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
	        return gas;
	    }
	   
	     
	    public List<LichTrinhModel> findLichTrinh() throws RemoteException {
	        List<LichTrinhModel> gas = new ArrayList<>();
	        conn = AbstractDB.getConnection();

	        String sql = "select * from LichTrinh;";
	        try {
	            ps =conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                LichTrinhModel ga = new LichTrinhModel(rs.getInt("lid"),rs.getString("ngayDi"), null);
	                gas.add(ga);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null && st != null && conn != null) {
	                try {
	                    rs.close();
	                    st.close();
	                    conn.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
	        return gas;
	    }
	    //
	    public List<TauModel> findTauByGa(int i) throws RemoteException {
	        List<TauModel> taus = new ArrayList<>();
	        conn = AbstractDB.getConnection();

	        String sql = "select * from Tau inner join LichTrinh where lichTrinh=lid and id_ga="+i+"";
	        try {
	            ps =conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                TauModel tau = new TauModel(rs.getInt("taiid"),
	                        rs.getString("tenTau"),
	                        rs.getInt("soToa"),
	                        new LichTrinhModel(rs.getInt("lid"),
	                                rs.getString("ngayDi"),
	                                rs.getString("ngayDen")),rs.getInt("id_ga"));
	                taus.add(tau);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null && st != null && conn != null) {
	                try {
	                    rs.close();
	                    st.close();
	                    conn.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	        }
	        return taus;
	    }
	    public List<TauModel> findAll() throws RemoteException {
	        List<TauModel> taus = new ArrayList<>();
	        conn = AbstractDB.getConnection();

	        String sql = "select * from Tau inner join LichTrinh where lichTrinh = lid ;";
	        try {
	            ps =conn.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                TauModel tau = new TauModel(rs.getInt("taiid"),
	                        rs.getString("tenTau"),
	                        rs.getInt("soToa"),
	                        new LichTrinhModel(rs.getInt("lid"),
	                                rs.getString("ngayDi"),
	                                rs.getString("ngayDen")), rs.getInt("id_ga"));
	                taus.add(tau);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (rs != null && st != null && conn != null) {
	                try {
	                    rs.close();
	                    st.close();
	                    conn.close();
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }

	        }

	        return taus;
	    }
	    public void loadTrainStation(){

	        ObservableList<GaModel> listMenu = FXCollections.observableArrayList();
	        try {
	            List<GaModel> list = findAllGa();
	            for (GaModel item:list){
	                listMenu.add(item);
	            }
	            choice.setItems(listMenu);
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }

	    }
	    public void loadTrainStation1(){

	        ObservableList<GaModel> listMenuu = FXCollections.observableArrayList();
	        try {
	            List<GaModel> list = findAllGa();
	            for (GaModel item:list){
	                listMenuu.add(item);
	            }
	            cbGa.setItems(listMenuu);
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }

	    }
	    
	    public void loadLichTrinh(){

	        ObservableList<LichTrinhModel> listMenuu = FXCollections.observableArrayList();
	        try {
	            List<LichTrinhModel> list = findLichTrinh();
	            for (LichTrinhModel item:list){
	                listMenuu.add(item);
	            }
	            cbNgayDi.setItems(listMenuu);

	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }

	    }

	    public void load(int id){
	        ObservableList<TauModel> listMenu = FXCollections.observableArrayList();
	        try {
	            List<TauModel> tau = findTauByGa(id);
	            for (TauModel item:tau){
	                listMenu.add(item);
	            }
	            tableTrain.setItems(listMenu);
	        } catch (RemoteException e) {
	            e.printStackTrace();
	        }

	    }
	    @FXML
	    void searchTrain(ActionEvent event) {
	    	 int choi = choice.getValue().getId();
		     tbtId.setCellValueFactory(new PropertyValueFactory<>("id"));
		     tbtName.setCellValueFactory(new PropertyValueFactory<>("tenTau"));
	         tbtNum.setCellValueFactory(new PropertyValueFactory<>("soToa"));
	         tbtSche.setCellValueFactory(new PropertyValueFactory<>("lichTrinh"));
   	         load(choi);
	    }
	    
	    public void addTrain() {
	    	conn =AbstractDB.getConnection();
	    	String sql = "insert into Tau (tenTau, soToa, lichTrinh, id_ga)values(?,?,?,?)";
	    	try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, trainName.getText());
				ps.setInt(2, Integer.parseInt(lbSeats.getText()));
				ps.setInt(3,cbNgayDi.getValue().getId());
				ps.setInt(4,cbGa.getValue().getId());
				ps.execute();
				searchTrain(null);
				JOptionPane.showMessageDialog(null, "Success");
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Error");
			}
	    }
	    @FXML
	    void getSelected(MouseEvent event) {
	    	index = tableTrain.getSelectionModel().getSelectedIndex();
	    	if (index <= -1) {
				return;
			}
	    	trainName.setText(tbtName.getCellData(index).toString());
	    	lbSeats.setText(String.valueOf(tbtNum.getCellData(index)));
	    	
	    }
	    public void editTrain() {
	    	try {
				conn = AbstractDB.getConnection();
				int value0 = Integer.parseInt(tbtId.getCellData(index).toString());
				String value1 = trainName.getText();
				int value2 =  Integer.parseInt(lbSeats.getText());
				int value3 = cbNgayDi.getValue().getId();
				int value4 = cbGa.getValue().getId();
				String sql = "update Tau set tenTau = '"+value1+"', soToa='"+value2+"', lichTrinh='"+value3+"',id_ga='"+value4+"'"
						+ " where taiid='"+value0+"'";
				ps = conn.prepareStatement(sql);
				ps.execute();
				searchTrain(null);
				JOptionPane.showMessageDialog(null, "Success");
				} catch (Exception e) {
				// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Error");
			}
	    }
	    //
	    public void deleteTrain(ActionEvent event) throws IOException {
	    	conn = AbstractDB.getConnection();
	    	String sql1 = "delete from Tau where taiid = ?";
	 
	    	try {
				ps = conn.prepareStatement(sql1);
				ps.setString(1, tbtId.getCellData(index).toString());
				ps.execute();
				searchTrain(null);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadTrainStation();
		loadTrainStation1();
		loadLichTrinh();
		
	}

}
