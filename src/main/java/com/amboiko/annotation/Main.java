package com.amboiko.annotation;

import com.amboiko.annotation.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebShopConfiguration.class);
        Customer customer = context.getBean(Customer.class);
        System.out.println(customer.getCart());
        context.close();
    }
}
