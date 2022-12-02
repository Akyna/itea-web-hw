package com.amboiko;

import com.amboiko.utils.ApplicationConstant;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecretController extends HttpServlet {
    private static final long serialVersionUID = 7911659960004421270L;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            dispatcher = req.getRequestDispatcher(ApplicationConstant.SECRET_PAGE_URL);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
