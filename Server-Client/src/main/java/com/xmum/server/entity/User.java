package com.xmum.server.entity;

import lombok.Data;

@Data
public class User {
    private Integer uid;

    private String email;

    private String password;

    private String role;
}
