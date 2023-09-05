package com.xmum.server.mapper;

import com.xmum.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public User selectUserWithEmailAndPassword(String email, String password);

    public User selectUserWithEmail(String email);

    public int createNewUser(User user);
}
