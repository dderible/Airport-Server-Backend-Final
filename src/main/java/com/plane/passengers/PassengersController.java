package com.plane.passengers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PassengersController {
    @Autowired
    private PassengersService passengersService;

    @GetMapping("/ListAllPassengers")
    public List<Passengers> getAllPassengers() {
        return passengersService.getAllPassengers();
    }

    @GetMapping("/listPassengerById")
    public ResponseEntity<Passengers> getPassengerById(@PathVariable Long passengerId) {
        Passengers passenger = passengersService.getPassengerById(passengerId);
        return passenger != null ? ResponseEntity.ok(passenger) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getPassengerByAircraft")
    public List<Passengers> getPassengersByAircraft(@PathVariable Long aircraftId) {
        return passengersService.getPassengersByAircraft(aircraftId);
    }

    @PostMapping("/addPassenger")
    public Passengers createPassenger(@RequestBody Passengers passenger) {
        return passengersService.savePassenger(passenger);
    }

    @PutMapping("/updatePassenger")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable Long passengerId, @RequestBody Passengers passengerDetails) {
        Passengers updatedPassenger = passengersService.updatePassenger(passengerId, passengerDetails);
        return updatedPassenger != null ? ResponseEntity.ok(updatedPassenger) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletePassenger")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        if (passengersService.getPassengerById(passengerId) != null) {
            passengersService.deletePassenger(passengerId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

