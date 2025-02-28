package com.plane.aircraft;

import com.plane.airport.Airport;
import com.plane.passengers.Passengers;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftId;

    private String model;
    private String airline;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<Passengers> passengers;

    public Aircraft(String model, String airline, Airport departureAirport, Airport arrivalAirport) {
        this.model = model;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public Long getId() {
        return aircraftId;
    }

    public void setId(Long id) {
        this.aircraftId = id;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public List<Passengers> getPassengers() {
        return passengers;

    }
    public void setPassengers(List<Passengers> passengers) {
        this.passengers = passengers;
    }
}

