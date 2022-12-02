package com.amboiko;

import com.amboiko.model.Category;
import com.amboiko.services.DBService;
import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 8839162079026193621L;
    DBService dbService;
    RequestDispatcher dispatcher;

    @Override
    public void init() {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        List<Category> categories = dbService.getCategories();
        session.setAttribute(ApplicationConstant.APP_CATEGORIES, categories);
        try {
            dispatcher = req.getRequestDispatcher(ApplicationConstant.HOME_PAGE_URL);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
