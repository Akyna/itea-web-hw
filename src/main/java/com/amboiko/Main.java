package com.amboiko;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println(context.getBean(NetworkServiceInterface.class));


//        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
//        System.out.println(context.getBean("getNetworkServiceInterface"));
    }
}
