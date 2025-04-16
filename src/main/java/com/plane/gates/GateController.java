package com.plane.gates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class GateController {
    @Autowired
    private GateService gateService;

    // Create a gate
    @PostMapping("/addGate")
    public Gate addGate(@RequestBody Gate gate) {
        return gateService.addGate(gate);
    }

    // Delete a gate
    @DeleteMapping("/deleteGateById/{gateId}")
    public ResponseEntity<Void> deleteGate(@PathVariable Long gateId) {
        gateService.deleteGateById(gateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update a gate
    @PutMapping("/updateGate/{gateId}")
    public ResponseEntity<Gate> updateGate(@PathVariable Long gateId, @RequestBody Gate gate) {
        return ResponseEntity.ok(gateService.updateGate(gateId, gate));
    }

    // Search for gates by ID
    @GetMapping("/getGateById/{gateId}")
    public Gate getGateById(@PathVariable Long gateId) {
        return gateService.getGateById(gateId);
    }

    // Search for gates by terminal
    @GetMapping("/getGateByTerminal")
    public Gate getGateByTerminal(@RequestParam("terminal") String terminal) {
        return gateService.getGateByTerminal(terminal);
    }

    // Search for gates by gate number
    @GetMapping("/getGateByNumber")
    public Gate getGateByNumber(@RequestParam("number") String number) {
        return gateService.getGateByNumber(number);
    }

    // Retrieve all gates
    @GetMapping("/getAllGates")
    public List<Gate> getAllGates() {
        return gateService.getAllGates();
    }
}
