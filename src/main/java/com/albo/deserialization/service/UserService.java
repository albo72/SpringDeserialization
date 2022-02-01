package com.albo.deserialization.service;

import com.albo.deserialization.db.UsersDataBase;
import com.albo.deserialization.entity.User;
import com.albo.deserialization.manager.Config;
import com.albo.deserialization.reader.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.io.FileNotFoundException;

@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final Config config;
    private final Reader<User> reader;
    private final Validator userValidator;
    private final UsersDataBase usersDataBase;

    public UserService(Config config, Reader<User> reader,
                       Validator userValidator, UsersDataBase usersDataBase) {
        this.config = config;
        this.reader = reader;
        this.userValidator = userValidator;
        this.usersDataBase = usersDataBase;
    }

    public UsersDataBase getUsersDataBase() {
        return usersDataBase;
    }

    public void run() {
        User user;
        for (String filePath : config.getFiles()) {
            try {
                user = reader.read(filePath);
                BeanPropertyBindingResult result = new BeanPropertyBindingResult(
                        user, "User from " + filePath);
                userValidator.validate(user, result);
                if (result.hasErrors()) {
                    StringBuilder errorsStr = new StringBuilder("Not valid fields in " + filePath + ": ");
                    for (ObjectError error : result.getAllErrors()) {
                        errorsStr.append(error.getDefaultMessage()).append("; ");
                    }
                    log.error(errorsStr.toString());
                } else {
                    usersDataBase.saveUser(user);
                }
            } catch (FileNotFoundException e) {
                log.error("File with path {} not found", filePath, e);
            }
        }
    }

    public void printUsersFromDataBaseToConsole(){
        for (User user : usersDataBase.getUsersDB()) {
            System.out.println(new StringBuilder("User with name:").append(user.getName()).append(", password:").
                    append(user.getPassword()).append(", email:").append(user.getEmail()).append(";"));
        }
    }
}
