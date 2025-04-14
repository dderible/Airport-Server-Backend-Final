package com.plane.gates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GateService {
    @Autowired
    private GateRepository gateRepository;

    public Gate addGate(Gate newGate) {
        return gateRepository.save(newGate);
    }

    public void deleteGateById(long gateId) {
        gateRepository.deleteById(gateId);
    }

    public Gate updateGate(long gateId, Gate updatedGate) {
        Optional<Gate> gateToUpdateOptional = gateRepository.findById(gateId);

        if (gateToUpdateOptional.isPresent()) {
            Gate gateToUpdate = gateToUpdateOptional.get();

            gateToUpdate.setTerminal(updatedGate.getTerminal());
            gateToUpdate.setNumber(updatedGate.getNumber());

            return gateRepository.save(gateToUpdate);
        }

        return null;
    }

    public Gate getGateById(long gateId) {
        Optional<Gate> gateOptional = gateRepository.findById(gateId);

        return gateOptional.orElse(null);
    }

    public Gate getGateByTerminal(String terminal) {
        return gateRepository.findByTerminal(terminal);
    }

    public Gate getGateByNumber(String number) {
        return gateRepository.findByNumber(number);
    }

    public List<Gate> getAllGates() {
        return (List<Gate>) gateRepository.findAll();
    }
}
