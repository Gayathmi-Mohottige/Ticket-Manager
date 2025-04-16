package com.ticketManager.ticketManager.service;

import com.ticketManager.ticketManager.repository.CustomerRepository;
import com.ticketManager.ticketManager.repository.TicketPoolRepository;
import com.ticketManager.ticketManager.repository.TransactionRepository;
import com.ticketManager.ticketManager.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    public void clearDatabase() {
        // Clear all repositories
        transactionRepository.deleteAll();
        customerRepository.deleteAll();
        vendorRepository.deleteAll();
        ticketPoolRepository.deleteAll();
    }
}

