package com.albo.deserialization;

import com.albo.deserialization.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
//        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("com.albo.deserialization");
        context.start();
        UserService userService = context.getBean("userService", UserService.class);
        userService.run();
        userService.printUsersFromDataBaseToConsole();
        context.stop();
        context.close();
    }
}
