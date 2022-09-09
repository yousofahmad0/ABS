package com.example.demoabs;

public class Passengers {
    private int PID;
    private String Username;
    private String Fullname;
    private int FNO;
    private String Gender;
    private String Address;
    private int Age;
    private int Children;

    public Passengers(int PID, String username, String fullname, int FNO, String gender, String address, int age, int children) {
        this.PID = PID;
        Username = username;
        Fullname = fullname;
        this.FNO = FNO;
        Gender = gender;
        Address = address;
        Age = age;
        Children = children;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public int getChildren() {
        return Children;
    }

    public void setChildren(int children) {
        Children = children;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getFNO() {
        return FNO;
    }

    public void setFNO(int FNO) {
        this.FNO = FNO;
    }
}
