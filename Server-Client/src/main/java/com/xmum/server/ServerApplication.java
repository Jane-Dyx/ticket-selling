package com.xmum.server;

import com.xmum.server.gui.MessageWindow;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableAsync
@SpringBootApplication
@MapperScan("com.xmum.server.mapper")
public class ServerApplication {

    public static void main(String[] args) {
        MessageWindow window = MessageWindow.getInstance();
        window.showWindow();
        try {
            window.systemLog("Server started at " + InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        SpringApplication.run(ServerApplication.class, args);
    }

}

