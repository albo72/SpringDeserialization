package com.albo.deserialization;

import com.albo.deserialization.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml"
//        );
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        context.start();
        UserService userService = context.getBean("userService", UserService.class);
        userService.deserialize("config.properties");
        userService.deserialize("config2.properties");
        System.out.println(userService.getUsersDataBase().getUsersDB());
        context.stop();
        context.close();
    }
}
