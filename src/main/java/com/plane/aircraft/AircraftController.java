package com.plane.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable Long id) {
        Aircraft aircraft = aircraftService.getAircraftById(id);
        return aircraft != null ? ResponseEntity.ok(aircraft) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Aircraft createAircraft(@RequestBody Aircraft aircraft) {
        return aircraftService.saveAircraft(aircraft);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable Long id, @RequestBody Aircraft aircraftDetails) {
        Aircraft updatedAircraft = aircraftService.updateAircraft(id, aircraftDetails);
        return updatedAircraft != null ? ResponseEntity.ok(updatedAircraft) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable Long id) {
        if (aircraftService.getAircraftById(id) != null) {
            aircraftService.deleteAircraft(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
