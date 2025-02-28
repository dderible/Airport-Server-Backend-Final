package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CitiesController {
    @Autowired
    private CitiesService citiesService;

    @GetMapping("/listAllCities")
    public List<Cities> getAllCities() {
        return citiesService.getAllCities();
    }

    @GetMapping("/listCitiesById")
    public ResponseEntity<Cities> getCityById(@PathVariable Long cityId) {
        Cities city = citiesService.getCityById(cityId);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getCitiesByName")
    public ResponseEntity<Cities> getCityByName(@PathVariable String name) {
        Cities city = citiesService.getCityByName(name);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @PostMapping("/addCity")
    public Cities createCity(@RequestBody Cities city) {
        return citiesService.saveCity(city);
    }

    @PutMapping("/updateCity")
    public ResponseEntity<Cities> updateCity(@PathVariable Long cityId, @RequestBody Cities cityDetails) {
        Cities updatedCity = citiesService.updateCity(cityId, cityDetails);
        return updatedCity != null ? ResponseEntity.ok(updatedCity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCity")
    public ResponseEntity<Void> deleteCity(@PathVariable Long cityId) {
        if (citiesService.getCityById(cityId) != null) {
            citiesService.deleteCity(cityId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

