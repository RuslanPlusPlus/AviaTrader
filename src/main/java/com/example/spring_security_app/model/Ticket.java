package com.example.spring_security_app.model;
import com.example.spring_security_app.web.dto.TicketDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "price")
    private int price;

    //Business, Comfort, Economy
    @Column(name = "class")
    private String flightClass;

    //FREE, NOT_FREE
    @Column(name = "is_free")
    private String isFree;

    @Column(name = "air_company")
    private String airCompany;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAirCompany() {
        return airCompany;
    }

    public void setAirCompany(String airCompany) {
        this.airCompany = airCompany;
    }


    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public Ticket(String departureCity, String arrivalCity,
                  LocalDate departureDate, LocalDate arrivalDate,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  int price, String flightClass,
                  String isFree, String airCompany) {
        super();
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.flightClass = flightClass;
        this.isFree = isFree;
        this.airCompany = airCompany;
    }

    public boolean isSuitable(TicketDto ticketDto){
        if (ticketDto == null){
            return false;
        }
        if(!this.isFree.equals("FREE"))
            return false;
        if (!this.departureCity.equals(ticketDto.getDepartureCity()))
            return false;
        if (!this.arrivalCity.equals(ticketDto.getArrivalCity()))
            return false;
        LocalDate date = LocalDate.parse(ticketDto.getDepartureDate());
        if (!date.equals(this.departureDate))
            return false;
        if (!this.flightClass.equals(ticketDto.getFlightClass()))
            return false;
        return true;
    }

    public Ticket() {
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

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsFree() {
        return isFree;
    }

    public void setIsFree(String isFree) {
        this.isFree = isFree;
    }
}
