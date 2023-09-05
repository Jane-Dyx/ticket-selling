package com.xmum.server.mapper;

import com.xmum.server.entity.Ticket;

import java.util.List;

public interface TicketMapper {
    void generateNewTicket(Ticket ticket);

    List<Ticket> getUserTickets(Integer uid);

    List<Ticket> getEventTickets(Integer eid);

    Ticket getTicketByEidAndSeatNo(Integer eid, Integer seatNo);

    void updateTicketOnUid(Integer tid, Integer uid);
}
