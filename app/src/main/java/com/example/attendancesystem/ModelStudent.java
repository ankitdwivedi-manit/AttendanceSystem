package com.example.attendancesystem;

public class ModelStudent {
    private String name;
    private String batch;
    private String password;
    private String scholar_no;

    public ModelStudent() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScholar_no() {
        return scholar_no;
    }

    public void setScholar_no(String scholar_no) {
        this.scholar_no = scholar_no;
    }
}
