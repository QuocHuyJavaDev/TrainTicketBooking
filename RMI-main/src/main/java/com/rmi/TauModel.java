package com.rmi;

import java.io.Serializable;

public class TauModel implements Serializable {
    private int id;
    private String tenTau;
    private int soToa;
    private LichTrinhModel lichTrinh;
    private int id_ga;

    private String lich;

    public String getLich() {
        return lich;
    }

    public void setLich(String lich) {
        this.lich = lichTrinh.getNgayDi();
    }
    public TauModel() {
    }

    public TauModel(int id, String tenTau, int soToa, LichTrinhModel lichTrinh, int id_ga) {
        this.id = id;
        this.tenTau = tenTau;
        this.soToa = soToa;
        this.lichTrinh = lichTrinh;
        this.id_ga = id_ga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTau() {
        return tenTau;
    }

    public void setTenTau(String tenTau) {
        this.tenTau = tenTau;
    }

    public int getSoToa() {
        return soToa;
    }

    public void setSoToa(int soToa) {
        this.soToa = soToa;
    }

    public LichTrinhModel getLichTrinh() {
        return lichTrinh;
    }


    public void setLichTrinh(LichTrinhModel lichTrinh) {
        this.lichTrinh = lichTrinh;
    }

    public int getId_ga() {
        return id_ga;
    }

    public void setId_ga(int id_ga) {
        this.id_ga = id_ga;
    }
}
