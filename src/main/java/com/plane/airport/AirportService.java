package com.plane.airport;

import com.plane.cities.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CitiesRepository citiesRepository;

    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Iterable<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long airportId) {
        return airportRepository.findById(airportId);
    }

    public Optional<Airport> updateAirport(Long airportId, Airport updatedAirport) {
        return airportRepository.findById(airportId).map(airport -> {
            airport.setName(updatedAirport.getName());
            airport.setCode(updatedAirport.getCode());
            airport.setCityName(updatedAirport.getCityName());
            return airportRepository.save(airport);
        });
    }

    public boolean deleteAirport(Long airportId) {
        if (airportRepository.existsById(airportId)) {
            airportRepository.deleteById(airportId);
            return true;
        }
        return false;
    }

    public Iterable<Airport> getAirportsByCityId(Long cityId) {
        if (citiesRepository.existsById(cityId)) {
            return airportRepository.findAirportByCityName_cityId(cityId);
        }
        return List.of();
    }

    public Airport findByAirportId(Long airportId) {
        return airportRepository.findByAirportId(airportId);
    }
}

