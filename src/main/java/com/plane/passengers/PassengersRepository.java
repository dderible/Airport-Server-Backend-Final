package com.plane.passengers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengersRepository extends JpaRepository<Passengers, Long> {
    List<Passengers> findByAircraftId(Long aircraftId);
}
