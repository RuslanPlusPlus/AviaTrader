package com.example.spring_security_app.web.dto;

import com.example.spring_security_app.model.Ticket;

public class TicketDto {

    private Long id;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private String price;
    //Business, Comfort, Economy
    private String flightClass;
    //FREE, NOT_FREE
    private String isFree;
    private String airCompany;

    public TicketDto(){
    }

    public TicketDto(Ticket ticket){
        super();
        this.id = ticket.getId();
        this.departureCity = ticket.getDepartureCity();
        this.arrivalCity = ticket.getArrivalCity();
        this.departureDate = String.valueOf(ticket.getDepartureDate());
        this.arrivalDate = String.valueOf(ticket.getArrivalDate());
        this.departureTime = String.valueOf(ticket.getDepartureTime().toLocalTime());
        this.arrivalTime = String.valueOf(ticket.getArrivalTime().toLocalTime());
        this.price = String.valueOf(ticket.getPrice());
        this.flightClass = ticket.getFlightClass();
        this.isFree = ticket.getIsFree();
        this.airCompany = ticket.getAirCompany();
    }

    public Long getId() {
        return id;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }

    public String getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(String airCompany) {
        this.airCompany = airCompany;
    }


}
