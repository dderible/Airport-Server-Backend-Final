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

    @GetMapping("/listCitiesById/{id}")
    public ResponseEntity<Cities> getCityById(@PathVariable("id") Long id) {
        Cities city = citiesService.getCityById(id);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getCitiesByName/{name}")
    public ResponseEntity<Cities> getCityByName(@PathVariable("name") String name) {
        Cities city = citiesService.getCityByName(name);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @PostMapping("/addCity")
    public Cities createCity(@RequestBody Cities city) {
        return citiesService.saveCity(city);
    }

    @PutMapping("/updateCity/{id}")
    public ResponseEntity<Cities> updateCity(@PathVariable("id") Long id, @RequestBody Cities cityDetails) {
        Cities updatedCity = citiesService.updateCity(id, cityDetails);
        return updatedCity != null ? ResponseEntity.ok(updatedCity) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable("id") Long id) {
        if (citiesService.getCityById(id) != null) {
            citiesService.deleteCity(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
