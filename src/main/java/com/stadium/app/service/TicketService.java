package com.stadium.app.service;

import com.stadium.app.model.dto.TicketDto;
import com.stadium.app.model.entity.Event;
import com.stadium.app.model.entity.Ticket;
import com.stadium.app.model.entity.UserEntity;
import com.stadium.app.model.enums.Sector;
import com.stadium.app.model.enums.Status;
import com.stadium.app.repository.EventRepository;
import com.stadium.app.repository.TicketRepository;
import com.stadium.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    public List<Ticket> getTicketsByUser(UserEntity user) {
        return ticketRepository.findAllByUser(user);
    }

    public void sellTicket(String eventName, Float price, Status status, Sector sector, String username) {
        UserEntity user = userRepository.findByUsernameIgnoreCase(username);
        Ticket ticket = new Ticket(eventName, price, status, sector);
        ticket.setUser(user);
        ticketRepository.save(ticket);
        user.getTickets().add(ticket);
    }

    public void refundTicket(Long id, String username) {
        //return money
        UserEntity user = userRepository.findByUsernameIgnoreCase(username);
        user.getTickets().remove(ticketRepository.getById(id));
        var a = ticketRepository.getById(id);
        ticketRepository.delete(a);
    }

    public TicketDto reserveTicket(String username, Long eventId, String sector) {
        Event event = eventRepository.getById(eventId);
        var user = userRepository.findByUsernameIgnoreCase(username);
        return eventService.reserveTicket(user, event.getName(), Sector.valueOf(sector));
    }
}
