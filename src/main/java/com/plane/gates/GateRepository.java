package com.plane.gates;

import org.springframework.data.repository.CrudRepository;

public interface GateRepository extends CrudRepository<Gate, Long> {
    Gate findByTerminal(String terminal);
    Gate findByNumber(String number);
}
