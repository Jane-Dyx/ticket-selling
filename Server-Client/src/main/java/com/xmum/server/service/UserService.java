package com.xmum.server.service;

import com.xmum.server.entity.User;

public interface UserService {
    public User loginUser(String email, String password);

    public User createUser(String email, String password);
}
