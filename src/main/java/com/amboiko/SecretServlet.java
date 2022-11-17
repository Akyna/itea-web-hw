package com.amboiko;

import com.amboiko.services.WebPageService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SecretServlet extends HttpServlet {
    private static final long serialVersionUID = -7732767139500239519L;
    WebPageService webPageService;

    @Override
    public void init() {
        webPageService = new WebPageService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        webPageService.setContentRoot(req.getContextPath());

        StringBuilder output = new StringBuilder();
        output.append(WebPageService.HEADER);
        output.append(webPageService.getMenu());
        output.append(webPageService.getLogOut());
        output.append("<h3>Це є secret text тільки для authorized users</h3>");
        output.append(WebPageService.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }
}
