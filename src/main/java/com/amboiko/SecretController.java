package com.amboiko;

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
            req.setAttribute("isLoggedSuccessfully", Boolean.TRUE);
            req.setAttribute("isShowLogoutLink", Boolean.TRUE);

            dispatcher = req.getRequestDispatcher("WEB-INF/views/pages/secret.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
