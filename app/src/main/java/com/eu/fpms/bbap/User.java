package com.eu.fpms.bbap;

/**
 * Created by Arnaud on 14-05-18.
 */

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String age;
    private String height;
    private String weight;

    public User(String username, String password, String email, String sex, String age, String height, String weight) {

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
