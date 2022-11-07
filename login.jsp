<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.Arrays" %>


<%! private final String EMAIL_REGEXP = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"; %>
<%! private final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"; %>

<%
    // TODO: For test purposes
    // Object isRegistrationWasSuccessful = request.getSession().getAttribute("isRegistrationSuccessful");
%>

<%
    boolean isLoginSuccessful = false;
    // TODO: For test purposes
    // Object isRegistrationWasSuccessful = request.getSession().getAttribute("isRegistrationSuccessful");
    String submit_form = trimInput(request.getParameter("submit"));
    String email = trimInput(request.getParameter("email"));
    String password = trimInput(request.getParameter("password"));

    String emailError = "";
    String passwordError = "";

    if ("Confirm".equals(submit_form)) {
        emailError = getMailError(email);
        passwordError = getPasswordError(password);


        isLoginSuccessful = chekRegistration(new String[]{
                emailError,
                passwordError,
        });
    }
%>

<%!
    private String getMailError(String data) {
        String result;
        result = checkIsNotEmpty(data);
        if (!result.isEmpty()) {
            return result;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(data);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return "Email address is invalid";
        }

        return "";
    }

    private String getPasswordError(String data) {
        String result;
        result = checkIsNotEmpty(data);
        if (!result.isEmpty()) {
            return result;
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEXP);
        Matcher matcher = pattern.matcher(data);
        boolean matchFound = matcher.find();
        if (!matchFound) {
            return "The password must contain at least 8 characters, there are capital letters and at least 1 digit";
        }
        return result;
    }

    private String checkIsNotEmpty(String data) {
        if (data == null) {
            return "Must be filled";
        }

        if (data.isBlank()) {
            return "Must be not blank";
        }

        return "";
    }


    private String trimInput(String field) {
        if (field != null) {
            return field.trim();
        }
        return null;
    }

    private boolean chekRegistration(String[] strings) {
        return Arrays.stream(strings).allMatch(String::isEmpty);
    }
%>

<%@ include file="/sections/header.jsp" %>

<div class="main-container-wrapper">
    <div class="main-container">
        <% if (isLoginSuccessful) { %>
        <h3>Login is successful</h3>
        <% } else { %>
        <form class="main-form" method="post" action="">
            <%--EMAIL--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Email</div>
                    <label>
                        <input
                                type="email"
                                name="email"
                                pattern="<%= EMAIL_REGEXP %>"
                                value="<%= email != null ? email : "" %>"
                                required
                        />
                    </label>
                </div>
                <div class="input-error">
                    <%out.write(emailError);%>
                </div>
            </div>
            <%--PASSWORD--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Password</div>
                    <label>
                        <input
                                type="password"
                                name="password"
                                pattern="<%= PASSWORD_REGEXP %>"
                                value="<%= password != null ? password : "" %>"
                                required
                        />
                    </label>
                </div>
                <div class="input-error">
                    <% out.write(passwordError); %>
                </div>
            </div>
            <%--SUBMIT--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <input type="submit" value="Confirm" name="submit"/>
                </div>
                <div class="input-error"></div>
            </div>
        </form>
        <% } %>
    </div>
</div>

<%@ include file="/sections/footer.jsp" %>