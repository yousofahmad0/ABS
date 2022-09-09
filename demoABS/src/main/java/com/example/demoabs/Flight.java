package com.example.demoabs;

public class Flight {
    private int idFlight;
    private String Date;
    private int idAirline;
    private String Destination;
    private String TimeStart;
    private String TimeArrive;
    private String CountryThro;
    private int Seats;
    private Double DefaultPrice;

    public Flight(int idFlight, String date, int idAirline, String destination, String timeStart, String timeArrive, String countryThro, int seats, Double defaultPrice) {
        this.idFlight = idFlight;
        Date = date;
        this.idAirline = idAirline;
        Destination = destination;
        TimeStart = timeStart;
        TimeArrive = timeArrive;
        CountryThro = countryThro;
        Seats = seats;
        DefaultPrice = defaultPrice;
    }

    public Flight(int idf, String d, String dest, String ts, String ta, String cthro, Double defaultPrice){
        idFlight = idf;
        Date = d;
        Destination = dest;
        TimeStart = ts;
        TimeArrive = ta;
        CountryThro = cthro;
        DefaultPrice = defaultPrice;
    }

    public int getIdFlight(){
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public String getDate(){
        return Date;
    }
    public String getDestination(){
        return Destination;
    }
    public String getTimeStart(){
        return TimeStart;
    }
    public String getTimeArrive(){
        return TimeArrive;
    }
    public String getCountryThro(){
        return CountryThro;
    }

    public void setDate(String d){
        Date = d;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }
    public void setTimeStart(String timeStart){
        TimeStart = timeStart;
    }

    public void setTimeArrive(String timeArrive) {
        TimeArrive = timeArrive;
    }

    public void setCountryThro(String countryThro) {
        CountryThro = countryThro;
    }

    public Double getDefaultPrice() {
        return DefaultPrice;
    }

    public void setDefaultPrice(Double defaultPrice) {
        DefaultPrice = defaultPrice;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    public int getSeats() {
        return Seats;
    }

    public void setSeats(int seats) {
        Seats = seats;
    }
}
