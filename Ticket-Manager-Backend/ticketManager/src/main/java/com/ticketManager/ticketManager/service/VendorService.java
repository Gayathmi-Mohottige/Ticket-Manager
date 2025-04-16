package com.ticketManager.ticketManager.service;

import com.ticketManager.ticketManager.model.Vendor;
import com.ticketManager.ticketManager.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class VendorService {

    @Autowired
    private TicketPoolService ticketPoolService;

    @Autowired
    private VendorRepository vendorRepository;

    private boolean isRunning = false;
    private Random random;

    public VendorService() {
        this.random = new Random();
    }

    public void startVendor(int numberOfVendors, int ticketReleaseRate,int poolId) {
        isRunning = true;

        for(int i = 0; i < numberOfVendors; i++) {
            final int vendorId = i + 1;
            Vendor vendor = new Vendor("Vendor-" + vendorId, 0);  // Initialize with 0 tickets added
            vendorRepository.save(vendor);
            final int insertedId = vendor.getId();
            Runnable newVendor = new Runnable() {
                @Override
                public void run() {
                    while(isRunning) {
                        int requestedNumOfTickets = random.nextInt(10) + 1;
                        boolean success = ticketPoolService.addTicket("Vendor-" + vendorId, requestedNumOfTickets,poolId);

                        if (success) {
                            Vendor vendor = vendorRepository.findById(insertedId).orElseThrow(() ->
                                    new IllegalStateException("Vendor not found: Vendor-" + vendorId));
                            vendor.setTicketsAdded(vendor.getTicketsAdded() + requestedNumOfTickets);
                            vendorRepository.save(vendor);
                        }
                        if(!success) {
                            break;
                        }
                        try {
                            Thread.sleep(ticketReleaseRate);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            };

            Thread vendorThread = new Thread(newVendor);
            vendorThread.start();
        }
    }

    public void stopVendor() {
        isRunning = false;
    }
}
