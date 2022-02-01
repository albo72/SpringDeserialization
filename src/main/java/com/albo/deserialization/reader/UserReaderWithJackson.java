package com.albo.deserialization.reader;

import com.albo.deserialization.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class UserReaderWithJackson implements Reader<User> {


    @Override
    public User read(String filePath) throws FileNotFoundException {
        ClassPathResource cps = new ClassPathResource(filePath);
        User user = null;
        try {
            user = new ObjectMapper().readValue(cps.getFile(), User.class);
        } catch (IOException e) {
            throw new FileNotFoundException("Файл не найден!");
        }
        return user;
    }
}
