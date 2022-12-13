package com.amboiko;

import com.amboiko.entity.Category;
import com.amboiko.services.CategoryService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public String getHome(HttpSession session) {
        List<Category> categories = categoryService.getCategories();

        session.setAttribute(ApplicationConstant.APP_CATEGORIES, categories);
        return "pages/home";
    }

}
