package com.example.demoabs;

public class Airline {
    private int ID;
    private String Name;

    public Airline(int ID, String name) {
        this.ID = ID;
        Name = name;
    }


    public Airline(String name){
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
