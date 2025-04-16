package com.ticketManager.ticketManager.service;

import com.ticketManager.ticketManager.dto.SimulationConfigDTO;
import com.ticketManager.ticketManager.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlThreadService {

    @Autowired
    private TicketPoolService ticketPoolService;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CustomerService customerService;

    private boolean isRunning = false;

    public synchronized void startSimulation(SimulationConfigDTO config) {
        if (isRunning) {
            throw new IllegalStateException("Simulation is already running.");
        }

        isRunning = true;

        int poolId = ticketPoolService.initializeTicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());

        vendorService.startVendor(config.getNumberOfVendors(), config.getTicketReleaseRate(),poolId);
        customerService.startCustomer(config.getNumberOfCustomers(), config.getCustomerRetrievalRate(),poolId);

        Runnable newControlThread = new Runnable() {
            @Override
            public void run() {
                try {
                    while (!ticketPoolService.isNoMoreTickets() && ticketPoolService.getTicketPoolSize() > 0 && isRunning) {
                        Thread.sleep(1000);
                    }
                    stopSimulation();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        Thread controlThread = new Thread(newControlThread);
        controlThread.start();
    }

    public synchronized void stopSimulation() {
        isRunning = false;
        ticketPoolService.stopSimulation();
        vendorService.stopVendor();
        customerService.stopCustomer();
    }

    public boolean isSimulationRunning() {
        return isRunning;
    }

    public int getTicketPoolSize() {
        return ticketPoolService.getTicketPoolSize();
    }

    public Iterable<Transaction> getTransactionHistory() {
        return ticketPoolService.getTransactionHistory();
    }
}