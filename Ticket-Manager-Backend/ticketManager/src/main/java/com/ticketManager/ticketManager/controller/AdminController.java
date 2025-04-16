package com.ticketManager.ticketManager.controller;

import com.ticketManager.ticketManager.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DatabaseService databaseService;

    @PostMapping("/clear-database")
    public String clearDatabase() {
        databaseService.clearDatabase();
        return "Database cleared successfully!";
    }
}

