package com.ticketManager.ticketManager.controller;

import com.ticketManager.ticketManager.dto.SimulationConfigDTO;
import com.ticketManager.ticketManager.service.ControlThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    @Autowired
    private ControlThreadService controlThreadService;

    @PostMapping ("/start")
    public ResponseEntity<String> startSimulation(@RequestBody SimulationConfigDTO configDTO) {
        controlThreadService.startSimulation(configDTO);
        return ResponseEntity.ok("Simulation started.");
    }

    @PostMapping ("/stop")
    public ResponseEntity<String> stopSimulation() {
        controlThreadService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped.");
    }

    @GetMapping("/ticket-pool")
    public ResponseEntity<Integer> getTicketPoolSize() {
        return ResponseEntity.ok(controlThreadService.getTicketPoolSize());
    }

    @GetMapping("/transactions")
    public ResponseEntity<?> getTransactionHistory() {
        return ResponseEntity.ok(controlThreadService.getTransactionHistory());
    }
}
