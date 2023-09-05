package com.xmum.server.msg;

import lombok.Data;

@Data
public class Message {
    public static final int ERROR_CODE = 0;
    public static final int CORRECT_CODE = 1;

    private int code;
    private String message;
}
