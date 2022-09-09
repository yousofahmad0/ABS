package com.example.demoabspassengercheck;


public class Flight {
    private int idFlight;
    private String Date;
    private String Destination;
    private String TimeStart;
    private String TimeArrive;
    private String CountryThro;
    private Double DefaultPrice;

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
}