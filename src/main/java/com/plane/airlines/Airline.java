package com.plane.airlines;

import jakarta.persistence.*;

@Entity
@Table (name = "airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;

    private String airlineName;

    // TBD: reminder for myself to check if we need function to
    // actually register a passenger to an airline
}