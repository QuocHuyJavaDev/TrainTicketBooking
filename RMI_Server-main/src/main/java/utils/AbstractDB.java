package utils;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.rmi.BienLaiModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AbstractDB{
    public static Connection getConnection(){
        try {
            String url = "jdbc:sqlite:D:\\CUOIKY\\RMI_Server-main\\db\\qlvt.db";
            return DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        
    }
    public static ObservableList<BienLaiModel> getData(){
        Connection conn = AbstractDB.getConnection();
        ObservableList<BienLaiModel> list = FXCollections.observableArrayList();
        String sql = "select * from BienLai";
        try {
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BienLaiModel toa = new BienLaiModel(rs.getInt("blid"), rs.getString("hoTenKH"), rs.getByte("gioiTinh"), rs.getString("diaChi")
						, rs.getString("sdtKH"), rs.getString("tenTauKH"), rs.getString("Ga"), rs.getString("ngayDi")
						, rs.getInt("soGhe"), rs.getLong("giaVe"), rs.getString("maPhieu"));
                list.add(toa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        

       
    }
        return list;
}
}
