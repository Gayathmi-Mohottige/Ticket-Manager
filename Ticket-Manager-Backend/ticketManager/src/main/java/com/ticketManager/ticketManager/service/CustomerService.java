package com.ticketManager.ticketManager.service;

import com.ticketManager.ticketManager.model.Customer;
import com.ticketManager.ticketManager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class CustomerService {

    @Autowired
    private TicketPoolService ticketPoolService;

    @Autowired
    private CustomerRepository customerRepository;

    private boolean isRunning = false;
    private Random random;

    public CustomerService() {
        this.random = new Random();
    }

    public void startCustomer (int numberOfCustomers, int customerRetrievalRate,int poolId) {
        isRunning = true;

        for (int i = 0; i < numberOfCustomers; i++) {
            final int customerId = i + 1;
            Customer customer = new Customer("Customer-" + customerId, 0);
            customerRepository.save(customer);
            final int insertedId = customer.getId();

            Runnable newCustomer = new Runnable () {
                @Override
                public void run() {
                    while (isRunning) {
                        int requestedNumOfTickets = random.nextInt(10) + 1;
                        ticketPoolService.removeTickets("Customer-" + customerId,requestedNumOfTickets,poolId);

                        Customer customer = customerRepository.findById(insertedId).orElseThrow(() ->
                                new IllegalStateException("Customer not found: Customer-" + customerId));
                        customer.setTicketsPurchased(customer.getTicketsPurchased() + requestedNumOfTickets);
                        customerRepository.save(customer);

                        try {
                            Thread.sleep(customerRetrievalRate);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            };

            Thread customerThread = new Thread(newCustomer);
            customerThread.start();
        }
    }

    public void stopCustomer () {
        isRunning = false;
    }
}
