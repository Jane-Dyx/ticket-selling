package com.xmum.server.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Event {
    private Integer eid;
    private String title;
    private String details;
    private Timestamp start;
    private Timestamp end;
    private String image; // path
    private Integer hid;
    private BigDecimal price1;
    private BigDecimal price2;
    private BigDecimal price3;
    private BigDecimal turnover;
    private byte[] imageData; // actual image binary data
}
