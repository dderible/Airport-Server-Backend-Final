package com.plane.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/listAllAirports")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/getAirportById")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        Airport airport = airportService.getAirportById(airportId);
        return airport != null ? ResponseEntity.ok(airport) : ResponseEntity.notFound().build();
    }

    @PostMapping("/createAirport")
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @PutMapping("/updateAirport")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport airportDetails) {
        Airport updatedAirport = airportService.updateAirport(airportId, airportDetails);
        return updatedAirport != null ? ResponseEntity.ok(updatedAirport) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAirport")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        if (airportService.getAirportById(airportId) != null) {
            airportService.deleteAirport(airportId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
