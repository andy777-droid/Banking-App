package com.example.SisonkeBankApp;

public class User {
    static String email, fname, lname, password, mobile, gender;
    static Double current, savings;

    public User(String email, String fname, String lname, String password, String mobile, String gender, Double current, Double savings) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.mobile = mobile;
        this.gender = gender;
        this.current = current;
        this.savings = savings;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public static String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Double getCurrent() {
        return current;
    }

    public static void setCurrent(Double current) {
        User.current = current;
    }

    public static Double getSavings() {
        return savings;
    }

    public static void setSavings(Double savings) {
        User.savings = savings;
    }
}
