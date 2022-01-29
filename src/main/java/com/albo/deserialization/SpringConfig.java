package com.albo.deserialization;

import com.albo.deserialization.db.UsersDataBase;
import com.albo.deserialization.entity.User;
import com.albo.deserialization.eventhandling.StartEventHandler;
import com.albo.deserialization.eventhandling.StopEventHandler;
import com.albo.deserialization.manager.ConfigFileManager;
import com.albo.deserialization.reader.Reader;
import com.albo.deserialization.reader.UserReaderWithJackson;
import com.albo.deserialization.service.UserService;
import com.albo.deserialization.manager.PropertyManager;
import com.albo.deserialization.validation.UserValidator;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

@Configuration
//@ComponentScan("com.albo.deserialization")
public class SpringConfig {
    @Bean
    public ConfigFileManager configFileManager() {
        return new PropertyManager();
    }

    @Bean
    public Reader<User> reader() {
        return new UserReaderWithJackson();
    }

    @Bean
    public Validator validator() {
        return new UserValidator();
    }

    @Bean
    public UsersDataBase usersDataBase() {
        return new UsersDataBase();
    }

    @Bean
    public UserService userService() {
        return new UserService(configFileManager(), reader(),
                validator(), usersDataBase());
    }

    @Bean
    public ApplicationListener<?> startEventHandler(){
        return new StartEventHandler();
    }

    @Bean
    public ApplicationListener<?> stopEventHandler(){
        return new StopEventHandler();
    }
}
