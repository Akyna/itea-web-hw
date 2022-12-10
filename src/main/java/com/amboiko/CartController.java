package com.amboiko;

import com.amboiko.model.AddProductRequest;
import com.amboiko.model.AddProductResponse;
import com.amboiko.model.Product;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/cart")
public class CartController {


    @GetMapping
    protected String getCart() {
        return ApplicationConstant.CART_PAGE_URL;
    }


    @PostMapping
    public ResponseEntity<String> addProduct(
            Gson gson,
            HttpSession session,
            DBService dbService,
            @ModelAttribute AddProductRequest data
    ) {

        if (data.getProductId() != null) {
            Object cartObject = session.getAttribute(ApplicationConstant.APP_CART);
            if (cartObject == null) {
                cartObject = new HashMap<Product, Integer>();
            }

            Product product = dbService.getProduct(data.getProductId());
            Map<Product, Integer> cart = new HashMap<>((Map<? extends Product, ? extends Integer>) cartObject);
            int quantity = cart.get(product) == null ? 0 : cart.get(product);
            cart.put(product, ++quantity);
            session.setAttribute(ApplicationConstant.APP_CART, cart);

            AddProductResponse cartResponse = new AddProductResponse("Product was added", cart.size());
            String cartResponseJsonString = gson.toJson(cartResponse);
            return ResponseEntity.ok(cartResponseJsonString);

        }

        return ResponseEntity.badRequest().body("Error with adding a product");


    }
}
