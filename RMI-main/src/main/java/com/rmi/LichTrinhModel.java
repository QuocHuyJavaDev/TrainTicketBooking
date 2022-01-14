package com.rmi;

import java.io.Serializable;

public class LichTrinhModel implements Serializable {
    private int id;
    private String ngayDi;
    private String ngayDen;

    public LichTrinhModel() {
    }

    public LichTrinhModel(int id, String ngayDi, String ngayDen) {
        this.id = id;
        this.ngayDi = ngayDi;
        this.ngayDen = ngayDen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(String ngayDi) {
        this.ngayDi = ngayDi;
    }

    public String getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(String ngayDen) {
        this.ngayDen = ngayDen;
    }

    @Override
    public String toString() {
        return ngayDi;
    }
}
