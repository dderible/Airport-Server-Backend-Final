package com.plane.airlines;

import com.plane.flights.Flight;
import com.plane.flights.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;
    private final FlightService flightService;

    public AirlineController(AirlineService airlineService, FlightService flightService) {
        this.airlineService = airlineService;
        this.flightService = flightService;
    }

    @GetMapping("/get-all-airlines")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/{airlineId}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Long airlineId) {
        return ResponseEntity.ok(airlineService.findByAirlineId(airlineId));
    }

    @PostMapping("/create-airline")
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        Airline newAirline = airlineService.createAirline(airline);
        return new ResponseEntity<>(newAirline, HttpStatus.CREATED);
    }

    @DeleteMapping("/{airlineId}")
    public ResponseEntity<Void> deleteAirline(@PathVariable Long airlineId) {
        airlineService.deleteAirline(airlineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/by-airline-name")
    public ResponseEntity<List<Airline>> getByAirlineName(@RequestParam String airlineName) {
        return ResponseEntity.ok(airlineService.getByAirlineName(airlineName));
    }

    @GetMapping("/search/by-airline-id")
    public ResponseEntity<List<Airline>> getByAirlineId(@RequestParam Long airlineId) {
        return ResponseEntity.ok(airlineService.getByAirlineId(airlineId));
    }

    @PostMapping("/{airlineId}/add-flight/{flightId}")
    public ResponseEntity<String> addFlight(@PathVariable Long airlineId, @PathVariable Long flightId) {
        Airline airline = airlineService.findByAirlineId(airlineId);
        Flight flight = flightService.findByFlightId(flightId);

        if (airline == null || flight == null) {
            return new ResponseEntity<>("ERROR: Airline or Flight not found.", HttpStatus.NOT_FOUND);
        }

        if (airline.getFlightList().contains(flight)) {
            return new ResponseEntity<>("ERROR: Flight already exists in Flight List.", HttpStatus.CONFLICT);
        }

        airline.getFlightList().add(flight);
        airlineService.createAirline(airline);

        return new ResponseEntity<>("Successfully added Flight to Airline's List", HttpStatus.CREATED);
    }

    @GetMapping("/getAllFlightsForAirlineById/{airlineId}")
    public Iterable<Flight> getAllFlightsForAirlineById(@PathVariable Long airlineId) {
        return airlineService.listFlightsByAirlineId(airlineId);
    }
}