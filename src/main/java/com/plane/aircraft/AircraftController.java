package com.plane.aircraft;

import com.plane.airport.Airport;
import com.plane.airport.AirportService;
import com.plane.cities.Cities;
import com.plane.passengers.Passengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AirportService airportService;

    // Add a new aircraft
    @PostMapping("/addNewAircraft")
    public Aircraft addNewAircraft(@RequestBody Aircraft aircraft) {

        Optional<Airport> airportOptional = Optional.ofNullable(airportService.findByAirportId(aircraft.getAirportId().getAirportId()));

        Airport airport;
        if (airportOptional.isPresent()) {
            airport = airportOptional.get();
        } else {
            // Save the new airport if it doesn't exist
            airport = aircraft.getAirportId();
            airportService.addAirport(airport);
        }

        aircraft.setAirportId(airport); // Set the persisted airport on the book

        return aircraftService.addAircraft(aircraft);
    }

    // Get all aircraft
    @GetMapping("/listAllAircrafts")
    public Iterable<Aircraft> getAllAircraft() {
        return aircraftService.getAllAircraft();
    }

    // Get an aircraft by ID
    @GetMapping("/getAircraftById/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long aircraftId) {
        Optional<Aircraft> aircraft = aircraftService.getAircraftById(aircraftId);
        return aircraft.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an aircraft
    @PutMapping("/updateAircraftById/{aircraftId}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long aircraftId, @RequestBody Aircraft updatedAircraft) {
        Optional<Aircraft> aircraft = aircraftService.updateAircraft(aircraftId, updatedAircraft);
        return aircraft.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an aircraft
    @DeleteMapping("/deleteAircraftById/{aircraftId}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long aircraftId) {
        aircraftService.deleteAircraft(aircraftId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get passengers on an aircraft by aircraft ID
    @GetMapping("/getPassengersByAircraftId/{aircraftId}")
    public ResponseEntity<List<Passengers>> getPassengersByAircraft(@PathVariable Long aircraftId) {
        Optional<List<Passengers>> passengers = aircraftService.getPassengersByAircraft(aircraftId);
        return passengers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Assign an airport to an aircraft
    @PostMapping("/{aircraftId}/airports/{airportId}")
    public ResponseEntity<Aircraft> addAirportToAircraft(@PathVariable Long aircraftId, @PathVariable Long airportId) {
        Optional<Aircraft> aircraft = aircraftService.addAirportToAircraft(aircraftId, airportId);
        return aircraft.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

