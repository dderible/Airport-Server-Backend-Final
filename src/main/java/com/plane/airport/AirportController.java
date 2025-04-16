package com.plane.airport;

import com.plane.cities.Cities;
import com.plane.cities.CitiesService;
import com.plane.flights.Flight;
import com.plane.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    @Autowired
    private CitiesService citiesService;
    @Autowired
    private FlightService flightService;

    // Create an airport
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

    // Retrieve a list of all airports
    @GetMapping("/listAllAirports")
    public ResponseEntity<Iterable<Airport>> getAllAirports() {
        airportService.getAllAirports();
        return ResponseEntity.ok().body(airportService.getAllAirports());
    }

    // Search airports by ID
    @GetMapping("/getAirportById/{airportId}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long airportId) {
        Optional<Airport> airport = airportService.getAirportById(airportId);
        return airport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Search airports by city ID
    @GetMapping("/getAirportsByCityId/{cityId}")
    public ResponseEntity<Iterable<Airport>> getAirportsByCity(@PathVariable Long cityId) {
        Iterable<Airport> airports = airportService.getAirportsByCityId(cityId);
        if (airports.iterator().hasNext()) {
            return ResponseEntity.ok(airports);
        }
        return ResponseEntity.notFound().build();
    }

    // Update an airport
    @PutMapping("/updateAirportById/{airportId}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long airportId, @RequestBody Airport updatedAirport) {
        Optional<Airport> airport = airportService.updateAirport(airportId, updatedAirport);
        return airport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an airport
    @DeleteMapping("/deleteAirportById/{airportId}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long airportId) {
        if (airportService.deleteAirport(airportId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Add an airport to a flight
    @PostMapping("/{airportId}/add-flight-from-airport/{flightId}")
    public ResponseEntity<String> addFlight(@PathVariable Long airportId, @PathVariable Long flightId) {
        Airport airport = airportService.findByAirportId(airportId);
        Flight flight = flightService.findByFlightId(flightId);

        if (airport == null || flight == null) {
            return new ResponseEntity<>("ERROR: Airport or Flight not found.", HttpStatus.NOT_FOUND);
        }

        if (airport.getFlightList().contains(flight)) {
            return new ResponseEntity<>("ERROR: Flight already exists in Flight List.", HttpStatus.CONFLICT);
        }

        airport.getFlightList().add(flight);
        airportService.addAirport(airport);

        return new ResponseEntity<>("Successfully added Flight to Airline's List!", HttpStatus.CREATED);
    }

    // Retrieve a list of airport's arrivals
    @GetMapping("/airport-arrivals")
    public ResponseEntity<List<Flight>> getAirportArrivals(String flightOrigin) {
        List<Flight> arrivals = flightService.getByFlightOrigin(flightOrigin);
        return ResponseEntity.ok(arrivals);
    }

    // Retrieve a list of airport's departures
    @GetMapping("/airport-departures")
    public ResponseEntity<List<Flight>> getAirportDepartures(String flightDestination) {
        List<Flight> departures = flightService.getByFlightDestination(flightDestination);
        return ResponseEntity.ok(departures);
    }
}
