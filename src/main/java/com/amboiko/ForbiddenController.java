package com.amboiko;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ForbiddenController extends HttpServlet {
    private static final long serialVersionUID = -7963559522895586594L;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setAttribute("isLoggedSuccessfully", Boolean.FALSE);
            req.setAttribute("isShowLoginLink", Boolean.TRUE);
            dispatcher = req.getRequestDispatcher("WEB-INF/views/pages/forbidden.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
