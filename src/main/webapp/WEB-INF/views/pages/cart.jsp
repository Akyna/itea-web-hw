<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.amboiko.utils.ApplicationConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/WEB-INF/views/component/header.jsp" %>

${sessionScope[ApplicationConstant.APP_CART] == null ? "<h3>Cart is empty</h3>": ""}

<%--TODO: discuss if this correct approach--%>
<%--${myVariable = 0 ; ""}--%>

<div>
    <c:forEach items="${sessionScope[ApplicationConstant.APP_CART]}" var="entry">
        <div class="categories-wrapper cart-view">
            <div class="category-item half">
                <div class="category-img-wrapper detail-info">
                    <img class="category-img"
                         src="${pageContext.request.contextPath}/${entry.key.image}"
                         alt="" title=${entry.key.title}/>
                </div>
            </div>
            <div class="category-item half text-container">
                <div class="info-line">
                    <p>Title</p>
                    <p>${entry.key.title}</p>
                </div>
                <div class="info-line">
                    <p>Description</p>
                    <p>${entry.key.description}</p>
                </div>
                <div class="info-line">
                    <p>Price</p>
                    <p>${entry.key.price}</p>
                </div>
                <div class="empty-flex"></div>
                <div class="info-line">
                    <p>Quantity</p>
                    <p>${entry.value}</p>
                </div>
                <div class="info-line">
                    <p>Total</p>
                    <p>${entry.key.price * entry.value}</p>
                </div>
            </div>
        </div>
        <%--TODO: discuss if this correct approach--%>
        <%--${myVariable = myVariable + entry.key.price * entry.value; ''}--%>
    </c:forEach>
</div>
<%--TODO: discuss if this correct approach--%>
<%--<div>--%>
<%--    ${myVariable > 0 ? myVariable : ''}--%>
<%--</div>--%>

<%@ include file="/WEB-INF/views/component/footer.jsp" %>