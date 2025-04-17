package com.plane.flights;

import com.plane.gates.Gate;
import com.plane.gates.GateRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;
    private final GateRepository gateRepository;

    public FlightController(FlightService flightService, GateRepository gateRepository) {
        this.flightService = flightService;
        this.gateRepository = gateRepository;
    }

    // Retrieve all flights
    @GetMapping("/get-all-flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        System.out.println("Fetching all flights");
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    // Retrieve flight by ID
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightId) {
        return ResponseEntity.ok(flightService.findByFlightId(flightId));
    }

    // Create a flight
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

    // Delete a flight
    @DeleteMapping("/{flightId}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Search for a flight by ID
    @GetMapping("/search/by-flight-id")
    public ResponseEntity<Flight> getByFlightId(@RequestParam Long flightId) {
        return ResponseEntity.ok(flightService.getByFlightId(flightId));
    }

    // Search for a flight by destination location
    @GetMapping("/search/by-flight-destination")
    public ResponseEntity<List<Flight>> getByFlightDestination(@RequestParam String flightDestination) {
        System.out.println("Searching flights by destination: " + flightDestination);
        return ResponseEntity.ok(flightService.getByFlightDestination(flightDestination));
    }

    // Search for a flight by origin location
    @GetMapping("/search/by-flight-origin")
    public ResponseEntity<List<Flight>> getByFlightOrigin(@RequestParam String flightOrigin) {
        System.out.println("Searching flights by origin: " + flightOrigin);
        return ResponseEntity.ok(flightService.getByFlightOrigin(flightOrigin));
    }

    // Search for a flight by it's airline
    @GetMapping("/search/by-flight-airline")
    public ResponseEntity<List<Flight>> getByFlightAirline(@RequestParam String flightAirline) {
        return ResponseEntity.ok(flightService.getByFlightAirline(flightAirline));
    }
}