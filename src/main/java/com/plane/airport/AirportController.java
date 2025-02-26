package com.plane.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Airport airport = airportService.getAirportById(id);
        return airport != null ? ResponseEntity.ok(airport) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airportDetails) {
        Airport updatedAirport = airportService.updateAirport(id, airportDetails);
        return updatedAirport != null ? ResponseEntity.ok(updatedAirport) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        if (airportService.getAirportById(id) != null) {
            airportService.deleteAirport(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
