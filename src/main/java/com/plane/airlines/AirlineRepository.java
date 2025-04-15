package com.plane.airlines;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    List<Airline> findByAirlineId(Long airlineId);
    List<Airline> findByAirlineName(String airlineName);

    // .
}