package com.amboiko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public NetworkServiceInterface getNetworkServiceInterface() {
        NetworkServiceInterface service = new FetchService();
        service.setProduct(new iMac());
        if (false) {
            service = new AxiosService();
            service.setProduct(new iPhone());
        }
        return service;
    }


}
