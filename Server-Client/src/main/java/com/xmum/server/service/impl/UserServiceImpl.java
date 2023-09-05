package com.xmum.server.service.impl;

import com.xmum.server.entity.User;
import com.xmum.server.mapper.UserMapper;
import com.xmum.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private PlatformTransactionManager platformTransactionManager;
//    @Autowired
//    private TransactionDefinition transactionDefinition;
    @Autowired
    private UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User loginUser(String email, String password) {
        return  userMapper.selectUserWithEmailAndPassword(email, password);
    }

    @Override
    public synchronized User createUser(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole("user");
        User user1 = userMapper.selectUserWithEmail(email);
        if(user1 != null){
            logger.info(email + " tried to sign up, but already exists!");
            user1.setUid(-1);
            return user1;
        }else{
            userMapper.createNewUser(newUser);
            logger.info("New User: " + newUser);
            return newUser;
        }
    }
}
