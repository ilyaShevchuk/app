package com.stadium.app.service;

import com.stadium.app.exceptions.BadParamsException;
import com.stadium.app.model.dto.EventDto;
import com.stadium.app.model.dto.PricesDto;
import com.stadium.app.model.dto.TicketDto;
import com.stadium.app.model.entity.AvailablePlace;
import com.stadium.app.model.entity.Event;
import com.stadium.app.model.entity.Ticket;
import com.stadium.app.model.entity.UserEntity;
import com.stadium.app.model.enums.Sector;
import com.stadium.app.model.enums.Status;
import com.stadium.app.model.requestBody.EventRequestBody;
import com.stadium.app.repository.AvailablePlacesRepository;
import com.stadium.app.repository.EventRepository;
import com.stadium.app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AvailablePlacesRepository placesRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public EventDto createEvent(String name, String date){
        AvailablePlace availablePlace = new AvailablePlace();
        placesRepository.save(availablePlace);
        Event event = new Event(name, date, availablePlace);
        eventRepository.save(event);
        return new EventDto(event.getId(), event.getName(), event.getDate());
    }

    public List<EventDto> getAllEvents(){
        return eventRepository.findAll().stream().map(EventDto::new).collect(Collectors.toList());
    }

    public void updateEvent(EventRequestBody event){
        Event oldEvent = eventRepository.getOne(event.getId());
        oldEvent.setName(event.getName());
        oldEvent.setDate(event.getDate());
        oldEvent.setStatus(Status.valueOf(event.getStatus()));
        eventRepository.save(oldEvent);
    }

    public void cancelEvent(Long id){
        Event event = eventRepository.getById(id);
        event.setStatus(Status.CANCELED);
        // makeRefundForCanceledEvent(event);
        eventRepository.save(event);
        new EventDto(event);
    }
    // change for good code
    public Event getEvent(Long id){
        var a= eventRepository.findById(id);
        if (a.isEmpty()){
            throw new BadParamsException("");
        }
        return a.get();
    }
    public PricesDto showPrices(String eventName){
        Event event = getEvent(eventName);
        AvailablePlace allPlaces = event.getAvailablePlaces();
        return new PricesDto(allPlaces.getPrices());
    }

    public void setPrices(String eventName, Float A1Price, Float A2Price, Float B1Price,
                          Float B2Price, Float B3Price, Float LoungePrice){
        Event event = getEvent(eventName);
        event.getAvailablePlaces().setPrices(A1Price, A2Price, B1Price, B2Price, B3Price, LoungePrice);
    }

    public TicketDto sellTicket(String eventName, Sector sector){
        Event event = getEvent(eventName);
        Float price = event.getAvailablePlaces().buyTicket(sector);
        Ticket ticket = new Ticket();
        ticket.setEvent(eventName);
        ticket.setPrice(price);
        ticket.setSector(sector);
        ticket.setStatus(Status.AVAILABLE);
        ticketRepository.save(ticket);
        return new TicketDto(event.getName(), event.getDate().toString(), price, Status.AVAILABLE);
    }

    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }

    private void makeRefundForCanceledEvent(Event event){
        List<Ticket> allSoldTickets = ticketRepository.findAllByEvent(event.getName());
        for (Ticket ticket : allSoldTickets){
            ticket.setStatus(Status.CANCELED);
            makeRefundForUser(ticket.getUser(), ticket.getPrice());
        }
    }

    private void makeRefundForUser(UserEntity userEntity, float price){
        // метод затычка для возврата денег на карту
        float userMoney = 0;
        userMoney += price;
    }

    private Event getEvent(String eventName){
        Event event = eventRepository.findEventByName(eventName);
        if (event == null){
            throw new BadParamsException("Don't find Event name: " +  eventName);
        }
        return event;
    }


}
