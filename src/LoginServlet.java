package com.amboiko;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        Common common = new Common();
        common.setContentRoot(req.getContextPath());

        StringBuilder output = new StringBuilder();
        output.append(Common.HEADER);
        output.append(common.getMenu());
        output.append(getForm(req));
        output.append(Common.FOOTER);

        PrintWriter out = resp.getWriter();
        out.println(output);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    private String getForm(HttpServletRequest request) {
        boolean isLoginSuccessful = false;
        String submit_form = Common.trimInput(request.getParameter("submit"));
        String email = Common.trimInput(request.getParameter("email"));
        String password = Common.trimInput(request.getParameter("password"));

        String emailError = "";
        String passwordError = "";

        if ("Confirm".equals(submit_form)) {
            emailError = Common.getMailError(email);
            passwordError = Common.getPasswordError(password);


            isLoginSuccessful = Common.chekRegistration(new String[]{
                    emailError,
                    passwordError,
            });
            if (isLoginSuccessful) {
                return "<h3>Login is successful</h3>";
            }
        }

        return "<div class='main-container-wrapper'>" +
                "    <div class='main-container'>" +
                "        <form class='main-form' method='post' action=''>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Email</div>" +
                "                    <label>" +
                "                        <input" +
                "                                type='email'" +
                "                                name='email'" +
                "                                pattern='" + Common.EMAIL_REGEXP + "'" +
                "                                value='" + (email != null ? email : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + emailError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Password</div>" +
                "                    <label>" +
                "                        <input" +
                "                                type='password'" +
                "                                name='password'" +
                "                                pattern='" + Common.PASSWORD_REGEXP + "'" +
                "                                value='" + (password != null ? password : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + passwordError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <input type='submit' value='Confirm' name='submit'/>" +
                "                </div>" +
                "                <div class='input-error'></div>" +
                "            </div>" +
                "        </form>" +
                "    </div>" +
                "</div>";
    }
}
