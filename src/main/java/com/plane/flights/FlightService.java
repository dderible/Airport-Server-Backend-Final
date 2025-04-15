package com.plane.flights;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight findByFlightId(Long flightId) {
        return flightRepository.findById(flightId).orElseThrow(EntityNotFoundException::new);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public void deleteFlight(Long flightId) {
        Flight flight = findByFlightId(flightId);
        flightRepository.delete(flight);
    }

    public List<Flight> getByFlightId(Long flightId) {
        return flightRepository.findByFlightId(flightId);
    }

    public List<Flight> getByFlightDestination(String flightDestination) {
        return flightRepository.findByFlightDestination(flightDestination);
    }

    public List<Flight> getByFlightOrigin(String flightOrigin) {
        return flightRepository.findByFlightOrigin(flightOrigin);
    }

    public List<Flight> getByFlightAirline(String flightAirline) {
        return flightRepository.findByFlightAirline(flightAirline);
    }
}