package com.plane.passengers;

import com.plane.aircraft.Aircraft;
import jakarta.persistence.*;

@Entity
public class Passengers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;
    private String passengerName;
    private String passengerAddress;
    private String passengerPhone;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Aircraft aircraftId;

    public Passengers() {

    }

    public Long getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }
    public String getPassengerName() {
        return passengerName;
    }
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }
    public String getPassengerAddress() {
        return passengerAddress;
    }
    public void setPassengerAddress(String passengerAddress) {
        this.passengerAddress = passengerAddress;
    }
    public String getPassengerPhone() {
        return passengerPhone;
    }
    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public Aircraft getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Aircraft aircraftId) {
        this.aircraftId = aircraftId;
    }



    public String toString() {
        return "Passenger: " + passengerName +
                ", ID:" + passengerId +
                ", Address: " + passengerAddress +
                ", Phone: " + passengerPhone;
    }
}

