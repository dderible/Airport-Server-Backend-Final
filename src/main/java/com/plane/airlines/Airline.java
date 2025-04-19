package com.plane.airlines;

import com.plane.flights.Flight;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;

    private Long flightsNumber;
    private String airlineName;
    private String originCountry;

    // Relationship
    @OneToMany
    private List<Flight> flightList;

    public Airline(Long airlineId, String airlineName, String originCountry, Long flightsNumber) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.originCountry = originCountry;
        this.flightsNumber = flightsNumber;
        this.flightList = new ArrayList<>();
    }

    public Airline() {
        this.flightList = new ArrayList<>();
    }

    public Airline(String airlineName, String originCountry, Long flightsNumber) {
        this.airlineName = airlineName;
        this.originCountry = originCountry;
        this.flightsNumber = flightsNumber;
        this.flightList = new ArrayList<>();
    }

    // Getters & Setters
    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public Long getFlightsNumber() {
        return flightsNumber;
    }

    public void setFlightsNumber(Long flightsNumber) {
        this.flightsNumber = flightsNumber;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public void addFlight(Flight flight) {
        this.flightList.add(flight);
    }

    public void removeFlight(Flight flight) {
        this.flightList.remove(flight);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId=" + airlineId +
                ", flightsNumber=" + flightsNumber +
                ", airlineName='" + airlineName + '\'' +
                ", originCountry='" + originCountry + '\'' +
                ", flightList=" + flightList +
                '}';
    }
}