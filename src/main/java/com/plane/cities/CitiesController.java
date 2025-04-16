package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    // Create a city
    @PostMapping("/addNewCity")
    public Cities addNewCity(@RequestBody Cities cities) {
        return citiesService.addCity(cities);
    }

    // List all cities
    @GetMapping("/listAllCities")
    public ResponseEntity<Iterable<Cities>> getAllCities() {
        citiesService.getAllCities();
        return ResponseEntity.ok().body(citiesService.getAllCities());
    }

    // Retrieve a city by ID
    @GetMapping("/getCityById/{cityId}")
    public ResponseEntity<Cities> getCityById(@PathVariable Long cityId) {
        Optional<Cities> cities = citiesService.getCityById(cityId);
        return cities.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a city by ID
    @PutMapping("/updateCity/{cityId}")
    public ResponseEntity<Cities> updateCity(@PathVariable("cityId") Long cityId, @RequestBody Cities cityDetails) {
        Cities updatedCity = citiesService.updateCity(cityId, cityDetails);
        return updatedCity != null ? ResponseEntity.ok(updatedCity) : ResponseEntity.notFound().build();
    }

    // Delete a city by ID
    @DeleteMapping("/deleteCityById/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        citiesService.deleteCity(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
