package com.plane.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long airportId) {
        return airportRepository.findById(airportId).orElse(null);
    }

    public List<Airport> getAirportsByCity(Long cityId) {
        return airportRepository.findByCityId(cityId);
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long airportId, Airport airportDetails) {
        Optional<Airport> existingAirport = airportRepository.findById(airportId);
        if (existingAirport.isPresent()) {
            Airport airport = existingAirport.get();
            airport.setName(airportDetails.getName());
            airport.setCode(airportDetails.getCode());
            airport.setCity(airportDetails.getCity());
            return airportRepository.save(airport);
        }
        return null;
    }

    public void deleteAirport(Long airportId) {
        airportRepository.deleteById(airportId);
    }
}

