package com.plane.passengers;

import com.plane.aircraft.Aircraft;
import com.plane.aircraft.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getAllPassengers")
    public Iterable<Passengers> getAllPassengers() {
        return passengersService.getAllPassengers();
    }

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

    @GetMapping("/findByPassengerID/{passengerID}")
    public ResponseEntity<Passengers> findByPassengerID(@PathVariable Long passengerID) {
        Optional<Passengers> passengers = passengersService.findByPassengerID(passengerID);
        return passengers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAircraftForPassenger")
    public Iterable<Passengers> getAircraftForPassenger(@RequestParam("ID") Long passengerID) {
        return passengersService.findByAircraftID(passengerID);
    }

    @PutMapping("/updatePassengerByID/{passengerID}")
    public ResponseEntity<Passengers> updatePassenger(@PathVariable Long passengerID,@RequestBody Passengers updatedPassenger) {
        Optional<Passengers> passengers = Optional.ofNullable(passengersService.updatePassenger(passengerID, updatedPassenger));
        return passengers.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deletePassengerByID/{passengerID}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long passengerID) {
        if(passengersService.deletePassenger(passengerID)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}

