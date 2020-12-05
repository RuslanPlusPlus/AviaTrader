package com.example.spring_security_app.service;

import com.example.spring_security_app.model.Ticket;
import com.example.spring_security_app.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket findById(Long id) {
        return this.ticketRepository.getOne(id);
    }

    @Override
    public List<Ticket> findAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public void updateTicket(Ticket ticket) {
        this.ticketRepository.save(ticket);
    }
}
