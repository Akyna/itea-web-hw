<%--TODO: discuss --%>
<%--<div class='top-menu'>--%>
<%--    ${requestScope.isLoggedSuccessfully ne null--%>
<%--            ? (requestScope.isLoggedSuccessfully eq true--%>
<%--            ? "<a href='"+= pageContext.request.contextPath +="?logout'>Logout</a><br/>"--%>
<%--            : "<a href='"+= pageContext.request.contextPath +="'>Login</a><br/>")--%>
<%--            : ""}--%>
<%--</div>--%>

<div class='top-menu'>
    ${requestScope.isShowLoginLink ? "<a href='"+= pageContext.request.contextPath +="/'>Login</a><br/>" : ""}
    ${requestScope.isShowRegisterLink ? "<a href='"+= pageContext.request.contextPath +="/registration'>Registration</a><br/>" : ""}
    ${requestScope.isShowSecretLink ? "<a href='"+= pageContext.request.contextPath +="/secret'>Secret</a><br/>" : ""}
    ${requestScope.isShowLogoutLink ? "<a href='"+= pageContext.request.contextPath +="/?logout'>Logout</a><br/>" : ""}
</div>
