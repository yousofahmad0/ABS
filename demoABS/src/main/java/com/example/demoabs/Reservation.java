package com.example.demoabs;

public class Reservation {

    private int TicketNum;
    private Double TotalPrice;
    private String Date;
    private String Cl;
    private String Destination;
    private Flight flight;


    public Reservation(int ticketNum, Double totalPrice, String date, String cl, String distination, Flight f) {
        TicketNum = ticketNum;
        TotalPrice = totalPrice;
        Date = date;
        Cl = cl;
        Destination = distination;
        flight = f;

    }

//    public static void incrementId(){
//        ResId++;
//    }

    public int getTicketNum() {
        return TicketNum;
    }

    public void setTicketNum(int ticketNum) {
        TicketNum = ticketNum;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCl() {
        return Cl;
    }

    public void setCl(String cl) {
        Cl = cl;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
