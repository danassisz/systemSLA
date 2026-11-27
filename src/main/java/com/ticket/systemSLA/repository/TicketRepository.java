package com.ticket.systemSLA.repository;

import com.ticket.systemSLA.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}