package com.plane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RestServiceApplicationTests {
    // Test #1
    @Test
    void testAddNewAircraft() {
        Aircraft newAircraft = new Aircraft("A123", "Boeing 737", 189);
        addNewAircraft(newAircraft);

        assertEquals(1, aircraftRegistry.size());
        assertTrue(aircraftRegistry.contains(newAircraft));
    }

    private List<Aircraft> aircraftRegistry;

    @BeforeEach
    void setUp() {
        aircraftRegistry = new ArrayList<>();
    }

    void addNewAircraft(Aircraft aircraft) {
        aircraftRegistry.add(aircraft);
    }

    static class Aircraft {
        private String id;
        private String model;
        private int capacity;

        Aircraft(String id, String model, int capacity) {
            this.id = id;
            this.model = model;
            this.capacity = capacity;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Aircraft)) {
                return false;
            } else {
                Aircraft other = (Aircraft) obj;
                return this.id.equals(other.id);
            }
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }



    // Test #2
    @Test
    void testAddNewAirport() {
        Airport newAirport = new Airport("JFK", "John F. Kennedy International Airport", "New York");
        addNewAirport(newAirport);

        assertEquals(1, airportRegistry.size());
        assertTrue(airportRegistry.contains(newAirport));
    }

    private List<Airport> airportRegistry;

    @BeforeEach
    void setUp2() {
        airportRegistry = new ArrayList<>();
    }

    void addNewAirport(Airport airport) {
        airportRegistry.add(airport);
    }
    
    static class Airport {
        private String code;
        private String name;
        private String city;

        Airport(String code, String name, String city) {
            this.code = code;
            this.name = name;
            this.city = city;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Airport)) {
                return false;
            } else {
                Airport other = (Airport) obj;
                return this.code.equals(other.code);
            }
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }
    }



    // Test #3
    @Test
    void testAddNewCity() {
        City newCity = new City("New York", "USA", 8419600);
        addNewCity(newCity);

        assertEquals(1, cityRegistry.size());
        assertTrue(cityRegistry.contains(newCity));
    }

    private List<City> cityRegistry;

    @BeforeEach
    void setUp3() {
        cityRegistry = new ArrayList<>();
    }

    void addNewCity(City city) {
        cityRegistry.add(city);
    }

    static class City {
        private String name;
        private String country;
        private int population;

        City(String name, String country, int population) {
            this.name = name;
            this.country = country;
            this.population = population;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof City)) {
                return false;
            } else {
                City other = (City) obj;
                return this.name.equals(other.name) && this.country.equals(other.country);
            }
        }

        @Override
        public int hashCode() {
            return name.hashCode() + country.hashCode();
        }
    }
}
