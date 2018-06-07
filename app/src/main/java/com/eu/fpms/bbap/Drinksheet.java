package com.eu.fpms.bbap;

public class Drinksheet {

    private int id;
    private String hour;
    private String date;
    private String drink_list;
    private String amount_list;

    public Drinksheet(){}

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

    public String getDrink_list() {
        return drink_list;
    }

    public void setDrink_list(String drink_list) {
        this.drink_list = drink_list;
    }

    public String getAmount_list() {
        return amount_list;
    }

    public void setAmount_list(String amount_list) {
        this.amount_list = amount_list;
    }

}
