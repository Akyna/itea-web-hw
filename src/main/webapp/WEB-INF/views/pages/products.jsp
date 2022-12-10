<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/component/header.jsp" %>


<div class="categories-wrapper">
    <c:forEach items="${sessionScope[ApplicationConstant.APP_CATEGORIES]}" var="category">
        <a href="${pageContext.request.contextPath}/products?categoryId=${category.id}">
            <div class="category-item">
                <div class="category-img-wrapper">
                    <img class="category-img" src="${pageContext.request.contextPath}/${category.image}"
                         alt="" title=${category.title}/>
                </div>
                <p>${category.title}</p>
            </div>
        </a>

    </c:forEach>
</div>


<%@ include file="/WEB-INF/views/component/footer.jsp" %>

