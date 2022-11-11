package com.amboiko;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {
    public static final String HEADER = "<!doctype html>" +
            "<html lang='en'><head><title>HW05</title>" +
            "<style>.main-container-wrapper {" +
            "    display: flex;" +
            "    justify-content: center;" +
            "    align-items: center;" +
            "    flex-direction: column;" +
            "}" +
            "" +
            "div.main-container-wrapper .main-form, div.main-container-wrapper p {" +
            "    color: #8A8A8A;" +
            "}" +
            "" +
            ".main-container {" +
            "    height: 100%;" +
            "    width: 100%;" +
            "    display: flex;" +
            "    flex-direction: column;" +
            "    align-content: center;" +
            "    align-items: center;" +
            "    justify-content: flex-start;" +
            "}" +
            "" +
            ".main-form {" +
            "    width: 90%;" +
            "}" +
            "" +
            "select, .wide-input {" +
            "    width: 147px;" +
            "}" +
            "" +
            ".form-row {" +
            "    display: flex;" +
            "    justify-content: center;" +
            "    align-items: center;" +
            "    padding: 4px 0 4px 0;" +
            "}" +
            "" +
            ".input-wrapper div.wide-input {" +
            "    padding: 0;" +
            "}" +
            "" +
            ".input-wrapper div, .input-error {" +
            "    padding: 0 10px 0 10px;" +
            "}" +
            "" +
            "input[type=\"checkbox\" i] {" +
            "    margin: 0;" +
            "}" +
            "" +
            "div h3 {" +
            "    text-align: center;" +
            "    text-transform: uppercase;" +
            "}" +
            "" +
            "div.input-error {" +
            "    flex: 2;" +
            "    color: red;" +
            "}" +
            "" +
            ".input-wrapper {" +
            "    flex: 3;" +
            "    display: flex;" +
            "    justify-content: flex-end;" +
            "    align-items: center;" +
            "}" +
            "" +
            "textarea {" +
            "    width: 141px;" +
            "    height: 50px;" +
            "    resize: none;" +
            "}" +
            "" +
            "div.input-wrapper div.wide-input label.single-checkbox {" +
            "    display: flex;" +
            "}</style>" +
            "</head>" +
            "<body>";

    public static final String FOOTER = "</body></html>";

    public static final String EMAIL_REGEXP = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    public static final String PASSWORD_REGEXP = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

    private String contentRoot;

    public void setContentRoot(String contentRoot) {
        this.contentRoot = contentRoot;
    }

    public String getMenu() {
        return "<div class='top-menu'>" +
                "<a href='" + contentRoot + "'>Login</a><br/>" +
                "<a href='" + contentRoot + "/registration'>Registration</a>" +
                "</div>";
    }

    public static String getMailError(String data) {
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

    public static String getPasswordError(String data) {
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

    public static String getRetypePasswordError(String password, String data) {
        if (!password.equals(data)) {
            return "Passwords do not match";
        }
        return "";
    }

    public static String checkIsNotEmpty(String data) {
        if (data == null) {
            return "Must be filled";
        }

        if (data.isBlank()) {
            return "Must be not blank";
        }

        return "";
    }

    public static String checkIsSelected(String data) {
        if (data == null || data.isBlank()) {
            return "Must be selected";
        }

        return "";
    }

    public static String checkSelectedRegion(String region, String option) {
        if (region != null && region.equals(option)) {
            return "selected";
        }
        return "";
    }

    public static String isChecked(String field, String option) {
        if (field != null && field.equals(option)) {
            return "checked";
        }
        return "";
    }

    public static String trimInput(String field) {
        if (field != null) {
            return field.trim();
        }
        return null;
    }

    public static boolean chekRegistration(String[] strings) {
        return Arrays.stream(strings).allMatch(String::isEmpty);
    }
}
