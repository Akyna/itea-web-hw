<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>

<%@ include file="/WEB-INF/views/component/header.jsp" %>


<h3>Hello, ${sessionScope[ApplicationConstant.APP_USER].userName}. You are already inside a secret place.</h3>

<%@ include file="/WEB-INF/views/component/footer.jsp" %>