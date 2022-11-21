package com.amboiko;

import com.amboiko.model.User;
import com.amboiko.services.DBService;
import com.amboiko.services.SessionService;
import com.amboiko.services.ValidatorService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 402358355779865865L;
    DBService dbService;
    RequestDispatcher dispatcher;

    @Override
    public void init() {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String url = "WEB-INF/views/pages/login.jsp";
        String key = req.getParameter("logout");

        req.setAttribute("isShowRegisterLink", Boolean.TRUE);


        if (key != null) {
            session.invalidate();
            req.setAttribute("isLoggedSuccessfully", Boolean.FALSE);
            req.setAttribute("isShowLoginLink", Boolean.TRUE);
            req.setAttribute("isShowRegisterLink", Boolean.FALSE);
            url = "WEB-INF/views/pages/logout.jsp";
        }

        session = req.getSession(true);
        Object appUser = session.getAttribute(SessionService.APP_USER);

        if (appUser instanceof User && dbService.getUserById(((User) appUser).getId()) != null) {
            req.setAttribute("isLoggedSuccessfully", Boolean.TRUE);
            req.setAttribute("isShowRegisterLink", Boolean.FALSE);
            req.setAttribute("isShowSecretLink", Boolean.TRUE);
            req.setAttribute("isShowLogoutLink", Boolean.TRUE);
            url = "WEB-INF/views/pages/home.jsp";
        }

        try {
            dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        boolean isErrorEmpty;
        String submit_form = ValidatorService.trimInput(req.getParameter("submit"));
        String email = ValidatorService.trimInput(req.getParameter("email"));
        String password = ValidatorService.trimInput(req.getParameter("password"));

        if ("Confirm".equals(submit_form)) {
            String emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            String passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");

            req.setAttribute("emailError", emailError);
            req.setAttribute("passwordError", passwordError);

            isErrorEmpty = ValidatorService.validateFields(new String[]{
                    emailError,
                    passwordError,
            });

            User user;
            String url = "WEB-INF/views/pages/login.jsp";

            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("isShowRegisterLink", Boolean.TRUE);


            if (isErrorEmpty) {
                req.setAttribute("errorMessage", "User not found. Please try login again");
                if ((user = dbService.getUser(email, password)) != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute(SessionService.APP_USER, user);
                    req.setAttribute("isShowRegisterLink", Boolean.FALSE);
                    req.setAttribute("isShowSecretLink", Boolean.TRUE);
                    req.setAttribute("isShowLogoutLink", Boolean.TRUE);
                    url = "WEB-INF/views/pages/home.jsp";
                }
            }


            try {
                dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
