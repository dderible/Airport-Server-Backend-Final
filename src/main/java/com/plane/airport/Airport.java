package com.plane.airport;

import com.plane.cities.Cities;
import com.plane.aircraft.Aircraft;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airportId;

    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private Cities city;

    @OneToMany(mappedBy = "departureAirport", cascade = CascadeType.ALL)
    private List<Aircraft> departingAircrafts;

    @OneToMany(mappedBy = "arrivalAirport", cascade = CascadeType.ALL)
    private List<Aircraft> arrivingAircrafts;

    public Airport() {

    }

    public Airport(String name, String code, Cities city) {
        this.name = name;
        this.code = code;
        this.city = city;
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

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public List<Aircraft> getDepartingAircrafts() {
        return departingAircrafts;
    }

    public void setDepartingAircrafts(List<Aircraft> departingAircrafts) {
        this.departingAircrafts = departingAircrafts;
    }

    public List<Aircraft> getArrivingAircrafts() {
        return arrivingAircrafts;
    }

    public void setArrivingAircrafts(List<Aircraft> arrivingAircrafts) {
        this.arrivingAircrafts = arrivingAircrafts;
    }
}

