package com.amboiko;

import com.amboiko.services.WebPageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class AuthorizationErrorServlet extends HttpServlet {

    private static final long serialVersionUID = 2761530605071902183L;
    Logger logger;
    WebPageService webPageService;

    @Override
    public void init() {
        logger = Logger.getLogger(LoginServlet.class.getName());
        webPageService = new WebPageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        webPageService.setContentRoot(req.getContextPath());

        StringBuilder output = new StringBuilder();
        output.append(WebPageService.HEADER);
        output.append("<a href='" + req.getContextPath() + "'>Login</a>");
        output.append("<h3>Access denied, ви не authorized</h3>");

        output.append(WebPageService.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }
}
