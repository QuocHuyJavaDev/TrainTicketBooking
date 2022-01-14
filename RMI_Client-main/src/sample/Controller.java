package sample;

import com.rmi.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    Registry req;
    RMI rmi;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private GridPane grid;
    @FXML
    private TextField txtseats;
    @FXML
    private TextField txtprice;
    @FXML
    private TextField txtstation;
    @FXML
    private TextField txtname;
    @FXML
    private Button btn;
    @FXML
    private Button btnSearchTic;
    @FXML
    private ComboBox<SexModel> sex;
    @FXML
    private TextField txtphone;
    @FXML
    private TextField txtaddress;
    @FXML
    private Button btnpay;
    @FXML
    private Button btnsreachts;

    private int idtau;
    //
    @FXML
    void searchTrainMoved(MouseEvent event) {
    	btn.setTextFill(Color.BLACK);
    	btn.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void searchTrainExited(MouseEvent event) {
    	btn.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    @FXML
    void searchGaMoved(MouseEvent event) {
    	btnsreachts.setTextFill(Color.BLACK);
    	btnsreachts.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void searchGaExited(MouseEvent event) {
    	btnsreachts.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    @FXML
    void searchTicketMoved(MouseEvent event) {
    	btnSearchTic.setTextFill(Color.BLACK);
    	btnSearchTic.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void searchTicketExited(MouseEvent event) {
    	btnSearchTic.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    @FXML
    void payMoved(MouseEvent event) {
    	btnpay.setTextFill(Color.BLACK);
    	btnpay.setStyle("-fx-background-color:  #F5FFFA;");
    }
    @FXML
    void payTicketExited(MouseEvent event) {
    	btnpay.setStyle("-fx-text-fill: #008b8b;-fx-background-color:   #F5FFFA;");
    }
    //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn.setDisable(true);
        btnpay.setDisable(true);
        try {
            req = LocateRegistry.getRegistry(2000);
            rmi= (RMI) req.lookup("quanlyvetau");
            setSex();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void setSex(){
        ObservableList<SexModel> list = FXCollections.observableArrayList();
        list.add(new SexModel(0,"Nam"));
        list.add(new SexModel(1,"Nữ"));
        sex.setItems(list);
    }

    public void loadToa(){
        grid.getChildren().clear();
        int soToa = convertNametoSoToa(comboBox.getValue());
        int id = convertNametoId(comboBox.getValue());
        try {
            List<ToaModel> toas = rmi.findAllToa();
            for (int j = 0 ;j <4; j++){
                for (int i = 1;i <= (soToa/4) ; i++){
                    int dem = i + (soToa/4)*j;
                    Button Toa = new Button(String.valueOf(dem));
                    Toa.setMaxSize(80,30);
                    Toa.setStyle("-fx-background-color:  #0BDA51;");
                    Toa.setTextFill(Color.WHITE);
                    Toa.setOnMouseMoved(event ->  {
                    	Toa.setStyle("-fx-background-color:  #009E60;");
            		});
                   
                    
                    Toa.setOnMouseExited(event ->  {
                    	Toa.setStyle("-fx-background-color:  #0BDA51;");
            		});                 
                    for (ToaModel item:toas){
                        Toa.setOnAction(event1 -> {
                            txtseats.setText(String.valueOf(dem));
                            txtprice.setText(String.valueOf(item.getGiaVe()));
                            btnpay.setDisable(false);
                            idtau = id;
                            System.out.println(idtau);
                        });
                        if(item.getIdtau() == id){
                            if (item.getSoGhe()==dem){
                                Toa.setDisable(true);
                                Toa.setStyle("-fx-background-color:  #FF0000;");
                                Toa.setTextFill(Color.WHITE);
                            }
                        }
                    }
//                Toa.setDisable(true);
                    grid.add(Toa,j,i-1);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

   
    public int isertPhieu(){
        try {
            PhieuDatVeModel phieu = new PhieuDatVeModel();
            phieu.setName(new RandomString().generateRandomString());
            phieu.setIdToa(Integer.parseInt(txtseats.getText()));
            return rmi.insertPhieu(phieu);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int isertKhachHang(){
        try {
            KhachHangModel khach = new KhachHangModel();
            khach.setHoTen(txtname.getText());
            khach.setGioiTinh((byte)sex.getValue().getId());
            khach.setDiaChi(txtaddress.getText());
            khach.setSdt(txtphone.getText());
            khach.setMaPhieuint(isertPhieu());
            return rmi.insertKhachHang(khach);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int isertBienLai(){
        try {
        	LocalDate localDate = datePicker.getValue();
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        	String formattedString = localDate.format(formatter);
            BienLaiModel bienLai = new BienLaiModel();
            bienLai.setHoTenKH(txtname.getText());
            bienLai.setGioiTinhKH((byte)sex.getValue().getId());
            bienLai.setDiaChi(txtaddress.getText());
            bienLai.setSdtKH(txtphone.getText());
            bienLai.setTenTauKH((String)comboBox.getValue());
            bienLai.setGa(txtstation.getText());
            bienLai.setNgayDi(formattedString);
            bienLai.setSoGhe(Integer.parseInt(txtseats.getText()));
            bienLai.setGiaVe(Long.parseLong(txtprice.getText()));
            bienLai.setMaPhieu(new RandomString().generateRandomString());
            return rmi.insertBienLai(bienLai);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void pay(ActionEvent event){
        if (txtname.getText().equals("") || txtphone.getText().equals("") || txtaddress.getText().equals("")){
            Notification.NotifError("Error","Vui lòng nhập");
            return;
        }
        ToaModel toa = new ToaModel();
        toa.setSoGhe(Integer.parseInt(txtseats.getText()));
        toa.setGiaVe(Long.parseLong(txtprice.getText()));
        toa.setIdtau(idtau);
        try {
            int ok = rmi.insertToa(toa);
//            int phieu = isertPhieu();
            int khach = isertKhachHang();
            int bienLai = isertBienLai();
            if (ok == 1  && khach>0){
                System.out.println("Thành công");
                loadToa();
                Notification.NotifSuccess("Susscess","");
                txtseats.setText("");
                txtprice.setText("");
                txtname.setText("");
                sex.setValue(null);
                txtphone.setText("");
                txtaddress.setText("");
            }else {
                System.out.println("Thất bại");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getTau(ActionEvent event) {
        comboBox.setValue(null);
        String date = datePicker.getEditor().getText();
        System.out.println(date);
        try {
            List<TauModel> list = rmi.findAll();
            ObservableList<String> listTau = FXCollections.observableArrayList();
            for(TauModel item :list){
                if (date.equals(item.getLichTrinh().getNgayDi())){
                    listTau.add(item.getTenTau());
                }
            }
            btn.setDisable(false);
            comboBox.setItems(listTau);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void SearchTrainStation(ActionEvent event) throws IOException {
//        Node node = (Node) event.getSource();
        Stage stage = new Stage();

        //stage.setMaximized(true);
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TrainStation.fxml")));
        stage.setScene(scene);
        stage.setTitle("TrainStation");
        stage.show();
    }
    public void SearchTicket(ActionEvent event) throws IOException {
//      Node node = (Node) event.getSource();
      Stage stage = new Stage();

      //stage.setMaximized(true);
      stage.close();
      Scene scene = new Scene(FXMLLoader.load(getClass().getResource("searchTicked.fxml")));
      stage.setScene(scene);
      stage.setTitle("Search Booked Tickets");
      stage.show();
  }

    public int convertNametoSoToa(String name){
        try {
            List<TauModel> list = rmi.findAll();
            for (TauModel item:list){
                if (name.equals(item.getTenTau())){
                    return item.getSoToa();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
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
    public int convertNametoIdGa(String name){
        try {
            List<TauModel> list = rmi.findAll();
            for (TauModel item:list){
                if (name.equals(item.getTenTau())){
                    return item.getId_ga();
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void getToa(ActionEvent event){
        loadToa();
        String a = comboBox.getValue();
        int id = convertNametoIdGa(a);
        int ids = convertNametoId(comboBox.getValue());
        System.out.println(id);
        try {
            List<GaModel> list = rmi.findGaByTau(id);
            for(GaModel item :list){
            	txtstation.setText(String.valueOf(item.toString()));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
