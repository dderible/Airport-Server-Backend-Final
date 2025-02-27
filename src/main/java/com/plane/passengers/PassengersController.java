package com.plane.passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengersController {
    @Autowired
    private PassengersService passengersService;

    @GetMapping
    public List<Passengers> getAllPassengers() {
        return passengersService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passengers> getPassengerById(@PathVariable Long id) {
        Passengers passenger = passengersService.getPassengerById(id);
        return passenger != null ? ResponseEntity.ok(passenger) : ResponseEntity.notFound().build();
    }

    @GetMapping("/aircraft/{aircraftId}")
    public List<Passengers> getPassengersByAircraft(@PathVariable Long aircraftId) {
        return passengersService.getPassengersByAircraft(aircraftId);
    }

    @PostMapping
    public Passengers createPassenger(@RequestBody Passengers passenger) {
        return passengersService.savePassenger(passenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable Long id, @RequestBody Passengers passengerDetails) {
        Passengers updatedPassenger = passengersService.updatePassenger(id, passengerDetails);
        return updatedPassenger != null ? ResponseEntity.ok(updatedPassenger) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        if (passengersService.getPassengerById(id) != null) {
            passengersService.deletePassenger(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

