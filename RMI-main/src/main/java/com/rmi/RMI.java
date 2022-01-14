package com.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMI extends Remote {
    public List<TauModel> findAll() throws RemoteException;
    public List<ToaModel> findAllToa() throws RemoteException;
    public int insertToa(ToaModel toa) throws RemoteException;
    public int insertPhieu(PhieuDatVeModel phieu) throws RemoteException;
    public int insertKhachHang(KhachHangModel khach) throws RemoteException;
    public int insertBienLai(BienLaiModel bienLaiModel) throws RemoteException;
    public List<GaModel> findAllGa() throws RemoteException;
    public List<TauModel> findTauByGa(int id) throws RemoteException;
    public List<GaModel> findGaByTau(int i) throws RemoteException;
    public List<BienLaiModel> findBienLai(String i) throws RemoteException;
    public void deleteReceip(int i, int j) throws RemoteException;
}
