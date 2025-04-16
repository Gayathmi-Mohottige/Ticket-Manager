package com.ticketManager.ticketManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String vendorName;
    private int ticketsAdded;

    public Vendor(String vendorName, int ticketsAdded) {
        this.vendorName = vendorName;
        this.ticketsAdded = ticketsAdded;
    }

    public Vendor() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getTicketsAdded() {
        return ticketsAdded;
    }

    public void setTicketsAdded(int ticketsAdded) {
        this.ticketsAdded = ticketsAdded;
    }
}
