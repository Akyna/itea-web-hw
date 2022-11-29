package com.amboiko;

import com.amboiko.model.Product;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 2650122574478201031L;

    DBService dbService;
    RequestDispatcher dispatcher;

    @Override
    public void init() {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("id");

        if (productId != null) {
            Product product = dbService.getProduct(productId);
            req.setAttribute(ApplicationConstant.APP_PRODUCT, product);
        }

        try {
            dispatcher = req.getRequestDispatcher(ApplicationConstant.PRODUCT_PAGE_URL);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
