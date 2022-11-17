package com.amboiko.services;

public class WebPageService {
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
    public static final String MAIN_CONTAINER_START = "<div class='main-container-wrapper'><div class='main-container'>";
    public static final String MAIN_CONTAINER_END = "</div></div>";
    public static final String FORM_START = "<form class='main-form' method='post' action=''>";
    public static final String FORM_END = " </form>";
    public static final String FOOTER = "</body></html>";
    private String contentRoot;

    public static String getFormRow(String regExp, String fieldName, String field, String fieldError) {
        return "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "</div>" +
                "                    <label>" +
                "                        <input" +
                "                                name='" + fieldName + "'" +
                "                                pattern='" + regExp + "'" +
                "                                value='" + (field != null ? field : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + fieldError +
                "                </div>" +
                "            </div>";
    }

    public static String getFormText(String fieldName, String field, String fieldError) {
        return "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "</div>" +
                "                    <label>" +
                "                        <input" +
                "                                type='text'" +
                "                                name='" + fieldName + "'" +
                "                                value='" + (field != null ? field : "") + "'" +
                "                                required" +
                "                        />" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + fieldError +
                "                </div>" +
                "            </div>";
    }

    public static String getFormSelect(String fieldName, String field, String[] options, String fieldError) {
        return "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "</div>" +
                "                    <label>" +
                "                        <select name='" + fieldName + "' required>" + getSelectOptions(field, options) +
                "                        </select>" +
                "                    </label>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + fieldError +
                "                </div>" +
                "            </div>";
    }

    private static String getSelectOptions(String field, String[] fields) {
        StringBuilder builder = new StringBuilder();

        for (String item : fields) {
            builder.append("<option").append(ValidatorService.checkSelectedRegion(field, item)).append(" value='").append(item).append("'>").append(item).append("</option>");
        }

        return builder.toString();

    }

    public static String getFormRadio(String fieldName, String field, String[] options, String fieldError) {
        return "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <div>" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1) + "</div>" +
                "                    <div class='wide-input'>" + getRadioOptions(fieldName, field, options) +
                "                    </div>" +
                "                </div>" +
                "                <div class='input-error'>" +
                "                    " + fieldError +
                "                </div>" +
                "            </div>";
    }

    public static String getRadioOptions(String fieldName, String field, String[] fields) {

        StringBuilder builder = new StringBuilder();

        for (String item : fields) {
            builder.append("<label> ").append(item).append(" <input").append(ValidatorService.isChecked(field, item)).append(" type='radio' value='").append(item).append("' name='").append(fieldName).append("' required/></label>");
        }

        return builder.toString();


    }

    public static String getFormSubmit() {
        return "            <div class='form-row'>" +
                "                <div class='input-wrapper'>" +
                "                    <input type='submit' value='Confirm' name='submit'/>" +
                "                </div>" +
                "                <div class='input-error'></div>" +
                "            </div>" +
                "        </form>";
    }

    public void setContentRoot(String contentRoot) {
        this.contentRoot = contentRoot;
    }

    public String getMenu() {
        return "<div class='top-menu'>" +
                "<a href='" + contentRoot + "'>Login</a><br/>" +
                "<a href='" + contentRoot + "/registration'>Registration</a><br/>" +
                "<a href='" + contentRoot + "/secret'>Secret</a><br/>" +
                "</div>";
    }

    public String getLogOut() {
        return "<div class='top-menu'>" +
                "<a href='" + contentRoot + "/logout'>Logout</a><br/>" +
                "</div>";
    }


}
