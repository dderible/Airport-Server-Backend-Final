package com.plane.flights;

import com.plane.aircraft.Aircraft;
import com.plane.gates.Gate;
import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String flightSeat;
    private String flightOrigin;
    private String flightDestination;
    private String flightAirline;

    @OneToOne
    Gate gate;

    @OneToOne
    Aircraft aircraft;

    public Flight(Long flightId, String flightSeat, String flightOrigin, String flightDestination, String flightAirline, Gate gate, Aircraft aircraft) {
        this.flightId = flightId;
        this.flightSeat = flightSeat;
        this.flightOrigin = flightOrigin;
        this.flightDestination = flightDestination;
        this.flightAirline = flightAirline;
        this.gate = gate;
        this.aircraft = aircraft;
    }

    public Flight(String flightSeat, String flightOrigin, String flightDestination, String flightAirline) {
        this.flightSeat = flightSeat;
        this.flightOrigin = flightOrigin;
        this.flightDestination = flightDestination;
        this.flightAirline = flightAirline;
    }

    public Flight() {

    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getFlightSeat() {
        return flightSeat;
    }

    public void setFlightSeat(String flightSeat) {
        this.flightSeat = flightSeat;
    }

    public String getFlightOrigin() {
        return flightOrigin;
    }

    public void setFlightOrigin(String flightOrigin) {
        this.flightOrigin = flightOrigin;
    }

    public String getFlightDestination() {
        return flightDestination;
    }

    public void setFlightDestination(String flightDestination) {
        this.flightDestination = flightDestination;
    }

    public String getFlightAirline() {
        return flightAirline;
    }

    public void setFlightAirline(String flightAirline) {
        this.flightAirline = flightAirline;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightSeat='" + flightSeat + '\'' +
                ", flightOrigin='" + flightOrigin + '\'' +
                ", flightDestination='" + flightDestination + '\'' +
                ", flightAirline='" + flightAirline + '\'' +
                ", gate=" + gate +
                ", aircraft=" + aircraft +
                '}';
    }
}