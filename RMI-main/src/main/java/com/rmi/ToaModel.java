package com.rmi;

import java.io.Serializable;

public class ToaModel implements Serializable {
    private int id;
    private int soGhe;
    private Long giaVe;
    private TauModel id_tau;
    private int idtau;

    public int getIdtau() {
        return idtau;
    }

    public void setIdtau(int idtau) {
        this.idtau = idtau;
    }

    public ToaModel(int id, int soGhe, Long giaVe, int idtau) {
        this.id = id;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.idtau = idtau;
    }

    public ToaModel() {
    }

    public ToaModel(int id, int soGhe, Long giaVe, TauModel id_tau) {
        this.id = id;
        this.soGhe = soGhe;
        this.giaVe = giaVe;
        this.id_tau = id_tau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TauModel getId_toa() {
        return id_tau;
    }

    public void setId_toa(TauModel id_toa) {
        this.id_tau = id_toa;
    }
}
