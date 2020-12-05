package com.example.spring_security_app.service;

import com.example.spring_security_app.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket findById(Long id);
    List<Ticket> findAll();
    void updateTicket(Ticket ticket);
}
