package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitiesService {

    @Autowired
    private CitiesRepository citiesRepository;

    public Cities addCity(Cities cities) {
        return citiesRepository.save(cities);
    }

    public Iterable<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    public Iterable<Cities> findByAirport(String name) {
        return citiesRepository.findCityByAirports_Name(name);
    }

    public Optional<Cities> getCityById(Long cityId) {
        return citiesRepository.findById(cityId);
    }

    public Cities updateCity(Long cityId, Cities updatedCity) {
        return citiesRepository.findById(cityId).map(city -> {
            city.setCityName(updatedCity.getCityName());
            city.setCountry(updatedCity.getCountry());
            city.setAirports(updatedCity.getAirports());

            return citiesRepository.save(city);
        }).orElseThrow(() -> new RuntimeException("City not found with id " + cityId));
    }

    public boolean deleteCity(Long cityId) {
        if (citiesRepository.existsById(cityId)) {
            citiesRepository.deleteById(cityId);
        } else {
            throw new RuntimeException("City not found with id " + cityId);
        }
        return false;
    }

    public Cities findByCityName(String cityName) {
        return citiesRepository.findByCityName(cityName);

    }

}
