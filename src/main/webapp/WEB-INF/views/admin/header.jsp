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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
        <a href="/gowithme/admin" class="w3-bar-item w3-button w3-wide">GoWithME</a>
        <!-- Right-sided navbar links -->
        <div class="w3-right w3-hide-small">
            <a href="/gowithme/admin" class="w3-bar-item w3-button"><i class="fa fa-users-gear"></i> Users</a>
            <a href="/gowithme/home" class="w3-bar-item w3-button"><i class="fa fa-home"></i> Home</a>
            <a href="/gowithme/app" class="w3-bar-item w3-button"><i class="fa fa-mandalorian"></i> App</a>
            <a href="/gowithme/admin/contact" class="w3-bar-item w3-button"><i class="fa fa-compass"></i> Contact</a>
            <a href="/gowithme/admin/category" class="w3-bar-item w3-button"><i class="fa fa-grunt"></i> Category</a>
            <a href="/gowithme/app/logout" class="w3-bar-item w3-button"><i class="fa fa-pencil"></i> Logout</a>
        </div>
        <!-- Hide right-floated links on small screens and replace them with a menu icon -->

        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium"
           onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
</div>

<!-- Sidebar on small screens when clicking the menu icon -->
<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium " style="display:none"
     id="mySidebar">
    <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
    <a href="/gowithme/home" onclick="w3_close()" class="fa fa-user w3-button">Home</a>
    <a href="/gowithme/app/main" onclick="w3_close()" class="w3-bar-item w3-button">App</a>
    <a href="/gowithme/admin/create-contact" onclick="w3_close()" class="fa fa-user w3-button">Create contact</a>
    <a href="/gowithme/app/logout" onclick="w3_close()" class="w3-bar-item w3-button">Logout</a>

</nav>
