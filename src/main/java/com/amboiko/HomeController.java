package com.amboiko;

import com.amboiko.model.Category;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHome(HttpSession session,  DBService dbService) {
        List<Category> categories = dbService.getCategories();
        session.setAttribute(ApplicationConstant.APP_CATEGORIES, categories);
        return "pages/home";
    }

}
