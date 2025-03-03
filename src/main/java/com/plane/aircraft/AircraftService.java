package com.plane.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public Aircraft getAircraftById(Long aircraftId) {
        return aircraftRepository.findById(aircraftId).orElse(null);
    }

    public Aircraft saveAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Long aircraftId, Aircraft aircraftDetails) {
        Optional<Aircraft> existingAircraft = aircraftRepository.findById(aircraftId);
        if (existingAircraft.isPresent()) {
            Aircraft aircraft = existingAircraft.get();
            aircraft.setModel(aircraftDetails.getModel());
            aircraft.setAirline(aircraftDetails.getAirline());
            return aircraftRepository.save(aircraft);
        }
        return null;
    }

    public void deleteAircraft(Long aircraftId) {
        aircraftRepository.deleteById(aircraftId);
    }
}
