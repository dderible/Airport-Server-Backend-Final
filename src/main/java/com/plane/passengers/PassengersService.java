package com.plane.passengers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengersService {

    @Autowired
    private PassengersRepository passengersRepository;

    public Passengers addPassenger(Passengers passengers) {
        return passengersRepository.save(passengers);
    }

    public Iterable<Passengers> getAllPassengers() {
        return passengersRepository.findAll();
    }

    public Optional<Passengers> findByPassengerID(Long passengerID) {
        return passengersRepository.findByPassengerID(passengerID);
    }

    public Iterable<Passengers> findByAircraftID(Long aircraftID) {
        return passengersRepository.findPassengerByAircraftId_aircraftId(aircraftID);
    }

    public Passengers updatePassenger(Long passengerID, Passengers updatedPassenger) {
        return passengersRepository.findByPassengerID(passengerID).map(passenger -> {
            passenger.setPassengerName(updatedPassenger.getPassengerName());
            passenger.setPassengerAddress(updatedPassenger.getPassengerAddress());
            passenger.setPassengerPhone(updatedPassenger.getPassengerPhone());
            passenger.setAircraftId(updatedPassenger.getAircraftId());

            return passengersRepository.save(passenger);
        }).orElseThrow(() -> new RuntimeException("Passenger not found with id " + passengerID));
    }

    public boolean deletePassenger(Long passengerID) {
        if(passengersRepository.existsById(passengerID)) {
            passengersRepository.deleteById(passengerID);
        } else {
            throw new RuntimeException("Passenger not found with id " + passengerID);
        }
        return false;
    }

}
