package com.plane.airlines;

import com.plane.airlines.Airline;
import com.plane.airlines.AirlineRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public Airline findByAirlineId(Long airlineId) {
        return airlineRepository.findById(airlineId)
            .orElseThrow(() -> new EntityNotFoundException("ERROR: No Airline with id: " + airlineId + " exists."));
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public void deleteAirline(Long airlineId) {
        Airline airline = findByAirlineId(airlineId);
        airlineRepository.delete(airline);
    }

    public List<Airline> getByAirlineName(String airlineName) {
        return airlineRepository.findByAirlineName(airlineName);
    }

    public List<Airline> getByAirlineId(Long airlineId) {
        return airlineRepository.findByAirlineId(airlineId);
    }
}