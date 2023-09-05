package com.xmum.server.service.impl;

import com.xmum.server.entity.Ticket;
import com.xmum.server.mapper.EventMapper;
import com.xmum.server.mapper.TicketMapper;
import com.xmum.server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private PlatformTransactionManager platformTransactionManager;
    @Autowired
    private TransactionDefinition transactionDefinition;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Ticket> getAllTicketsByUid(Integer uid) {
        List<Ticket> tickets = ticketMapper.getUserTickets(uid);
        for(Ticket ticket:tickets){
            ticket.setEvent(eventMapper.getEventById(ticket.getEid()));
        }
        return tickets;
    }

    @Override
    public List<Ticket> getAllTicketsByEid(Integer eid) {
        return ticketMapper.getEventTickets(eid);
    }

    @Override
    public synchronized int orderTicket(Integer eid, Integer seatNo, Integer uid) {
        int result = 0;
        // 开启事务
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);

        try {
            // 1. check if the ticket is available
            Ticket ticket = ticketMapper.getTicketByEidAndSeatNo(eid, seatNo);
            if(ticket.getUid() != 0){
                throw new Exception("Ticket sold");
            }
            // 2. assign the ticket to the user
            ticketMapper.updateTicketOnUid(ticket.getTid(), uid);
            // 3. update the turnover
            eventMapper.updateTurnOver(eid, ticket.getPrice());
            // commit transaction
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            // rollback, failed
            result = -1;
            platformTransactionManager.rollback(transactionStatus);
        }
        return result;
    }
}
