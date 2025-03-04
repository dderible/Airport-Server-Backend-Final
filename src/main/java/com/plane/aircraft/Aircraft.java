package com.plane.aircraft;

import com.plane.airport.Airport;
import com.plane.passengers.Passengers;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftId;

    private String model;
    private String airlineName;
    private int numberOfPassengers;

    @ManyToOne(cascade = CascadeType.ALL)
    private Airport airportId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Passengers passengerID;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Airport> airports = new ArrayList<Airport>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Passengers> passengers = new ArrayList<>();

    public Aircraft() {

    }

    public Aircraft(String model, String airlineName, int numberOfPassengers, Airport airportId) {
        this.model = model;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
        this.airportId = airportId;
    }


    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getType() {
        return model;
    }

    public void setType(String type) {
        this.model = type;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public Airport getAirportId() {
        return airportId;
    }

    public void setAirportId(Airport airportId) {
        this.airportId = airportId;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public Passengers getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(Passengers passengerID) {
        this.passengerID = passengerID;
    }

    public List<Passengers> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passengers> passengers) {
        this.passengers = passengers;
    }

    // toString method
    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + aircraftId +
                ", model='" + model + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }
}

