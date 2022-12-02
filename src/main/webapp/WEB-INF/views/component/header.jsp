<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>HW#04</title>

    <link rel="icon"
          href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">

    <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css"
          media="screen"/>

    <link href="${pageContext.request.contextPath}/static/css/registration.css" rel="stylesheet" type="text/css"
          media="screen"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery.poptrox-0.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/axios.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/main.js"></script>
</head>
<body>
<div id="header" class="container">
    <%@ include file="/WEB-INF/views/component/logo.jsp" %>
    <%@ include file="/WEB-INF/views/component/menu.jsp" %>
</div>
<!-- end #header -->
<%@ include file="/WEB-INF/views/component/gallery.jsp" %>
<div id="page">
    <div id="bg1">
        <div id="bg2">
            <div id="bg3">
                <div id="content">
