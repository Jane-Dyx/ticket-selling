package com.xmum.server.service;

import com.xmum.server.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllTicketsByUid(Integer uid);

    List<Ticket> getAllTicketsByEid(Integer eid);

    int orderTicket(Integer eid, Integer seatNo, Integer uid);
}
