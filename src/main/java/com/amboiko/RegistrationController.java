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

public class RegistrationController extends HttpServlet {
    private static final long serialVersionUID = -1466388984950287911L;

    RequestDispatcher dispatcher;
    DBService dbService;

    @Override
    public void init() {
        dbService = new DBService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String url = ApplicationConstant.REGISTRATION_PAGE_URL;

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
        String retypePassword = ValidatorService.trimInput(req.getParameter("retypePassword"));
        String fullName = ValidatorService.trimInput(req.getParameter("fullName"));

        if ("Confirm".equals(submit_form)) {
            String emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            String passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");
            String retypePasswordError = ValidatorService.getRetypePasswordError(password, retypePassword);
            String fullNameError = ValidatorService.checkIsNotEmpty(fullName);


            req.setAttribute("emailError", emailError);
            req.setAttribute("passwordError", passwordError);
            req.setAttribute("retypePasswordError", retypePasswordError);
            req.setAttribute("fullNameError", fullNameError);

            isErrorEmpty = ValidatorService.validateFields(new String[]{
                    emailError,
                    passwordError,
                    retypePasswordError,
                    fullNameError
            });

            String url = ApplicationConstant.REGISTRATION_PAGE_URL;

            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("retypePassword", retypePassword);
            req.setAttribute("fullName", fullName);

            if (isErrorEmpty) {
                req.setAttribute("errorMessage", "Registration failed. Please try again");
                int userId;
                if ((userId = dbService.storeUser(email, password, fullName)) > 0) {
                    HttpSession session = req.getSession();
                    session.setAttribute(ApplicationConstant.APP_USER, new User(userId, fullName));
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
