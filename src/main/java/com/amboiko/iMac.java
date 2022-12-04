package com.amboiko;

import org.springframework.stereotype.Component;

@Component
public class iMac extends AbstractProduct {

    @Override
    public String getName() {
        return "IMac";
    }

    @Override
    public String getTitle() {
        return "AMP: " + getName();
    }

}
