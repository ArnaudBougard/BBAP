package com.eu.fpms.bbap;

import java.util.ArrayList;

public class Drinksheet {

    private int id;
    private String hour;
    private String date;
    private ArrayList<Integer> drink_list;
    private ArrayList<Integer> amount_list;

    public Drinksheet(String hour, String date){

        this.hour = hour;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Integer> getDrink_list() {
        return drink_list;
    }

    public void setDrink_list(ArrayList<Integer> drink_list) {
        this.drink_list = drink_list;
    }

    public ArrayList<Integer> getAmount_list() {
        return amount_list;
    }

    public void setAmount_list(ArrayList<Integer> amount_list) {
        this.amount_list = amount_list;
    }
}
