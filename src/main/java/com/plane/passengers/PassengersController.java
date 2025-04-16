package com.plane.passengers;

import com.plane.aircraft.Aircraft;
import com.plane.aircraft.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@CrossOrigin
public class PassengersController {

    @Autowired
    private PassengersService passengersService;

    @Autowired
    private AircraftService aircraftService;

    // Retrieve all passengers
    @GetMapping("/getAllPassengers")
    public Iterable<Passengers> getAllPassengers() {
        return passengersService.getAllPassengers();
    }

    // Create a passenger
    @PostMapping("/addNewPassenger")
    public Passengers addNewPassenger(@RequestBody Passengers passenger) {
        Optional<Aircraft> aircraftOptional = Optional.ofNullable(aircraftService.findByAircraftId(passenger.getAircraftId().getAircraftId()));

        Aircraft aircraft;
        if (aircraftOptional.isPresent()) {
            aircraft = aircraftOptional.get();
        } else {
            // Save the new aircraft if it doesn't exist
            aircraft = passenger.getAircraftId();
            aircraftService.addAircraft(aircraft);
        }

        passenger.setAircraftId(aircraft); // Set the persisted aircraft on the book
        return passengersService.addPassenger(passenger);
    }

    // Find a passenger by ID
    @GetMapping("/findByPassengerID/{passengerId}")
    public ResponseEntity<Passengers> findByPassengerId(@PathVariable Long passengerId) {
        Optional<Passengers> passengers = passengersService.findByPassengerId(passengerId);
        return passengers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Retrieve an aircraft for a passenger
    @GetMapping("/getAircraftForPassenger")
    public Iterable<Passengers> getAircraftForPassenger(@RequestParam("ID") Long passengerId) {
        return passengersService.findByAircraftId(passengerId);
    }

    // Update a passenger
    @PutMapping("/updatePassengerById/{passengerId}")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable Long passengerId,@RequestBody Passengers updatedPassenger) {
        Optional<Passengers> passengers = Optional.ofNullable(passengersService.updatePassenger(passengerId, updatedPassenger));
        return passengers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a passenger
    @DeleteMapping("/deletePassengerById/{passengerId}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerId) {
        passengersService.deletePassenger(passengerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

