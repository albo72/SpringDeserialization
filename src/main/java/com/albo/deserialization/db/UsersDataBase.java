package com.albo.deserialization.db;

import com.albo.deserialization.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDataBase {
    private final List<User> usersDB = new ArrayList<>();

    public List<User> getUsersDB() {
        return usersDB;
    }

    public void saveUser(User user){
        usersDB.add(user);
    }
}
