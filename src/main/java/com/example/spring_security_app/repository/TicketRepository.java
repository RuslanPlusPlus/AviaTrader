package com.example.spring_security_app.repository;

import com.example.spring_security_app.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
