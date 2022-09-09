package com.example.demoabspassengercheck;

public class User {
    private String Username;
    private String Password;
    private String Fullname;
    private String Gender;
    private String Address;
    private int Age;
    private int Children;
    private int Phone;

    public User(String username, String password, String fullname, String gender, String address, int age, int children, int phone) {
        Username = username;
        Password = password;
        Fullname = fullname;
        Gender = gender;
        Address = address;
        Age = age;
        Children = children;
        Phone = phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
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

    public int getChildren() {
        return Children;
    }

    public void setChildren(int children) {
        Children = children;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }
}
