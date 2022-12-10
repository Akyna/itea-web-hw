package com.amboiko;

import com.amboiko.model.Product;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    protected String getProduct(ModelMap modelMap, DBService dbService, @RequestParam(required = false) String id) {
        String url = ApplicationConstant.PRODUCTS_PAGE_URL;
        Product product;
        if (id != null && (product = dbService.getProduct(id)) != null) {
            modelMap.addAttribute(ApplicationConstant.APP_PRODUCT, product);
            url = ApplicationConstant.PRODUCT_PAGE_URL;
        }

        return url;
    }
}
