package com.ticketManager.ticketManager.controller;

import com.ticketManager.ticketManager.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/api")
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/logs")
    public List<String> getLogs() {
        return new ArrayList<>(logService.getLogs());
    }
}

