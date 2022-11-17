package com.amboiko;

import com.amboiko.model.User;
import com.amboiko.services.DBService;
import com.amboiko.services.SessionService;
import com.amboiko.services.ValidatorService;
import com.amboiko.services.WebPageService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -5031217977766334744L;
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

        StringBuilder output = new StringBuilder();
        output.append(WebPageService.HEADER);
        output.append(webPageService.getMenu());

        HttpSession session = req.getSession();
        User user;
        Object sessionAttribute = session.getAttribute(SessionService.USER_ID);
        if (sessionAttribute != null && ((user = dbService.getUserById((int) sessionAttribute)) != null)) {
            output.append(webPageService.getLogOut());
            output.append("<h3>Hello, ")
                    .append(user.getUserName())
                    .append(". You are already authorized.</h3>");
        } else {
            output.append(getForm(req, resp));
        }

        output.append(WebPageService.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    private String getForm(HttpServletRequest request, HttpServletResponse resp) {
        boolean isErrorEmpty;
        String submit_form = ValidatorService.trimInput(request.getParameter("submit"));
        String email = ValidatorService.trimInput(request.getParameter("email"));
        String password = ValidatorService.trimInput(request.getParameter("password"));

        String emailError = "";
        String passwordError = "";

        if ("Confirm".equals(submit_form)) {
            emailError = ValidatorService.getFieldError(email, ValidatorService.EMAIL_REGEXP, "Email address is invalid");
            passwordError = ValidatorService.getFieldError(password, ValidatorService.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");


            isErrorEmpty = ValidatorService.chekRegistration(new String[]{
                    emailError,
                    passwordError,
            });

            User user;
            if (isErrorEmpty && ((user = dbService.getUser(email, password)) != null)) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionService.USER_ID, user.getId());
                try {
                    resp.sendRedirect(request.getContextPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                return "<h3>User not found. Please try login again</h3>";
            }
        }

        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append(WebPageService.MAIN_CONTAINER_START);
        formBuilder.append(WebPageService.FORM_START);
        formBuilder.append(WebPageService.getFormRow(ValidatorService.EMAIL_REGEXP, "email", email, emailError));
        formBuilder.append(WebPageService.getFormRow(ValidatorService.PASSWORD_REGEXP, "password", password, passwordError));
        formBuilder.append(WebPageService.getFormSubmit());
        formBuilder.append(WebPageService.FORM_END);

        formBuilder.append(WebPageService.MAIN_CONTAINER_END);

        return formBuilder.toString();
    }
}
