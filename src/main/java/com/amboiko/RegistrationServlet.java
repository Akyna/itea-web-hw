package com.amboiko;

import com.amboiko.services.DBService;
import com.amboiko.services.FormFactory;
import com.amboiko.services.Validators;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = -2085861963398905708L;
    Logger logger;
    DBService dbService;
    FormFactory formFactory;


    @Override
    public void init() {
        logger = Logger.getLogger(LoginServlet.class.getName());
        dbService = new DBService();
        formFactory = new FormFactory();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        FormFactory formFactory = new FormFactory();
        formFactory.setContentRoot(req.getContextPath());

        StringBuilder output = new StringBuilder();
        output.append(FormFactory.HEADER);
        output.append(formFactory.getMenu());
        output.append(getForm(req));
        output.append(FormFactory.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    private String getForm(HttpServletRequest request) {
        boolean isErrorEmpty;

        String submit_form = Validators.trimInput(request.getParameter("submit"));
        String email = Validators.trimInput(request.getParameter("email"));
        String password = Validators.trimInput(request.getParameter("password"));
        String retypePassword = Validators.trimInput(request.getParameter("retypePassword"));
        String fullName = Validators.trimInput(request.getParameter("fullName"));

        String emailError = "";
        String passwordError = "";
        String retypePasswordError = "";
        String fullNameError = "";

        if ("Confirm".equals(submit_form)) {
            emailError = Validators.getFieldError(email, Validators.EMAIL_REGEXP, "Email address is invalid");
            passwordError = Validators.getFieldError(password, Validators.PASSWORD_REGEXP, "The password must contain at least 8 characters, there are capital letters and at least 1 digit");

            retypePasswordError = Validators.getRetypePasswordError(password, retypePassword);
            fullNameError = Validators.checkIsNotEmpty(fullName);


            isErrorEmpty = Validators.chekRegistration(new String[]{
                    emailError,
                    passwordError,
                    retypePasswordError,
                    fullNameError,
            });


            if (isErrorEmpty && dbService.storeUser(email, password, fullName)) {
                return "<h3>Registration is successful</h3>";
            } else {
                return "<h3>Registration failed. Please try again</h3>";
            }


        }

        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append(FormFactory.MAIN_CONTAINER_START);

        formBuilder.append(FormFactory.FORM_START);
        formBuilder.append(FormFactory.getFormRow(Validators.EMAIL_REGEXP, "email", email, emailError));
        formBuilder.append(FormFactory.getFormRow(Validators.PASSWORD_REGEXP, "password", password, passwordError));
        formBuilder.append(FormFactory.getFormRow(Validators.PASSWORD_REGEXP, "retypePassword", retypePassword, retypePasswordError));
        formBuilder.append(FormFactory.getFormText("fullName", fullName, fullNameError));


        formBuilder.append(FormFactory.getFormSubmit());
        formBuilder.append(FormFactory.FORM_END);

        formBuilder.append(FormFactory.MAIN_CONTAINER_END);

        return formBuilder.toString();

    }


}
