package com.plane.flights;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{flightid}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long flightid) {
        return ResponseEntity.ok(flightService.findByFlightId(flightid));
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight NewFlight = flightService.createFlight(flight);
        return new ResponseEntity<>(NewFlight, HttpStatus.CREATED);
    }

    @DeleteMapping("/{flightid}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long flightid) {
        flightService.deleteFlight(flightid);
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