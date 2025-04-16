package com.plane.gates;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gateId;

    private String terminal;
    private String number;

    public Gate() {

    }

    public Gate(String terminal, String number) {
        this.terminal = terminal;
        this.number = number;
    }

    // Getters & Setters
    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "gateId=" + gateId +
                ", terminal='" + terminal + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
