<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.Arrays" %>

<%! private final String EMAIL_REGEXP = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$"; %>
<%! private final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"; %>




<%
    boolean isRegistrationSuccessful = false;
    // TODO: For test purposes
    // Object isRegistrationWasSuccessful = request.getSession().getAttribute("isRegistrationSuccessful");
    String submit_form = trimInput(request.getParameter("submit"));
    String email = trimInput(request.getParameter("email"));
    String password = trimInput(request.getParameter("password"));
    String retypePassword = trimInput(request.getParameter("retypePassword"));
    String fullName = trimInput(request.getParameter("fullName"));
    String region = trimInput(request.getParameter("region"));
    String gender = trimInput(request.getParameter("gender"));
    String comment = trimInput(request.getParameter("comment"));
    String agreement = trimInput(request.getParameter("agreement"));

    String emailError = "";
    String passwordError = "";
    String retypePasswordError = "";
    String fullNameError = "";
    String regionError = "";
    String genderError = "";
    String commentError = "";
    String agreementError = "";

    if ("Confirm".equals(submit_form)) {
        emailError = getMailError(email);
        passwordError = getPasswordError(password);
        retypePasswordError = getRetypePasswordError(password, retypePassword);
        fullNameError = checkIsNotEmpty(fullName);
        regionError = checkIsNotEmpty(region);
        genderError = checkIsSelected(gender);
        commentError = checkIsNotEmpty(comment);
        agreementError = checkIsSelected(agreement);


        isRegistrationSuccessful = chekRegistration(new String[]{
                emailError,
                passwordError,
                retypePasswordError,
                fullNameError,
                regionError,
                genderError,
                commentError,
                agreementError
        });

        // TODO: For test purposes
        // if (isRegistrationSuccessful) {
        //     request.getSession().setAttribute("isRegistrationSuccessful", isRegistrationSuccessful);
        // }
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

    private String getRetypePasswordError(String password, String data) {
        if (!password.equals(data)) {
            return "Passwords do not match";
        }
        return "";
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

    private String checkIsSelected(String data) {
        if (data == null || data.isBlank()) {
            return "Must be selected";
        }

        return "";
    }

    private String checkSelectedRegion(String region, String option) {
        if (region != null && region.equals(option)) {
            return "selected";
        }
        return "";
    }

    private String isChecked(String field, String option) {
        if (field != null && field.equals(option)) {
            return "checked";
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
        <% if (isRegistrationSuccessful) { %>
        <h3>Registration is successful</h3>
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
            <%--RETYPE PASSWORD--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Retype Password</div>
                    <label>
                        <input
                                type="password"
                                name="retypePassword"
                                pattern="<%= PASSWORD_REGEXP %>"
                                value="<%= retypePassword != null ? retypePassword : "" %>"
                                required
                        />
                    </label>
                </div>
                <div class="input-error">
                    <% out.write(retypePasswordError); %>
                </div>
            </div>
            <%--FULL NAME--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Full name</div>
                    <label>
                        <input
                                type="text"
                                name="fullName"
                                value="<%= fullName != null ? fullName : "" %>"
                                required
                        />
                    </label>
                </div>
                <div class="input-error">
                    <% out.write(fullNameError); %>
                </div>
            </div>
            <%--REGION--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Region</div>
                    <label>
                        <select name="region" required>
                            <option <%= checkSelectedRegion(region, "Kyiv") %> value="Kyiv">Kyiv</option>
                            <option <%= checkSelectedRegion(region, "Lviv") %> value="Lviv">Lviv</option>
                            <option <%= checkSelectedRegion(region, "Kharkiv") %> value="Kharkiv">Kharkiv</option>
                            <option <%= checkSelectedRegion(region, "Odessa") %> value="Odessa">Odessa</option>
                        </select>
                    </label>
                </div>
                <div class="input-error">
                    <% out.write(regionError); %>
                </div>
            </div>
            <%--GENDER--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Gender</div>
                    <div class="wide-input">
                        <label>
                            F <input <%= isChecked(gender, "F") %> type="radio" value="F" name="gender" required/>
                        </label>
                        <label>
                            M <input <%= isChecked(gender, "M") %> type="radio" value="M" name="gender" required/>
                        </label>
                    </div>
                </div>
                <div class="input-error">
                    <% out.write(genderError); %>
                </div>
            </div>
            <%--COMMENT--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>Comment</div>
                    <label>
                        <textarea maxlength="100" name="comment"><%= comment != null ? comment : "" %></textarea>
                    </label>
                </div>
                <div class="input-error">
                    <% out.write(commentError); %>
                </div>
            </div>
            <%--AGREEMENT--%>
            <div class="form-row">
                <div class="input-wrapper">
                    <div>I agree terms</div>
                    <div class="wide-input">
                        <label class="single-checkbox">
                            <input <%= isChecked(agreement, "agree") %> type="checkbox" name="agreement" value="agree"/>
                        </label>
                    </div>
                </div>
                <div class="input-error">
                    <% out.write(agreementError); %>
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