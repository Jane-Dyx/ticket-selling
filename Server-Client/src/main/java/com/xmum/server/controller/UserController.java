package com.xmum.server.controller;

import com.xmum.server.entity.User;
import com.xmum.server.gui.MessageWindow;
import com.xmum.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/auth/login")
    public User login(@RequestBody Map<String, String> map){
        String email = map.get("email");
        String password = map.get("password");
        User result = userService.loginUser(email, password);
        if(result == null){
            result = new User();
            result.setUid(-1);
            result.setEmail("null");
            result.setPassword("null");
            result.setRole("null");
        }
        if(result.getUid() == -1){
            MessageWindow.getInstance().userLog(email + " tried to login but failed.");
        }else{
            MessageWindow.getInstance().userLog(email + " successfully logged in.");
        }
        return result;
    }

    @PostMapping("/api/auth/signup")
    public User signUp(@RequestBody Map<String, String> map){
        String email = map.get("email");
        String password = map.get("password");
        User result = userService.createUser(email, password);
        if(result.getUid() == -1){
            MessageWindow.getInstance().userLog(email + " tried to sign up but failed.");
        }else{
            MessageWindow.getInstance().userLog(email + " successfully signed up.");
        }
        return result;
    }
}
