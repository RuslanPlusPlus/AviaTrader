package com.example.spring_security_app.web;

import com.example.spring_security_app.model.Ticket;
import com.example.spring_security_app.service.TicketService;
import com.example.spring_security_app.web.dto.TicketDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final TicketService ticketService;
    private TicketDto requestedTicket;

    public MainController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("ticket", new TicketDto());
        return "index";
    }

    @PostMapping("/")
    public String searchTicket(@ModelAttribute("ticket") TicketDto ticketDto){
        this.requestedTicket = ticketDto;
        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String orderTicket( Model model){
        List<Ticket> tickets = this.ticketService.findAll();
        List<TicketDto> currentList = new ArrayList<>();
        List<TicketDto> ticketsToDisplay = null;
        if(tickets != null){
            for(Ticket ticket: tickets){
                if (ticket.isSuitable(this.requestedTicket)){
                    currentList.add(new TicketDto(ticket));
                }
            }
            if (currentList.size() > 0){
                ticketsToDisplay = new ArrayList<>(currentList);
            }

        }
        model.addAttribute("tr", ticketsToDisplay);
        return "tickets";
    }

    /*@GetMapping("/order")
    public String makeOrder(){
        return "redirect:tickets";
    }*/

}
/*List<Ticket> tickets = this.ticketService.findAll();
        List<TicketDto> ticketDtoList = null;
        if (tickets != null){
            ticketDtoList = new ArrayList<>();
            for (Ticket ticket: tickets){
                if (ticket.getIsFree().equals("FREE")){
                    ticketDtoList.add(new TicketDto(ticket));
                }
            }
        }
        model.addAttribute("tickets", ticketDtoList);*/
