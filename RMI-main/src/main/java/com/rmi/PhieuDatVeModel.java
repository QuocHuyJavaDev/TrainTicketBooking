package com.rmi;

import java.io.Serializable;

public class PhieuDatVeModel implements Serializable {
    private int id;
    private String name;
    private ToaModel id_toa;
    private int idToa;

    public int getIdToa() {
        return idToa;
    }

    public void setIdToa(int idToa) {
        this.idToa = idToa;
    }

    public PhieuDatVeModel(int id, String name, int idToa) {
        this.id = id;
        this.name = name;
        this.idToa = idToa;
    }

    public PhieuDatVeModel() {
    }

    public PhieuDatVeModel(int id, String name, ToaModel id_toa) {
        this.id = id;
        this.name = name;
        this.id_toa = id_toa;
    }

    public ToaModel getId_toa() {
        return id_toa;
    }

    public void setId_toa(ToaModel id_toa) {
        this.id_toa = id_toa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
