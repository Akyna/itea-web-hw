package com.amboiko;

import com.amboiko.utils.ApplicationConstant;
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
            dispatcher = req.getRequestDispatcher(ApplicationConstant.FORBIDDEN_PAGE_URL);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
