package com.stadium.app.controller;

import com.stadium.app.model.ReserveRequest;
import com.stadium.app.model.dto.TicketDto;
import com.stadium.app.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ReserveController {
    @Autowired
    private TicketService ticketService;

    @MessageMapping("/reserve")
    @SendTo("/topic/reservation")
    public TicketDto reserve(ReserveRequest message, Principal principal) throws Exception {
        return ticketService.reserveTicket(principal.getName(), Long.valueOf(message.getEventId()), message.getSector());
    }


}