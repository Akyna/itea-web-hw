package com.amboiko;

import com.amboiko.services.SessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        if (session.getAttribute(SessionService.APP_USER) == null) {
            RequestDispatcher dispatcher = servletRequest.getServletContext()
                    .getRequestDispatcher("/forbidden");
            dispatcher.forward(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
