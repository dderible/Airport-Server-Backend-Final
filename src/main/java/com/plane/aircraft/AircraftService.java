package com.plane.aircraft;

import com.plane.airport.Airport;
import com.plane.airport.AirportRepository;
import com.plane.passengers.Passengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirportRepository airportRepository;

    public Aircraft addAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Iterable<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    public Optional<Aircraft> getAircraftById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId);
    }

    public Optional<Aircraft> updateAircraft(Long aircraftId, Aircraft updatedAircraft) {
        return aircraftRepository.findById(aircraftId).map(aircraft -> {
            aircraft.setModel(updatedAircraft.getModel());
            aircraft.setAirlineName(updatedAircraft.getAirlineName());
            aircraft.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
            return aircraftRepository.save(aircraft);
        });
    }

    public boolean deleteAircraft(Long aircraftId) {
        if (aircraftRepository.existsById(aircraftId)) {
            aircraftRepository.deleteById(aircraftId);
            return true;
        }
        return false;
    }

    public Optional<List<Passengers>> getPassengersByAircraft(Long id) {
        return aircraftRepository.findById(id).map(Aircraft::getPassengers);
    }

    public Aircraft findByAircraftId(Long aircraftId) {
        return aircraftRepository.findByAircraftId(aircraftId);

    }

    public Iterable<Aircraft> findByAirport(Long airportId) {
        return aircraftRepository.findAircraftByAirportId_airportId(airportId);
    }


    public Optional<Aircraft> addAirportToAircraft(Long aircraftId, Long airportId) {
        Optional<Aircraft> aircraftOpt = aircraftRepository.findById(aircraftId);
        Optional<Airport> airportOpt = airportRepository.findById(airportId);

        if (aircraftOpt.isPresent() && airportOpt.isPresent()) {
            Aircraft aircraft = aircraftOpt.get();
            aircraft.getAirports().add(airportOpt.get());
            return Optional.of(aircraftRepository.save(aircraft));
        }

        return Optional.empty();
    }
}

