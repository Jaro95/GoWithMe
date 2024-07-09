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
    <p class="w3-center w3-jumbo cantact-info">Co w trawie piszczy</p>
    <table id="activitiesTable" class="display">
        <thead>
        <tr>
            <th>Id</th>
            <th>Kategoria</th>
            <th>Opis</th>
            <th>Miasto</th>
            <th>Dokładna lokalizacja</th>
            <th>Przypisane osoby</th>
            <th>Dodał</th>
            <th>Brzmi spoko</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="activity" items="${activities}" varStatus="status">
            <tr>

                <td>${status.count}</td>
                <td>${activity.category.name}</td>
                <td>${activity.description}</td>
                <td>${activity.city}</td>
                <td>${activity.location}</td>
                <td>
                       <c:forEach var="user" items="${activity.usersJoined}">
                           <p>${user.firstName} ${user.lastName}</p>
                       </c:forEach>
                </td>
                <td>${activity.user.firstName} ${activity.user.lastName}</td>
                <c:if test="${activity.enabled}">
                <td>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/details?id=${activity.user.id}&activityId=${activity.id}'">
                        <i class="fa fa-right-to-bracket"></i>Szczegóły
                    </button>
                </td>
                </c:if>
                <c:if test="${!activity.enabled}">
                    <td>
                        Nieaktywne
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>