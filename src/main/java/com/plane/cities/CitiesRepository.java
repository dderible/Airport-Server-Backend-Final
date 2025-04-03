package com.plane.cities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends CrudRepository<Cities, Long> {
    Iterable<Cities> findCityByAirports_Name(String airport_name);
    public Cities findByCityName(String cityName);
}