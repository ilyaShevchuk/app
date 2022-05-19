package com.stadium.app.controller;

import com.stadium.app.model.entity.Event;
import com.stadium.app.model.entity.UserEntity;
import com.stadium.app.model.enums.Sector;
import com.stadium.app.model.requestBody.PayRequestBody;
import com.stadium.app.service.EventService;
import com.stadium.app.service.TicketService;
import com.stadium.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
            UserEntity user = userService.findUserByUsername(username);
            model.addAttribute("user", user);
        } else {
            String username = principal.toString();
            System.out.println(username);
            UserEntity user = userService.findUserByUsername("admin");
            model.addAttribute("user", user);
        }
        return "user-dashboard.html";
    }

    @GetMapping("/buy/{id}")
    public String buyTicket(@PathVariable("id") Long id, Model model, Principal principal) {
        Event event = eventService.getEvent(id);
        var x = event.getAvailablePlaces().getDto();
        model.addAttribute("place0", x.get(0));
        model.addAttribute("place1", x.get(1));
        model.addAttribute("place2", x.get(2));
        model.addAttribute("place3", x.get(3));
        model.addAttribute("place4", x.get(4));
        model.addAttribute("place5", x.get(5));
        model.addAttribute("event", event);
        return "buy-ticket.html";
    }

    @GetMapping("/{id}/tickets")
    public String showTicketsList(@PathVariable("id") Long id, Model model) {
        UserEntity user = userService.getUserById(id);

        model.addAttribute("tickets", ticketService.getTicketsByUser(user));
        return "show-tickets.html";
    }

    @GetMapping("/refund/{id}")
    public String refundTicket(@PathVariable("id") Long id, Model model) {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
            ticketService.refundTicket(id, username);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/pay")
    public String payForTicket(@RequestParam(value = "eventId", required = false) String eventId,
                               @RequestParam(value = "sector", required = false) String sector,
                               @RequestParam(value = "price", required = false) String price,
                               Model model, Principal principal) {
        Event event = eventService.getEvent(Long.valueOf(eventId));
        event.getAvailablePlaces().buyTicket(Sector.valueOf(sector));
        String username = principal.getName();
        ticketService.sellTicket(event.getName(), Float.valueOf(price), event.getStatus(), Sector.valueOf(sector),
                username);
        model.addAttribute("price", price);
        return "pay-load.html";
    }
}