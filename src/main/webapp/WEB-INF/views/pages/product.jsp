<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/component/header.jsp" %>

<div class="categories-wrapper">
    <div class="category-item half">
        <div class="category-img-wrapper detail-info">
            <img class="category-img"
                 src="${pageContext.request.contextPath}/${requestScope[ApplicationConstant.APP_PRODUCT].image}"
                 alt="" title=${requestScope[ApplicationConstant.APP_PRODUCT].title}/>
        </div>
    </div>
    <div class="category-item half text-container">
        <div class="info-line">
            <p>Title</p>
            <p>${requestScope[ApplicationConstant.APP_PRODUCT].title}</p>
        </div>
        <div class="info-line">
            <p>Description</p>
            <p>${requestScope[ApplicationConstant.APP_PRODUCT].description}</p>
        </div>
        <div class="info-line">
            <p>Price</p>
            <p>${requestScope[ApplicationConstant.APP_PRODUCT].price}</p>
        </div>
        <div class="empty-flex"></div>
        <input class="buy-button" type="submit" value="BUY" onclick="addProduct(${requestScope[ApplicationConstant.APP_PRODUCT].id})"/>
    </div>
</div>


<%@ include file="/WEB-INF/views/component/footer.jsp" %>

