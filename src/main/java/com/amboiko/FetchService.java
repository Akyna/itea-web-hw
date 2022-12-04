package com.amboiko;

import org.springframework.stereotype.Service;

@Service
public class FetchService implements NetworkServiceInterface {
    private MainProduct product;

    public FetchService() {
    }

    @Override
    public void setProduct(MainProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "FetchService{" + "product=" + product +
                '}';
    }
}
