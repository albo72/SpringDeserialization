package com.albo.deserialization;

import com.albo.deserialization.db.UsersDataBase;
import com.albo.deserialization.entity.User;
import com.albo.deserialization.reader.Reader;
import com.albo.deserialization.reader.UserReaderWithJackson;
import com.albo.deserialization.service.UserService;
import com.albo.deserialization.util.PropertyManager;
import com.albo.deserialization.util.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("com.albo.deserialization")
public class SpringConfig {
    @Bean
    public PropertyManager propertyManager() {
        return new PropertyManager();
    }

    @Bean
    public Reader<User> reader() {
        return new UserReaderWithJackson();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }

    @Bean
    public UsersDataBase usersDataBase() {
        return new UsersDataBase();
    }

    @Bean
    public UserService userService() {
        return new UserService(propertyManager(), reader(),
                userValidator(), usersDataBase());
    }
}
