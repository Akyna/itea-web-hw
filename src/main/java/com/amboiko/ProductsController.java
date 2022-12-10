package com.amboiko;

import com.amboiko.model.Product;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @GetMapping
    protected String getProducts(
            ModelMap modelMap,
            DBService dbService,
            @RequestParam(required = false) String categoryId
    ) {
        String uri = ApplicationConstant.PRODUCTS_PAGE_URL;


        if (categoryId != null) {
            List<Product> products = dbService.getProducts(categoryId);
            modelMap.addAttribute(ApplicationConstant.APP_PRODUCTS, products);
            uri = ApplicationConstant.PRODUCTS_IN_CATEGORY_PAGE_URL;
        }

        return uri;
    }
}
