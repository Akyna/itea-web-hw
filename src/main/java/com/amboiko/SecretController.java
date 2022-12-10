package com.amboiko;

import com.amboiko.utils.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secret")
public class SecretController {

    @GetMapping
    protected String getSecretPage() {
        return ApplicationConstant.SECRET_PAGE_URL;
    }
}
