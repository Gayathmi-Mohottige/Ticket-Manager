package com.ticketManager.ticketManager.service;

import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class LogService {
    private final Queue<String> logs = new ConcurrentLinkedQueue<>();

    public void addLog(String log) {
        logs.add(log);
        if (logs.size() > 100) { // Limit log size to avoid memory overflow
            logs.poll();
        }
    }

    public Queue<String> getLogs() {
        return logs;
    }

    public void clearLogs() {
        logs.clear();
    }
}
