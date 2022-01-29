package com.albo.deserialization.reader;

import com.albo.deserialization.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UserReaderWithJackson implements Reader<User> {

    @Override
    public User read(String fileName) throws FileNotFoundException {
        User user = null;
        try {
            user = new ObjectMapper().readValue(new File(fileName), User.class);
        } catch (IOException e) {
            throw new FileNotFoundException("Файл не найден!");
        }
        return user;
    }
}
