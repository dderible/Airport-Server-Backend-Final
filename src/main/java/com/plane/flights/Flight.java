package com.plane.flights;

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
    private Gate gate;

    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightSeat='" + flightSeat + '\'' +
                ", flightOrigin='" + flightOrigin + '\'' +
                ", flightDestination='" + flightDestination + '\'' +
                ", flightAirline='" + flightAirline + '\'' +
                ", gate=" + gate +
                '}';
    }
}