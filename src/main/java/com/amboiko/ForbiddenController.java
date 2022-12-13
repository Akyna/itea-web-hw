package com.amboiko;

import com.amboiko.utils.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forbidden")
public class ForbiddenController {

    @GetMapping
    protected String getForbidden() {
        return ApplicationConstant.FORBIDDEN_PAGE_URL;
    }
}
