package com.amboiko;

import com.amboiko.entity.Product;
import com.amboiko.services.ProductService;
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

    final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    protected String getProducts(
            ModelMap modelMap,
            @RequestParam(required = false) String categoryId
    ) {
        String uri = ApplicationConstant.PRODUCTS_PAGE_URL;


        if (categoryId != null) {
            List<Product> products = productService.getProducts(categoryId);
            modelMap.addAttribute(ApplicationConstant.APP_PRODUCTS, products);
            uri = ApplicationConstant.PRODUCTS_IN_CATEGORY_PAGE_URL;
        }

        return uri;
    }
}
