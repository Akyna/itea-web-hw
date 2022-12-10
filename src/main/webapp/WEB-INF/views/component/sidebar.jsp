<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="sidebar">
    <h2>Товари</h2>
    <ul>
        <c:forEach items="${sessionScope[ApplicationConstant.APP_CATEGORIES]}" var="category">
            <li><a href="${pageContext.request.contextPath}/products?categoryId=${category.id}">${category.title}</a>
            </li>
        </c:forEach>
        ${!sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/registration'>Реєстрація</a></li>" : ""}
        ${!sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/login'>Вхід</a></li>" : ""}
        ${sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/login?logout'>Вийти</a></li>" : ""}
        <li><a href="${pageContext.request.contextPath}/cart">Кошик</a></li>
    </ul>
</div>