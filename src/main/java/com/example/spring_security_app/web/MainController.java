package com.example.spring_security_app.web;

import com.example.spring_security_app.model.Ticket;
import com.example.spring_security_app.model.User;
import com.example.spring_security_app.service.TicketService;
import com.example.spring_security_app.service.UserService;
import com.example.spring_security_app.web.dto.TicketDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final TicketService ticketService;
    private TicketDto requestedTicket;
    private final UserService userService;

    public MainController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
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

    @GetMapping("/order/{id}")
    public String makeOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        User user = this.userService.findUserByName(userDetails.getUsername());
        Ticket ticket = this.ticketService.findById(id);
        user.addTicket(ticket);
        this.ticketService.updateTicket(ticket);
        this.userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping("/account")
    public String getAccount(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User user = this.userService.findUserByName(userDetails.getUsername());
        List<Ticket> tickets = user.getTickets();
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket: tickets){
            ticketDtos.add(new TicketDto(ticket));
        }
        model.addAttribute("tr", ticketDtos);
        return "account";
    }

    @GetMapping("/cancel-order/{id}")
    public String cancelOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        System.out.println("cancel-order method");
        User user = this.userService.findUserByName(userDetails.getUsername());
        Ticket ticket = this.ticketService.findById(id);
        user.removeTicket(ticket);
        this.userService.updateUser(user);
        this.ticketService.updateTicket(ticket);
        return "redirect:/account";
    }

}
