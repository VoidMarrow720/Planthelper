package com.MIL8I8.Planthelper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nev;
    private String latinNev;

    @ManyToOne
    @JoinColumn(name = "Gid")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "Lid")
    private Lite lite;

    @ManyToOne
    @JoinColumn(name = "Wid")
    private Water water;

    @ManyToOne
    @JoinColumn(name = "Sid")
    private Soil soil;

    @ManyToOne
    @JoinColumn(name = "Tid")
    private Temp temp;

    @ManyToOne
    @JoinColumn(name = "Hid")
    private Hum hum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLatinNev() {
        return latinNev;
    }

    public void setLatinNev(String latinNev) {
        this.latinNev = latinNev;
    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Lite getLite() {
        return lite;
    }

    public void setLite(Lite lite) {
        this.lite = lite;
    }

    public Water getWater() {
        return water;
    }

    public void setWater(Water water) {
        this.water = water;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Hum getHum() {
        return hum;
    }

    public void setHum(Hum hum) {
        this.hum = hum;
    }
}

