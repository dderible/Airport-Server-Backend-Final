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

    public Optional<Passengers> findByPassengerId(Long passengerId) {
        return passengersRepository.findByPassengerId(passengerId);
    }

    public Iterable<Passengers> findByAircraftId(Long aircraftId) {
        return passengersRepository.findPassengerByAircraftId_aircraftId(aircraftId);
    }

    public Passengers updatePassenger(Long passengerId, Passengers updatedPassenger) {
        return passengersRepository.findByPassengerId(passengerId).map(passenger -> {
            passenger.setPassengerName(updatedPassenger.getPassengerName());
            passenger.setPassengerAddress(updatedPassenger.getPassengerAddress());
            passenger.setPassengerPhone(updatedPassenger.getPassengerPhone());
            passenger.setAircraftId(updatedPassenger.getAircraftId());

            return passengersRepository.save(passenger);
        }).orElseThrow(() -> new RuntimeException("Passenger not found with id " + passengerId));
    }

    public boolean deletePassenger(Long passengerId) {
        if(passengersRepository.existsById(passengerId)) {
            passengersRepository.deleteById(passengerId);
        } else {
            throw new RuntimeException("Passenger not found with id " + passengerId);
        }
        return false;
    }

}
