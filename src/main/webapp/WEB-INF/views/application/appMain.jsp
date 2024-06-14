<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.06.2024
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="w3-display-container w3-light-grey contact" id="appMain">

    <p class="w3-center w3-jumbo cantact-info">Activity Table</p>
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
</div>
<jsp:include page="footer.jsp"/>