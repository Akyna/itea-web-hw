package com.amboiko;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationServlet extends HttpServlet {

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
        boolean isRegistrationSuccessful = false;
        // TODO: For test purposes
        // Object isRegistrationWasSuccessful = request.getSession().getAttribute("isRegistrationSuccessful");
        String submit_form = Common.trimInput(request.getParameter("submit"));
        String email = Common.trimInput(request.getParameter("email"));
        String password = Common.trimInput(request.getParameter("password"));
        String retypePassword = Common.trimInput(request.getParameter("retypePassword"));
        String fullName = Common.trimInput(request.getParameter("fullName"));
        String region = Common.trimInput(request.getParameter("region"));
        String gender = Common.trimInput(request.getParameter("gender"));
        String comment = Common.trimInput(request.getParameter("comment"));
        String agreement = Common.trimInput(request.getParameter("agreement"));

        String emailError = "";
        String passwordError = "";
        String retypePasswordError = "";
        String fullNameError = "";
        String regionError = "";
        String genderError = "";
        String commentError = "";
        String agreementError = "";

        if ("Confirm".equals(submit_form)) {
            emailError = Common.getMailError(email);
            passwordError = Common.getPasswordError(password);
            retypePasswordError = Common.getRetypePasswordError(password, retypePassword);
            fullNameError = Common.checkIsNotEmpty(fullName);
            regionError = Common.checkIsNotEmpty(region);
            genderError = Common.checkIsSelected(gender);
            commentError = Common.checkIsNotEmpty(comment);
            agreementError = Common.checkIsSelected(agreement);


            isRegistrationSuccessful = Common.chekRegistration(new String[]{
                    emailError,
                    passwordError,
                    retypePasswordError,
                    fullNameError,
                    regionError,
                    genderError,
                    commentError,
                    agreementError
            });

            if (isRegistrationSuccessful) {
                return "<h3>Registration is successful</h3>";
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
                "                    <div>Retype Password</div>" +
                "                    <label>" +
                "                        <input" +
                "                                type='password'" +
                "                                name='retypePassword'" +
                "                                pattern='" + Common.PASSWORD_REGEXP + "'" +
                "                                value='" + (retypePassword != null ? retypePassword : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + retypePasswordError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Full name</div>" +
                "                    <label>" +
                "                        <input" +
                "                                type='text'" +
                "                                name='fullName'" +
                "                                value='" + (fullName != null ? fullName : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + fullNameError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Region</div>" +
                "                    <label>" +
                "                        <select name='region' required>" +
                "                            <option " + Common.checkSelectedRegion(region, "Kyiv") + " value='Kyiv'>Kyiv</option>" +
                "                            <option " + Common.checkSelectedRegion(region, "Lviv") + " value='Lviv'>Lviv</option>" +
                "                            <option " + Common.checkSelectedRegion(region, "Kharkiv") + " value='Kharkiv'>Kharkiv</option>" +
                "                            <option " + Common.checkSelectedRegion(region, "Odessa") + " value='Odessa'>Odessa</option>" +
                "                        </select>" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + regionError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Gender</div>" +
                "                    <div class='wide-input'>" +
                "                        <label>" +
                "                            F <input " + Common.isChecked(gender, "F") + " type='radio' value='F' name='gender' required/>" +
                "                        </label>" +
                "                        <label>" +
                "                            M <input " + Common.isChecked(gender, "M") + " type='radio' value='M' name='gender' required/>" +
                "                        </label>" +
                "                    </div>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + genderError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>Comment</div>" +
                "                    <label>" +
                "                        <textarea maxlength='100' name='comment'>" + (comment != null ? comment : "") + "</textarea>" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + commentError +
                "                </div>" +
                "            </div>" +
                "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>I agree terms</div>" +
                "                    <div class='wide-input'>" +
                "                        <label class='single-checkbox'>" +
                "                            <input " + Common.isChecked(agreement, "'agree'") + " type='checkbox' name='agreement' value='agree'/>" +
                "                        </label>" +
                "                    </div>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + agreementError +
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
