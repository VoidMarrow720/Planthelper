package com.MIL8I8.Planthelper;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import java.util.HashSet;
import java.util.Set;

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

        @ManyToMany
        @JoinTable(name = "plant_lite",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "lite_id"))
        private Set<Lite> lites = new HashSet<>();

        @ManyToMany
        @JoinTable(name = "plant_water",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "water_id"))
        private Set<Water> waters = new HashSet<>();

        @ManyToMany
        @JoinTable(name = "plant_soil",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "soil_id"))
        private Set<Soil> soils = new HashSet<>();

        @ManyToMany
        @JoinTable(name = "plant_temp",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "temp_id"))
        private Set<Temp> temps = new HashSet<>();

        @ManyToMany
        @JoinTable(name = "plant_hum",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "hum_id"))
        private Set<Hum> hums = new HashSet<>();

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

    public Set<Lite> getLites() {
        return lites;
    }

    public void setLites(Set<Lite> lites) {
        this.lites = lites;
    }

    public Set<Water> getWaters() {
        return waters;
    }

    public void setWaters(Set<Water> waters) {
        this.waters = waters;
    }

    public Set<Soil> getSoils() {
        return soils;
    }

    public void setSoils(Set<Soil> soils) {
        this.soils = soils;
    }

    public Set<Temp> getTemps() {
        return temps;
    }

    public void setTemps(Set<Temp> temps) {
        this.temps = temps;
    }

    public Set<Hum> getHums() {
        return hums;
    }

    public void setHums(Set<Hum> hums) {
        this.hums = hums;
    }
}

