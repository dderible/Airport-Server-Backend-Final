package com.plane.flights;

// ADD: import com.plane.aircraft.AircraftRepository;
import com.plane.gates.Gate;
import com.plane.gates.GateRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    private final GateRepository gateRepository;

    // ADD: private final AircraftRepository aircraftRepository;

    public FlightController(FlightService flightService, GateRepository gateRepository) { // AircraftRepository aircraftRepository) {
        this.flightService = flightService;
        this.gateRepository = gateRepository;
        // ADD: this.aircraftRepository = aircraftRepository;
    }

    @GetMapping("/get-all-flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.findByFlightId(flightId));
    }

    @PostMapping("/create-flight")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        try {
            Gate airlineGate = gateRepository.findByTerminal(flight.gate.getTerminal());
            if (airlineGate == null) {
                gateRepository.save(flight.getGate());
            }

            Flight NewFlight = flightService.createFlight(flight);
            return new ResponseEntity<>(NewFlight, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/by-flight-id")
    public ResponseEntity<List<Flight>> getByFlightId(@RequestParam Long flightId) {
        return ResponseEntity.ok(flightService.getByFlightId(flightId));
    }

    @GetMapping("/search/by-flight-destination")
    public ResponseEntity<List<Flight>> getByFlightDestination(@RequestParam String flightDestination) {
        return ResponseEntity.ok(flightService.getByFlightDestination(flightDestination));
    }

    @GetMapping("/search/by-flight-origin")
    public ResponseEntity<List<Flight>> getByFlightOrigin(@RequestParam String flightOrigin) {
        return ResponseEntity.ok(flightService.getByFlightOrigin(flightOrigin));
    }

    @GetMapping("/search/by-flight-airline")
    public ResponseEntity<List<Flight>> getByFlightAirline(@RequestParam String flightAirline) {
        return ResponseEntity.ok(flightService.getByFlightAirline(flightAirline));
    }
}