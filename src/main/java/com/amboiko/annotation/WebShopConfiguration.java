package com.amboiko.annotation;

import com.amboiko.annotation.model.Cart;
import com.amboiko.annotation.model.Customer;
import com.amboiko.annotation.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebShopConfiguration {

    @Bean
    public Customer getCustomer() {
        return new Customer("Akyna");
    }

    @Bean
    public Cart getCart() {
        return new Cart(10);
    }

    @Bean
    public Map<Product, Integer> getProducts() {
        return new HashMap<>() {
            private static final long serialVersionUID = -2823639362518836954L;

            {
                put(new Product().setId(1).setName("iMac").setPrice(3000.0), 1);
                put(new Product().setId(1).setName("iPhone").setPrice(800.89), 2);
            }
        };
    }
}
