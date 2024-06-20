<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GoWithMe</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/styles.css"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/action.js"></script>
</head>
<body>

<!-- Navbar (sit on top) -->

<div class="w3-top">
    <div class="w3-bar w3-white w3-card" id="myNavbar">
        <a href="/gowithme/app" class="w3-bar-item w3-button w3-wide">GoWithME</a>
        <!-- Right-sided navbar links -->
        <div class="w3-right w3-hide-small">

            <sec:authorize access="hasAnyRole('SUPER_ADMIN','ADMIN')">
                <a href="/gowithme/admin" class="w3-bar-item w3-button"><i class="fa fa-jedi"></i> Admin</a>
            </sec:authorize>
            <a href="/gowithme/app" class="w3-bar-item w3-button"><i class="fa fa-magnifying-glass"></i> Szukaj</a>
            <a href="/gowithme/app/activity/add" class="w3-bar-item w3-button"><i class="fa fa-person-running"></i>
                Dodaj aktywność</a>
            <a class="w3-bar-item w3-button" id="notification"><i class="fa fa-bell"></i> Powiadomienia</a>
            <div class="dropdown-notification" id="dropdown-notification">
                <c:forEach items="${sessionScope.notificationsList}" var="notification">
                    <div class="dropdown-notification-item ">${notification.name}</div>
                </c:forEach>

            </div>
            <a class="w3-bar-item w3-button " id="user-icon"><i class="fa fa-user"></i> <i class="fa fa-list"></i></a>
            <div class="dropdown-menu" id="dropdown-menu">
                <a href="/gowithme/app/profile">
                    <div class="dropdown-item">Profil</div>
                </a>
                <a href="<c:url value='/gowithme/app/logout'/>">
                    <div class="dropdown-item">Wyloguj</div>
                </a>
            </div>


        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->

        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
           onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
</div>

<!-- Sidebar on small screens when clicking the menu icon -->
<%--<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium " style="display:none"--%>
<%--     id="mySidebar">--%>
<%--    <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>--%>
<%--    <a href="/gowithme/app/main" onclick="w3_close()" class="fa fa-user w3-button">Szukaj</a>--%>
<%--    <a href="/gowithme/app/add_activity" onclick="w3_close()" class="w3-bar-item w3-button">Dodaj aktywność</a>--%>
<%--    <a href="/gowithme/app/random" onclick="w3_close()" class="fa fa-ring w3-button">Powiadomienia</a>--%>
<%--    <a href="/gowithme/app/profile" onclick="w3_close()" class="w3-bar-item w3-user">Profil</a>--%>
<%--  --%>
<%--</nav>--%>
