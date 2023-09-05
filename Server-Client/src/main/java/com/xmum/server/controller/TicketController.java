package com.xmum.server.controller;

import com.xmum.server.entity.Ticket;
import com.xmum.server.gui.MessageWindow;
import com.xmum.server.msg.Message;
import com.xmum.server.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/api/ticket/{uid}")
    public List<Ticket> getTicketsByUid(@PathVariable("uid") Integer uid) {
        MessageWindow.getInstance().ticketLog("User[" + uid + "] retrieves all tickets he/she has booked.");
        return ticketService.getAllTicketsByUid(uid);
    }

    @GetMapping(value = "/api/event/{eid}")
    public List<Ticket> getTicketsByEid(@PathVariable("eid") Integer eid) {
        MessageWindow.getInstance().ticketLog("All tickets from Event[" + eid + "] have just been retrieved.");
        return ticketService.getAllTicketsByEid(eid);
    }

    @PostMapping(value = "/api/event/{eid}/{uid}/{seatNo}")
    public Message orderTicket(@PathVariable("eid") Integer eid,
                               @PathVariable("uid")  Integer uid,
                               @PathVariable("seatNo")  Integer seatNo){
        Message msg = new Message();
        int result = ticketService.orderTicket(eid, seatNo, uid);
        if(result == -1){
            MessageWindow.getInstance().ticketLog("User[" + uid + "] tried to book Seat[" + seatNo+"] from Event[" + eid + "] but the ticket has been sold to someone.");
            msg.setCode(Message.ERROR_CODE);
            msg.setMessage("Sorry, this ticket has been booked.");
        }else{
            MessageWindow.getInstance().ticketLog("User[" + uid + "] successfully booked Seat[" + seatNo+"] from Event[" + eid + "].");
            msg.setCode(Message.CORRECT_CODE);
            msg.setMessage("You've booked the tickets.");
        }
        return msg;
    }
}
