package com.plane.aircraft;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    Iterable<Aircraft> findAircraftByAirportId_airportId(Long airportId);
    public Aircraft findByAircraftId(Long aircraftId);

}

