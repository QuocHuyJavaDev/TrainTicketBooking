import com.rmi.*;
import com.rmi.TauModel;
import com.sun.javafx.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import utils.AbstractDB;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Server extends UnicastRemoteObject implements RMI, Initializable{
	int index = -1;
    private Connection conn;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnRef;
    @FXML
    private Button btnDelete;
    @FXML
    private Label  lbServer;
    @FXML
    private TextField filterFld;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ab;
    
    @FXML
    private Button btnSeatMana;
    @FXML
    private Button btnHom;
    @FXML
    private TableView<BienLaiModel> tableReceipt;
    @FXML
    private TableColumn<BienLaiModel,Integer> blids;
    @FXML
    private TableColumn<BienLaiModel,String> name;
    @FXML
    private TableColumn<BienLaiModel, Byte> sex;
    @FXML
    private TableColumn<BienLaiModel,String> addr;
    @FXML
    private TableColumn<BienLaiModel,String> phoneNum;
    @FXML
    private TableColumn<BienLaiModel,String> train;
    @FXML
    private TableColumn<BienLaiModel,String> station;
    @FXML
    private TableColumn<BienLaiModel,String> departDay;
    @FXML
    private TableColumn<BienLaiModel,Integer> seats;
    @FXML
    private TableColumn<BienLaiModel,Long> price;
    @FXML
    private TableColumn<BienLaiModel,String> code;
    
    //
    ObservableList<BienLaiModel> dataList;
    //
    @FXML
    void refreshMoved(MouseEvent event) {
    	btnRef.setTextFill(Color.WHITE);
    	btnRef.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
    }
    @FXML
    void refreshExited(MouseEvent event) {
    	btnRef.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
    			+ "-fx-border-width: 2;");
    }
    @FXML
    void delMoved(MouseEvent event) {
    	btnDelete.setTextFill(Color.WHITE);
    	btnDelete.setStyle("-fx-background-color:  #008B8B;-fx-border-color:   #008B8B;-fx-border-width: 2;");
    }
    @FXML
    void delExited(MouseEvent event) {
    	btnDelete.setStyle("-fx-text-fill: #008B8B;-fx-background-color:   #FFFFFF;-fx-border-color:   #008B8B; "
    			+ "-fx-border-width: 2;");
    }
    @FXML
    void strMoved(MouseEvent event) {
    	btnStart.setTextFill(Color.BLACK);
    	btnStart.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void strExited(MouseEvent event) {
    	btnStart.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    @FXML
    void Moust(MouseEvent event) {
    	btnSeatMana.setTextFill(Color.BLACK);
    	btnSeatMana.setStyle("-fx-background-color:  #008080;"); 
    }
    @FXML
    void Mouse(MouseEvent event) {
    	btnSeatMana.setTextFill(Color.WHITE);
    	btnSeatMana.setStyle("-fx-background-color:  #008B8B;");
    }
    @FXML
    void MoustHome(MouseEvent event) {
    	btnHom.setTextFill(Color.BLACK);
    	btnHom.setStyle("-fx-background-color:  #008080;"); 
    	
    }
    @FXML
    void MouseHome(MouseEvent event) {
    	btnHom.setTextFill(Color.WHITE);
    	btnHom.setStyle("-fx-background-color:  #008B8B;");
    }
    //
   
    @FXML
    private void serverHome(MouseEvent event) {
    	bp.setCenter(ab);
    }
    @FXML
    private void SeatsManagement(MouseEvent event) throws IOException {
    	loadPage("SeatsManagement");
    	
    }
    @FXML
    private void intro(MouseEvent event) {
    	
    }
    private void loadPage(String page) throws IOException {
    	Parent root = null;
		root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		bp.setCenter(root);
		
    }
    public List<BienLaiModel> loadBienLai() throws RemoteException {
        List<BienLaiModel> taus = new ArrayList<>();
        conn = AbstractDB.getConnection();

        String sql = "select * from BienLai";
        try {
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BienLaiModel toa = new BienLaiModel(rs.getInt("blid"), rs.getString("hoTenKH"), rs.getByte("gioiTinh"), rs.getString("diaChi")
						, rs.getString("sdtKH"), rs.getString("tenTauKH"), rs.getString("Ga"), rs.getString("ngayDi")
						, rs.getInt("soGhe"), rs.getLong("giaVe"), rs.getString("maPhieu"));
                taus.add(toa);
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
    @FXML
    public void load(){ 
    	blids.setCellValueFactory(new PropertyValueFactory<>("id"));;
		name.setCellValueFactory(new PropertyValueFactory<>("hoTenKH"));;
		sex.setCellValueFactory(new PropertyValueFactory<>("gioiTinhKH"));;
		addr.setCellValueFactory(new PropertyValueFactory<>("diaChi"));;
		phoneNum.setCellValueFactory(new PropertyValueFactory<>("sdtKH"));;
		train.setCellValueFactory(new PropertyValueFactory<>("tenTauKH"));;
		station.setCellValueFactory(new PropertyValueFactory<>("ga"));;
		departDay.setCellValueFactory(new PropertyValueFactory<>("ngayDi"));;
		seats.setCellValueFactory(new PropertyValueFactory<>("soGhe"));;
		price.setCellValueFactory(new PropertyValueFactory<>("giaVe"));;
		code.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));;
    	  ObservableList<BienLaiModel> oblist = FXCollections.observableArrayList();
    	  try {
              List<BienLaiModel> tau = loadBienLai();
              for (BienLaiModel item:tau){
            	  oblist.add(item);
              }
              tableReceipt.setItems(oblist);
          } catch (RemoteException e) {
              e.printStackTrace();
          }
    	  searchReceipt();
    }
    @FXML
    void searchReceipt() {
    	blids.setCellValueFactory(new PropertyValueFactory<>("id"));;
		name.setCellValueFactory(new PropertyValueFactory<>("hoTenKH"));;
		sex.setCellValueFactory(new PropertyValueFactory<>("gioiTinhKH"));;
		addr.setCellValueFactory(new PropertyValueFactory<>("diaChi"));;
		phoneNum.setCellValueFactory(new PropertyValueFactory<>("sdtKH"));;
		train.setCellValueFactory(new PropertyValueFactory<>("tenTauKH"));;
		station.setCellValueFactory(new PropertyValueFactory<>("ga"));;
		departDay.setCellValueFactory(new PropertyValueFactory<>("ngayDi"));;
		seats.setCellValueFactory(new PropertyValueFactory<>("soGhe"));;
		price.setCellValueFactory(new PropertyValueFactory<>("giaVe"));;
		code.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));;		
		dataList = AbstractDB.getData();
		tableReceipt.setItems(dataList);
		FilteredList<BienLaiModel> filteredData = new FilteredList<>(dataList, b -> true);
		filterFld.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (Integer.toString(person.getId()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getHoTenKH().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (Byte.toString(person.getGioiTinhKH()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getDiaChi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getSdtKH().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getTenTauKH().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (person.getGa().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if (person.getNgayDi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if (Integer.toString(person.getSoGhe()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if (Long.toString(person.getGiaVe()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if (person.getMaPhieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else return false;
			});
		});
		SortedList<BienLaiModel> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableReceipt.comparatorProperty());
		tableReceipt.setItems(sortedData);
    }
    public void deleteReceipt(ActionEvent event) throws IOException {
    	index = tableReceipt.getSelectionModel().getSelectedIndex();
    	if (index <= -1) {
			return;
		} 
    	conn = AbstractDB.getConnection();
    	String sql1 = "delete from BienLai where blid = ?";
 
    	try {
			ps = conn.prepareStatement(sql1);
			ps.setString(1, blids.getCellData(index).toString());
			ps.execute();
		    deleteToa();
			load();
			searchReceipt();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public int convertNametoId(String name){
        try {
            List<TauModel> list = findAll();
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
            List<ToaModel> list = findAllToa();
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
    public void deleteToa() {
    	index = tableReceipt.getSelectionModel().getSelectedIndex();
    	if (index <= -1) {
			return;
		} 
    	int a = convertNametoId(train.getCellData(index));
    	int b = seats.getCellData(index);
    	int c = convertToatoId(b, a);
    	conn = AbstractDB.getConnection();
    	String sql1 = "delete from Toa where toaid = ?";
    	try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, c);
			ps.execute();
			load();
			searchReceipt();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    @Override
    public void deleteReceip(int i, int j) throws RemoteException {
    	conn = AbstractDB.getConnection();
    	String sql1 = "delete from BienLai where blid = ?";
 
    	try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, i);
			ps.execute();
		    deleteToa2(j);
			load();
			searchReceipt();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void deleteToa2(int c) {
    	conn = AbstractDB.getConnection();
    	String sql1 = "delete from Toa where toaid = ?";
    	try {
			ps = conn.prepareStatement(sql1);
			ps.setInt(1, c);
			ps.execute();
			load();
			searchReceipt();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void StartServer(ActionEvent event) throws IOException{
    	try{
    		
            Registry req = LocateRegistry.createRegistry(2000);
            req.rebind("quanlyvetau",new Server());
            System.out.println("server running.....");
            lbServer.setText("Server running");
            load();
    		searchReceipt();
          
        }catch (Exception e){
            e.getMessage();
        }
    	
    }
    public Server() throws RemoteException{
        super();
      
    }
    
    @Override
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

    @Override
    public List<ToaModel> findAllToa() throws RemoteException {
        List<ToaModel> toas = new ArrayList<>();
        conn = AbstractDB.getConnection();

        String sql = "select * from Toa inner join Tau where taiid = id_tau";
        try {
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ToaModel toa = new ToaModel(rs.getInt("toaid"),
                        rs.getInt("soGhe"),
                        rs.getLong("giaVe"),
                        rs.getInt("id_tau"));
                toas.add(toa);
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

        return toas;
    }
   

    @Override
    public int insertToa(ToaModel toa) throws RemoteException {
        int success = 1;
        conn = AbstractDB.getConnection();

        String sql = "insert into Toa(soGhe, giaVe, id_tau)" +
                " VALUES ('"+toa.getSoGhe()+"','"+toa.getGiaVe()+"','"+toa.getIdtau()+"')";
        try {
            ps = conn.prepareStatement(sql);
            success = ps.executeUpdate();

        } catch (SQLException e) {
            success = 2;
            e.printStackTrace();
        } finally {
            if (rs != null && st != null && conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return success;
    }

    @Override
    public int insertPhieu(PhieuDatVeModel phieuDatVeModel) throws RemoteException {
        conn = AbstractDB.getConnection();
        ResultSet resultSet = null;
        int id =0;

        String sql = "insert into PhieuDatVe(name, id_toa) VALUES ('"+phieuDatVeModel.getName()+"','"+
                phieuDatVeModel.getIdToa()+"')";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            if (rs != null && st != null && conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return id;
    }

    @Override
    public int insertKhachHang(KhachHangModel khachHangModel) throws RemoteException {
        conn = AbstractDB.getConnection();
        ResultSet resultSet = null;
        int id =0;

        String sql = "insert into KhachHang(hoTen, gioiTinh, diaChi, sdt, id_phieu)" +
                " VALUES ('"+khachHangModel.getHoTen()+"','"
                +khachHangModel.isGioiTinh()+"', '"+khachHangModel.getDiaChi()+"', '"+khachHangModel.getSdt()
                +"','"+khachHangModel.getMaPhieuint()+"')";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null && st != null && conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return id;
    }
    
    @Override
    public int insertBienLai(BienLaiModel bienLaiModel) throws RemoteException {
        conn = AbstractDB.getConnection();
        ResultSet resultSet = null;
        int id =0;

        String sql = "insert into BienLai(hoTenKH, gioiTinh, diaChi, sdtKH, tenTauKH, Ga, ngayDi, soGhe, giaVe, maPhieu)"
        		+ " VALUES ('"+bienLaiModel.getHoTenKH()+"','"+bienLaiModel.getGioiTinhKH()+"', '"+bienLaiModel.getDiaChi()+"', '"+bienLaiModel.getSdtKH()+"','"+bienLaiModel.getTenTauKH()+"','"+bienLaiModel.getGa()+"','"+bienLaiModel.getNgayDi()+"', '"+bienLaiModel.getSoGhe()+"', '"+bienLaiModel.getGiaVe()+"', '"+bienLaiModel.getMaPhieu()+"')";
        try {
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
      
            resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            if (rs != null && st != null && conn != null) {
                try {
                    rs.close();
                    ps.close();
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return id;
        
    }
    @Override
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

    @Override
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
    @Override
    public List<GaModel> findGaByTau(int i) throws RemoteException {
        List<GaModel> gaas = new ArrayList<>();
        conn = AbstractDB.getConnection();

        String sql = "select * from Ga where gid ="+i+";";
        try {
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                GaModel ga = new GaModel(rs.getInt("gid"),
                        rs.getString("tenGa"));
                gaas.add(ga);
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
        return gaas;
    }
    @Override
    public List<BienLaiModel> findBienLai(String i) throws RemoteException {
        List<BienLaiModel> gaas = new ArrayList<>();
        conn = AbstractDB.getConnection();

        String sql = "select * from BienLai where sdtKH ='"+i+"';";
        try {
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                BienLaiModel ga = new BienLaiModel(rs.getInt("blid"), rs.getString("hoTenKH"), rs.getByte("gioiTinh"), rs.getString("diaChi")
						, rs.getString("sdtKH"), rs.getString("tenTauKH"), rs.getString("Ga"), rs.getString("ngayDi")
						, rs.getInt("soGhe"), rs.getLong("giaVe"), rs.getString("maPhieu"));
                gaas.add(ga);
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
        return gaas;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}
}
