package com.amboiko;

import com.amboiko.entity.Product;
import com.amboiko.services.ProductEntityManager;
import com.amboiko.utils.ApplicationConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductEntityManager productEntityManager;

    public ProductController(ProductEntityManager productEntityManager) {
        this.productEntityManager = productEntityManager;
    }

    @GetMapping
    protected String getProduct(ModelMap modelMap, @RequestParam(required = false) String id) {
        String url = ApplicationConstant.PRODUCTS_PAGE_URL;
        try {
            Product product;
            if (id != null && (product = productEntityManager.getProductById(Integer.parseInt(id))) != null) {
                modelMap.addAttribute(ApplicationConstant.APP_PRODUCT, product);
                url = ApplicationConstant.PRODUCT_PAGE_URL;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return url;
    }
}
