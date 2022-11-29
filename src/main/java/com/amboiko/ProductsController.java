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
import java.util.List;

public class ProductsController extends HttpServlet {
    private static final long serialVersionUID = 8839162079026193621L;
    DBService dbService;
    RequestDispatcher dispatcher;

    @Override
    public void init() {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String uri = ApplicationConstant.PRODUCTS_PAGE_URL;
        String categoryId = req.getParameter("category_id");

        if (categoryId != null) {
            List<Product> products = dbService.getProducts(categoryId);
            req.setAttribute(ApplicationConstant.APP_PRODUCTS, products);
            uri = ApplicationConstant.PRODUCTS_IN_CATEGORY_PAGE_URL;
        }

        try {
            dispatcher = req.getRequestDispatcher(uri);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
