package com.plane.cities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {
    @Autowired
    private CitiesService citiesService;

    @GetMapping
    public List<Cities> getAllCities() {
        return citiesService.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cities> getCityById(@PathVariable Long id) {
        Cities city = citiesService.getCityById(id);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Cities> getCityByName(@PathVariable String name) {
        Cities city = citiesService.getCityByName(name);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Cities createCity(@RequestBody Cities city) {
        return citiesService.saveCity(city);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cities> updateCity(@PathVariable Long id, @RequestBody Cities cityDetails) {
        Cities updatedCity = citiesService.updateCity(id, cityDetails);
        return updatedCity != null ? ResponseEntity.ok(updatedCity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        if (citiesService.getCityById(id) != null) {
            citiesService.deleteCity(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

