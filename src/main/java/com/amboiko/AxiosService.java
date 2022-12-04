package com.amboiko;

import org.springframework.stereotype.Service;

@Service
public class AxiosService implements NetworkServiceInterface {
    private MainProduct product;

    public AxiosService() {
    }

    @Override
    public void setProduct(MainProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "AxiosService{" + "product=" + product +
                '}';
    }
}
