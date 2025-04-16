package com.ticketManager.ticketManager.controller;

import com.ticketManager.ticketManager.model.Transaction;
import com.ticketManager.ticketManager.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class GraphController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/tickets/graph-data")
    public Map<String, Object> getGraphData() {
        List<Transaction> transactions = transactionRepository.findAll();

        List<String> timestamps = new ArrayList<>();
        List<Integer> ticketsReleased = new ArrayList<>();
        List<Integer> ticketsPurchased = new ArrayList<>();

        for (Transaction transaction : transactions) {
            timestamps.add(transaction.getId().toString()); // Use transaction ID as timestamp
            if ("ADD".equals(transaction.getTransactionType())) {
                ticketsReleased.add(transaction.getTicketCount());
            } else if ("RETRIEVE".equals(transaction.getTransactionType())) {
                ticketsPurchased.add(transaction.getTicketCount());
            }
        }

        Map<String, Object> data = new HashMap<>();
        data.put("timestamps", timestamps);
        data.put("ticketsReleased", ticketsReleased);
        data.put("ticketsPurchased", ticketsPurchased);

        return data;
    }
}
