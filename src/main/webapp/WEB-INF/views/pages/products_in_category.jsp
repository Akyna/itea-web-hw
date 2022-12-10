<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/component/header.jsp" %>


<div class="categories-wrapper">
    <c:forEach items="${requestScope[ApplicationConstant.APP_PRODUCTS]}" var="product">
        <a href="${pageContext.request.contextPath}/product?id=${product.id}">
            <div class="category-item">
                <div class="category-img-wrapper">
                    <img class="category-img" src="${pageContext.request.contextPath}/${product.image}"
                         alt="" title=${product.title}/>
                </div>
                <p>${product.title}</p>
            </div>
        </a>

    </c:forEach>
</div>


<%@ include file="/WEB-INF/views/component/footer.jsp" %>

