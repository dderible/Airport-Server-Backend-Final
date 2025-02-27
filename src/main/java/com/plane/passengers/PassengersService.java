package com.plane.passengers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengersService {
    @Autowired
    private PassengersRepository passengersRepository;

    public List<Passengers> getAllPassengers() {
        return passengersRepository.findAll();
    }

    public Passengers getPassengerById(Long id) {
        return passengersRepository.findById(id).orElse(null);
    }

    public List<Passengers> getPassengersByAircraft(Long aircraftId) {
        return passengersRepository.findByAircraftId(aircraftId);
    }

    public Passengers savePassenger(Passengers passenger) {
        return passengersRepository.save(passenger);
    }

    public Passengers updatePassenger(Long id, Passengers passengerDetails) {
        Optional<Passengers> existingPassenger = passengersRepository.findById(id);
        if (existingPassenger.isPresent()) {
            Passengers passenger = existingPassenger.get();
            passenger.setName(passengerDetails.getName());
            passenger.setEmail(passengerDetails.getEmail());
            passenger.setAircraft(passengerDetails.getAircraft());
            return passengersRepository.save(passenger);
        }
        return null;
    }

    public void deletePassenger(Long id) {
        passengersRepository.deleteById(id);
    }
}
