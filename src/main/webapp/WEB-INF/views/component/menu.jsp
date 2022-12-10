<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>

<div id="menu">
    <ul>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}">Головна</a></li>
        <li><a href="${pageContext.request.contextPath}/products">Товари</a></li>

        ${!sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/registration'>Реєстрація</a></li>" : ""}
        ${!sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/login'>Вхід</a></li>" : ""}
        ${sessionScope[ApplicationConstant.APP_USER_IS_AUTHORIZED] ? "<li><a href='"+= pageContext.request.contextPath +="/login?logout'>Вийти</a></li>" : ""}

        <li><a class="app-cat" href="${pageContext.request.contextPath}/cart">Кошик
            <div id="app-cart-count-quantity"
                 class="app-cart-count ${sessionScope[ApplicationConstant.APP_CART] == null ? "hidden-content":""}">
                ${sessionScope[ApplicationConstant.APP_CART].size()}
            </div>
        </a></li>
    </ul>
</div>