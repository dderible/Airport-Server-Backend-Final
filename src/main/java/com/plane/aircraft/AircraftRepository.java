package com.plane.aircraft;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    List<Aircraft> findByDepartureAirport_airportId(Long departureAirportId);
    List<Aircraft> findByArrivalAirport_airportId(Long arrivalAirportId);
}

