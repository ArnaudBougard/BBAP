package com.eu.fpms.bbap;

public class Drink {

    private int id;
    private String name;
    private String country;
    private double vol;
    private String kcal;

    public Drink(){}

    public Drink(String name, String country, double vol, String kcal){

        this.name = name;
        this.country = country;
        this.vol = vol;
        this.kcal = kcal;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

}
