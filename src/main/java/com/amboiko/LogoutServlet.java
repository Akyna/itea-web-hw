package com.amboiko;

import com.amboiko.services.DBService;
import com.amboiko.services.WebPageService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = -1466388984950287911L;
    Logger logger;
    DBService dbService;
    WebPageService webPageService;

    @Override
    public void init() {
        logger = Logger.getLogger(LoginServlet.class.getName());
        dbService = new DBService();
        webPageService = new WebPageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        webPageService.setContentRoot(req.getContextPath());

        HttpSession session = req.getSession();
        session.invalidate();

        StringBuilder output = new StringBuilder();
        output.append(WebPageService.HEADER);
        output.append(webPageService.getMenu());
        output.append("<h3>You have successfully left your account</h3>");
        output.append(WebPageService.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }
}
