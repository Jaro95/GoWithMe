<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles.css">
    <script type="text/javascript" src ="${pageContext.request.contextPath}/js/action.js"></script>
</head>
<body>
<h1>User list</h1>
<div>
    Add new userDetails
    <button class="btn" onclick="location.href='/gowithme/home/registration'">Dodaj</button>
</div>
<div>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Age</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="userDetails">
            <tr>
                <td>${userDetails.id}</td>
                <td>${userDetails.firstName}</td>
                <td>${userDetails.lastName}</td>
                <td>${userDetails.age}</td>
                <td>${userDetails.description}</td>

                <td>
                    <button class="btn" onclick="location.href='/gowithme/home/update?id=${userDetails.id}'">Edytuj</button>
                    <a class="btn confirm-delete" href="${pageContext.request.contextPath}/gowithme/home/delete?id=${userDetails.id}">Usuń</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
