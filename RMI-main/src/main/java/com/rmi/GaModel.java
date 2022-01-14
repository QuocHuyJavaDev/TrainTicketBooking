package com.rmi;

import java.io.Serializable;

public class GaModel implements Serializable {
    private int id;
    private String name;

    public GaModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GaModel() {
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

    @Override
    public String toString() {
        return name;
    }
}
