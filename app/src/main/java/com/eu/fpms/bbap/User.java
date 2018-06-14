package com.eu.fpms.bbap;

import java.util.ArrayList;

/**
 * Created by Arnaud on 14-05-18.
 */

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private int age;
    private int height;
    private int weight;

    public User(){};

    public User(String username, String password, String email, String sex, int age, int height, int weight) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.sex= sex;
        this.age = age;
        this.height = height;
        this.weight = weight;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
