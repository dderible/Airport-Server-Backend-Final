package com.plane.cities;

import com.plane.airport.Airport;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    private String cityName;
    private String country;

    // Relationships
    @OneToMany(cascade = CascadeType.ALL)
    private List<Airport> airports = new ArrayList<Airport>();
    private String population;

    public Cities() {

    }

    public Cities(String cityName, String country, List<Airport> airports, String population) {
        this.cityName = cityName;
        this.country = country;
        this.airports = airports;
        this.population = population;
    }

    // Getters & Setters
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City {" +
                "Cities Id:" + cityId +
                "City:" + cityName +
                "Country:" + country +
                "Airports" + airports +
                "Population" + population +
                "}";
    }
}
