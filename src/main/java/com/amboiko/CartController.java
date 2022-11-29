package com.amboiko;

import com.amboiko.model.CartResponse;
import com.amboiko.model.Product;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 8158606569158258206L;
    DBService dbService;
    RequestDispatcher dispatcher;
    private Gson gson;

    @Override
    public void init() {
        dbService = new DBService();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            dispatcher = req.getRequestDispatcher(ApplicationConstant.CART_PAGE_URL);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("product_id");

        if (productId != null) {
            HttpSession session = req.getSession();
            Object cartObject = session.getAttribute(ApplicationConstant.APP_CART);
            if (cartObject == null) {
                cartObject = new HashMap<Product, Integer>();
            }

            Product product = dbService.getProduct(productId);
            Map<Product, Integer> cart = new HashMap<>((Map<? extends Product, ? extends Integer>) cartObject);
            int quantity = cart.get(product) == null ? 0 : cart.get(product);
            cart.put(product, ++quantity);
            session.setAttribute(ApplicationConstant.APP_CART, cart);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            CartResponse cartResponse = new CartResponse("Product was added", cart.size());
            String cartResponseJsonString = gson.toJson(cartResponse);

            try {
                PrintWriter out = resp.getWriter();
                out.write(cartResponseJsonString);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
