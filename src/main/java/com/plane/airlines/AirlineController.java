package com.plane.airlines;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
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
}