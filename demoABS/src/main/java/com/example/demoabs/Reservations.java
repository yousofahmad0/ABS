package com.example.demoabs;

public class Reservations {
    private int ResId;
    private int TicketNum;
    private Double TotalPrice;
    private String Date;
    private int Fno;
    private String Cl;
    private String Destination;

    public Reservations(int resId, int ticketNum, Double totalPrice, String date, int fno, String cl, String destination) {
        ResId = resId;
        TicketNum = ticketNum;
        TotalPrice = totalPrice;
        Date = date;
        Fno = fno;
        Cl = cl;
        Destination = destination;
    }

    public int getResId() {
        return ResId;
    }

    public void setResId(int resId) {
        ResId = resId;
    }

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

    public int getFno() {
        return Fno;
    }

    public void setFno(int fno) {
        Fno = fno;
    }
}
