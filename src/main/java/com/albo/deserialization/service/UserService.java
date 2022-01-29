package com.albo.deserialization.service;

import com.albo.deserialization.db.UsersDataBase;
import com.albo.deserialization.manager.ConfigFileManager;
import com.albo.deserialization.entity.User;
import com.albo.deserialization.exception.ConfigManagerException;
import com.albo.deserialization.reader.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;

import java.io.FileNotFoundException;
import java.util.Map;

@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final ConfigFileManager configManager;
    private final Reader<User> reader;
    private final Validator userValidator;
    private final UsersDataBase usersDataBase;

    public UserService(ConfigFileManager configManager, Reader<User> reader,
                       Validator userValidator, UsersDataBase usersDataBase) {
        this.configManager = configManager;
        this.reader = reader;
        this.userValidator = userValidator;
        this.usersDataBase = usersDataBase;
    }

    public void deserialize(String configName) {
        User user;
        Map<String, String> mapFromConfig = null;
        try {
            mapFromConfig = configManager.getHashMap(configName);
            for (Map.Entry<String, String> entry : mapFromConfig.entrySet()) {
                try {
                    user = reader.read(entry.getValue());
                    BeanPropertyBindingResult result = new BeanPropertyBindingResult(
                            user, "User from " + entry.getKey());
                    userValidator.validate(user, result);
                    if (result.hasErrors()) {
                        log.error("Not valid fields in the {}", entry.getKey());
                        log.error(result.getAllErrors().toString());
                    } else {
                        usersDataBase.saveUser(user);
                    }
                } catch (FileNotFoundException e) {
                    log.error("File with name {} not found", entry.getKey(), e);
                }
            }
        } catch (ConfigManagerException e) {
            log.error("Config is not found", e);
        }
    }

    public UsersDataBase getUsersDataBase() {
        return usersDataBase;
    }
}
