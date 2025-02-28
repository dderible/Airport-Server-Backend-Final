package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    public Cities getCityById(Long cityId) {
        return citiesRepository.findById(cityId).orElse(null);
    }

    public Cities getCityByName(String name) {
        return citiesRepository.findByName(name);
    }

    public Cities saveCity(Cities city) {
        return citiesRepository.save(city);
    }

    public Cities updateCity(Long cityId, Cities cityDetails) {
        Optional<Cities> existingCity = citiesRepository.findById(cityId);
        if (existingCity.isPresent()) {
            Cities city = existingCity.get();
            city.setName(cityDetails.getName());
            city.setCountry(cityDetails.getCountry());
            return citiesRepository.save(city);
        }
        return null;
    }

    public void deleteCity(Long cityId) {
        citiesRepository.deleteById(cityId);
    }
}

