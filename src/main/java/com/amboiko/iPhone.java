package com.amboiko;

import org.springframework.stereotype.Component;

@Component
public class iPhone extends AbstractProduct {

    @Override
    public String getName() {
        return "IPhone";
    }

    @Override
    public String getTitle() {
        return "AMP: " + getName();
    }

}
