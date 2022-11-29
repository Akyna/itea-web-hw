package com.amboiko;

import com.amboiko.model.User;
import com.amboiko.services.DBService;
import com.amboiko.services.ValidatorService;
import com.amboiko.utils.ApplicationConstant;
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
        String url = ApplicationConstant.LOGIN_PAGE_URL;
        String key = req.getParameter("logout");


        if (key != null) {
            session.removeAttribute(ApplicationConstant.APP_USER);
            session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.FALSE);
        }

        Object appUser = session.getAttribute(ApplicationConstant.APP_USER);

        if (appUser instanceof User && dbService.getUserById(((User) appUser).getId()) != null) {
            session.setAttribute(ApplicationConstant.APP_USER, appUser);
            session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
            url = ApplicationConstant.HOME_PAGE_URL;
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
            HttpSession session = req.getSession();
            String emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            String passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");

            req.setAttribute("emailError", emailError);
            req.setAttribute("passwordError", passwordError);

            isErrorEmpty = ValidatorService.validateFields(new String[]{
                    emailError,
                    passwordError,
            });

            User user;
            String url = ApplicationConstant.LOGIN_PAGE_URL;

            req.setAttribute("email", email);
            req.setAttribute("password", password);


            if (isErrorEmpty) {
                req.setAttribute("errorMessage", "User not found. Please try login again");
                if ((user = dbService.getUser(email, password)) != null) {
                    session.setAttribute(ApplicationConstant.APP_USER, user);
                    session.setAttribute(ApplicationConstant.APP_USER_IS_AUTHORIZED, Boolean.TRUE);
                    url = ApplicationConstant.HOME_PAGE_URL;
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
