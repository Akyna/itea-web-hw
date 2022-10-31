<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.lang.Math" %>

<%!
    String firstColor = "red-bg";
    String secondColor = "yellow-bg";

    public String getColor(int rowIndex, int colIndex) {
        // TODO: not readable but done in one line :-)
        return rowIndex % 2 == 0 ? colIndex % 2 == 0 ? firstColor : secondColor : colIndex % 2 == 0 ? secondColor : firstColor;
    }

    public String getColorSecondApproach(int rowIndex, int colIndex) {
        String firstColor = "red-bg";
        String secondColor = "yellow-bg";

        if (rowIndex % 2 != 0) {
            firstColor = "yellow-bg";
            secondColor = "red-bg";
        }
        return colIndex % 2 == 0 ? firstColor : secondColor;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>JAVA WEB HW#1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>

</head>
<body>
<div class="main-form">
    <p>Fill the data and press <code>OK</code></p>
    <form>
        <label>
            ROW
            <input type="number" name="row" min="1" max="20" required/>
        </label>
        <label>
            COLUMNS
            <input type="number" name="columns" min="1" max="20" required/>
        </label>
        <input type="submit" value="OK"/>
    </form>
</div>
<div class="result-table">
    <%
        String row = request.getParameter("row");
        String columns = request.getParameter("columns");

        if (row != null && columns != null) {
            try {
                int rowNumber = Math.abs(Integer.parseInt(row));
                int colNumber = Math.abs(Integer.parseInt(columns));

                StringBuilder result = new StringBuilder("<table>");

                for (int i = 0; i < rowNumber; i++) {
                    result.append("<tr>");

                    for (int j = 0; j < colNumber; j++) {
                        result.append("<td class=")
                                .append(getColor(i, j))

                                // We can swap a color in a simple way - just add 1 to the first parameter
                                // .append(getColor(i + 1, j))

                                // Another way - more readable
                                // .append(getColorSecondApproach(i, j))

                                .append(">")
                                // .append(i * colNumber + j + 1)
                                .append(i)
                                .append(":")
                                .append(j)
                                .append("</td>");
                    }
                    result.append("</tr>");
                }
                result.append("</table>");
                out.write(result.toString());
            } catch (NumberFormatException e) {
                String error = "<p>Are you serious? Did you type \"" +
                        row + "\" and \"" + columns +
                        "\" and want to see the table? Phew...</p>";
                out.write(error);
            }
        }
    %>
</div>
</body>
</html>
