package com.plane.passengers;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengersRepository extends CrudRepository<Passengers, Long> {
    Iterable<Passengers> findPassengerByAircraftId_aircraftId(Long aircraftId);
    Optional<Passengers> findByPassengerId(Long passengerId);
}
