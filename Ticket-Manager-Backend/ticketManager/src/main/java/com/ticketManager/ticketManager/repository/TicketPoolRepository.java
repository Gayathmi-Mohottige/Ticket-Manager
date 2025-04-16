package com.ticketManager.ticketManager.repository;

import com.ticketManager.ticketManager.model.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPoolRepository extends JpaRepository<TicketPool, Integer> {
}
