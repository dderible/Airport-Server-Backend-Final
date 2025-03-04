package com.plane.passengers;

import com.plane.aircraft.Aircraft;
import jakarta.persistence.*;

@Entity
public class Passengers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerID;
    private String passengerName;
    private String passengerAddress;
    private String passengerPhone;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Aircraft aircraftId;

    public Passengers() {

    }

    public Long getPassengerID() {
        return passengerID;
    }
    public void setPassengerID(Long passengerID) {
        this.passengerID = passengerID;
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
                ", ID:" + passengerID +
                ", Address: " + passengerAddress +
                ", Phone: " + passengerPhone;
    }
}

