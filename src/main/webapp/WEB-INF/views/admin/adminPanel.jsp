<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<div class="w3-display-container w3-light-grey contact" id="contact">
    <p class="w3-center w3-jumbo cantact-info">All users:</p>
    <div class="w3-center cantact-info">

        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Enabled</th>
                <th>Role</th>
                <th>Full name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td>
                    <c:forEach items="${user.roles}" var="role">
                        <p>${role.name}</p>
                    </c:forEach>
                    </td>
                    <td>${user.userDetails.firstName} ${user.userDetails.lastName}</td>
                    <td>${user.userDetails.city.name}</td>
                    <td>
                        <button class="w3-button w3-black" onclick="location.href='/gowithme/admin/update?id=${user.id}'">
                            <i class="fa fa-pencil"></i> Edytuj</button>
                        <a class="w3-button w3-black confirm-delete-user" href="${pageContext.request.contextPath}/gowithme/admin/delete?id=${user.id}">
                            <i class="fa fa-trash"></i>Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

    <jsp:include page="footer.jsp"/>

<h1>Activity Table</h1>
<input type="text" id="searchBox" placeholder="Search...">
<table id="activityTable" class="display">
    <thead>
    <tr>
        <th>Activity</th>
        <th>Description</th>
        <th>City</th>
        <th>Location</th>
        <th>User Details</th>
        <th>User</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="activity" items="${activities}">
        <tr>
            <td>${activity.activity}</td>
            <td>${activity.description}</td>
            <td>${activity.city}</td>
            <td>${activity.location}</td>
            <td>${activity.userDetails}</td>
            <td>${activity.user}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

