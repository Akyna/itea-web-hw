package com.amboiko.xml;

import com.amboiko.xml.model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Customer customer = (Customer) context.getBean("customer");
        System.out.println(customer.getCart());
    }
}
