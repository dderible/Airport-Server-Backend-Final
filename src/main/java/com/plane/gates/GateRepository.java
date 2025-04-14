package com.plane.gates;

import org.springframework.data.repository.CrudRepository;

public interface GateRepository extends CrudRepository<Gate, Long> {
    public Gate findByTerminal(String terminal);

    public Gate findByNumber(String number);
}
