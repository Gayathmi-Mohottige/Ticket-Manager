package com.ticketManager.ticketManager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transactionType;
    private String name;
    private int ticketCount;
    private int currentPoolSize;

    public Transaction(String transactionType, String name, int ticketCount, int currentPoolSize) {
        this.transactionType = transactionType;
        this.name = name;
        this.ticketCount = ticketCount;
        this.currentPoolSize = currentPoolSize;
    }

    public Transaction() {}

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public int getCurrentPoolSize() {
        return currentPoolSize;
    }

    public void setCurrentPoolSize(int currentPoolSize) {
        this.currentPoolSize = currentPoolSize;
    }
}
