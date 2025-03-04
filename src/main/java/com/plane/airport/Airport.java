package com.plane.airport;

import com.plane.cities.Cities;
import com.plane.aircraft.Aircraft;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    private String name;
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cities cityName;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Aircraft> aircrafts = new ArrayList<>();

    public Airport() {}

    public Airport(String name, String code, Cities cityName) {
        this.name = name;
        this.code = code;
        this.cityName= cityName;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Cities getCityName() {
        return cityName;
    }

    public void setCityName(Cities cityName) {
        this.cityName = cityName;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

}

