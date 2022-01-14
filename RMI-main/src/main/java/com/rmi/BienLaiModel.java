package com.rmi;

import java.io.Serializable;

public class BienLaiModel implements Serializable{
	 private int id;
	 private String hoTenKH;
	 private byte gioiTinhKH;
	 private String diaChi;
	 private String sdtKH;
	 private String tenTauKH;
	 private String ga;
	 private String ngayDi;
	 private int soGhe;
	 private Long giaVe;
	 private String maPhieu;

	 public BienLaiModel(int id, String hoTenKH, byte gioiTinhKH, String diaChi, String sdtKH, String tenTauKH, String ga, String ngayDi, int soGhe, long giaVe, String maPhieu) {
	        this.id = id;
	        this.hoTenKH = hoTenKH;
	        this.gioiTinhKH = gioiTinhKH;
	        this.diaChi = diaChi;
	        this.sdtKH = sdtKH;
	        this.tenTauKH = tenTauKH;
	        this.ga = ga;
	        this.ngayDi = ngayDi;
	        this.soGhe = soGhe;
	        this.giaVe = giaVe;
	        this.maPhieu = maPhieu;
	    }

	    public BienLaiModel() {
	    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public byte getGioiTinhKH() {
		return gioiTinhKH;
	}
	public void setGioiTinhKH(byte gioiTinhKH) {
		this.gioiTinhKH = gioiTinhKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdtKH() {
		return sdtKH;
	}
	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}
	public String getTenTauKH() {
		return tenTauKH;
	}
	public void setTenTauKH(String tenTauKH) {
		this.tenTauKH = tenTauKH;
	}
	public String getGa() {
		return ga;
	}
	public void setGa(String ga) {
		this.ga = ga;
	}
	public String getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(String ngayDi) {
		this.ngayDi = ngayDi;
	}
	public int getSoGhe() {
		return soGhe;
	}
	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}
	public Long getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(Long giaVe) {
		this.giaVe = giaVe;
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	 
	 
	 
}
