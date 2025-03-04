package com.plane.airport;

import com.plane.cities.Cities;
import com.plane.cities.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private CitiesService citiesService;

    @PostMapping("/addNewAirport")
    public Airport addNewAirport(@RequestBody Airport airport) {

        Optional<Cities> cityOptional = Optional.ofNullable(citiesService.findByCityName(airport.getCityName().getCityName()));

        Cities cities;
        if (cityOptional.isPresent()) {
            cities = cityOptional.get();
        } else {
            cities = airport.getCityName();
            citiesService.addCity(cities);
        }

        airport.setCityName(cities);

        return airportService.addAirport(airport);
    }

    @GetMapping("/listAllAirports")
    public ResponseEntity<Iterable<Airport>> getAllAirports() {
        airportService.getAllAirports();
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }

    @GetMapping("/getAirportById/{airportId}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        Optional<Airport> airport = airportService.getAirportById(airportId);
        return airport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateAirportById/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport updatedAirport) {
        Optional<Airport> airport = airportService.updateAirport(airportId, updatedAirport);
        return airport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteAirportById/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        if (airportService.deleteAirport(airportId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAirportsByCityId/{cityId}")
    public ResponseEntity<Iterable<Airport>> getAirportsByCity(@PathVariable Long cityId) {
        Iterable<Airport> airports = airportService.getAirportsByCityId(cityId);
        if (airports.iterator().hasNext()) {
            return ResponseEntity.ok(airports);
        }
        return ResponseEntity.notFound().build();
    }
}
