package com.example.demoabspassengercheck;

public class Passenger {
    private String Username;
    private String Fullname;
    private String Gender;
    private String Address;
    private int Age;
    private int Children;
    private Flight flight;


    public Passenger(String username, String fullname, String gender, String address,int age,int children, Flight f) {
        Username = username;
        Fullname = fullname;
        Gender = gender;
        Address = address;
        Age = age;
        Children = children;
        flight = f;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
}
