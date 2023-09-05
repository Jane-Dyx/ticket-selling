package com.xmum.server.service.impl;

import com.xmum.server.entity.Event;
import com.xmum.server.entity.Hall;
import com.xmum.server.entity.Ticket;
import com.xmum.server.mapper.EventMapper;
import com.xmum.server.mapper.HallMapper;
import com.xmum.server.mapper.TicketMapper;
import com.xmum.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private HallMapper hallMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public int createEvent(Event event) {

        // 1. insert the event into event table
        int result = eventMapper.addNewEvent(event);
        // 2. get the information of hall
        Hall hall = hallMapper.getHallById(event.getHid());
        // 3. insert tickets into ticket table
        Ticket ticket1 = new Ticket();
        ticket1.setEid(event.getEid());
        ticket1.setPrice(event.getPrice1());
        for(int i = 1; i <= hall.getZone1();i++){
            ticket1.setSeatNo(i);
            ticketMapper.generateNewTicket(ticket1);
        }
        Ticket ticket2 = new Ticket();
        ticket2.setEid(event.getEid());
        ticket2.setPrice(event.getPrice2());
        for(int i = 1; i <= hall.getZone2();i++){
            ticket2.setSeatNo(i + hall.getZone1());
            ticketMapper.generateNewTicket(ticket2);
        }
        Ticket ticket3 = new Ticket();
        ticket3.setEid(event.getEid());
        ticket3.setPrice(event.getPrice3());
        for(int i = 1; i <= hall.getZone3();i++){
            ticket3.setSeatNo(i + hall.getZone1() + hall.getZone2());
            ticketMapper.generateNewTicket(ticket3);
        }
        return result;
    }

    @Async("asyncServiceExecutor")
    @Override
    public Future<List<Event>> getAllEvents() {
        return new AsyncResult<>(eventMapper.getAllEvents());
    }
}
