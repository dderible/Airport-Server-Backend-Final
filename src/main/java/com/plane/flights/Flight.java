package com.plane.flights;

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

    // maybe?: switch airports function here?
}