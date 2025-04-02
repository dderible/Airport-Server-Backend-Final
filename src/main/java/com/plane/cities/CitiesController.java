package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@CrossOrigin
public class CitiesController {

    @Autowired
    private CitiesService citiesService;

    @PostMapping("/addNewCity")
    public Cities addNewCity(@RequestBody Cities cities) {
        return citiesService.addCity(cities);
    }

    @GetMapping("/listAllCities")
    public ResponseEntity<Iterable<Cities>> getAllCities() {
        citiesService.getAllCities();
        return ResponseEntity.ok().body(citiesService.getAllCities());
    }

    @GetMapping("/getCityById/{cityId}")
    public ResponseEntity<Cities> getCityById(@PathVariable Long cityId) {
        Optional<Cities> cities = citiesService.getCityById(cityId);
        return cities.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateCity/{cityId}")
    public ResponseEntity<Cities> updateCity(@PathVariable("cityId") Long cityId, @RequestBody Cities cityDetails) {
        Cities updatedCity = citiesService.updateCity(cityId, cityDetails);
        return updatedCity != null ? ResponseEntity.ok(updatedCity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCityById/{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        if (citiesService.deleteCity(cityId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
