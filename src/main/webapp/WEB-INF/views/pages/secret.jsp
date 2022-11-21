<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.services.SessionService" %>

<%@ include file="../component/header.jsp" %>

<jsp:include page="../component/menu.jsp"/>

<h3>Hello, ${sessionScope[SessionService.APP_USER].userName}. You are already inside a secret place.</h3>

<%@ include file="../component/footer.jsp" %>