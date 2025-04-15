package com.plane.flights;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFlightId(Long flightId);
    List<Flight> findByFlightDestination(String flightDestination);
    List<Flight> findByFlightOrigin(String flightOrigin);
    List<Flight> findByFlightAirline(String flightAirline);
}