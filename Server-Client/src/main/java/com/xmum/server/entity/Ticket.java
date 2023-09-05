package com.xmum.server.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticket {
    private Integer tid;
    private Integer eid;
    private Integer seatNo;
    private Integer uid; // if uid == 0 means the ticket is not sold yet
    private BigDecimal price;

    private Event event; // make it simple when see user's tickets, he can also see other information
}
