package com.rmi;

import java.io.Serializable;

public class KhachHangModel implements Serializable {
    private int id;
    private String hoTen;
    private byte gioiTinh;
    private String diaChi;
    private String sdt;
    private PhieuDatVeModel maPhieu;
    private int maPhieuint;

    public KhachHangModel(int id, String hoTen, byte gioiTinh, String diaChi,  String sdt, int maPhieuint) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maPhieuint = maPhieuint;
    }

    public int getMaPhieuint() {
        return maPhieuint;
    }

    public void setMaPhieuint(int maPhieuint) {
        this.maPhieuint = maPhieuint;
    }

    public KhachHangModel() {
    }

    public KhachHangModel(int id, String hoTen, byte gioiTinh, String diaChi, String sdt, PhieuDatVeModel maPhieu) {
        this.id = id;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maPhieu = maPhieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public byte isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(byte gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
    public String getDiaChi() {
        return diaChi;
    }
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }


    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public PhieuDatVeModel getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(PhieuDatVeModel maPhieu) {
        this.maPhieu = maPhieu;
    }
}
