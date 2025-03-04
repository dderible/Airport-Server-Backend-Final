package com.plane.airport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    Iterable<Airport> findAirportByCityName_cityId(Long cityId);
    public Airport findByAirportId(Long airportId);
}

