package com.xmum.server.entity;

import lombok.Data;

@Data
public class Hall {
    private Integer hid;
    private String name;
    private Integer capacity;
    private Integer zone1;
    private Integer zone2;
    private Integer zone3;
}
