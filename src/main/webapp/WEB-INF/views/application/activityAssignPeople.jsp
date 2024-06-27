<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.06.2024
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<%--<div class="w3-container w3-light-grey">--%>

<%--    <div class="w3-row-padding">--%>
<%--        <div class="w3-col m6" style="padding:128px 200px">--%>
<%--            <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350"--%>
<%--                 height="200">--%>
<%--        </div>--%>
<%--        <div class="w3-col m6" style="padding:128px 200px">--%>

<%--                <h3>${firstName} ${lastName}</h3>--%>
<%--                <p>${city}</p>--%>
<%--                <p>Wiek: ${age}</p>--%>
<%--                <p>Kilka słów o mnie: </p>--%>
<%--                <p>${description}</p>--%>

<%--        </div>--%>
<%--    </div>--%>

<%--    <c:if test="${not empty messageUpdate}">--%>
<%--        <div class="w3-center alert alert-success">--%>
<%--                ${messageUpdate}--%>
<%--        </div>--%>
<%--    </c:if>--%>
<%--</div>--%>

<div class="w3-display-container w3-light-grey contact" id="appMain">
    <div class="w3-center w3-jumbo cantact-info">Przydziel do aktywności</div>
    <c:if test="${not empty messageError}">
        <div class="alert alert-error">
                ${messageError}
        </div>
    </c:if>
    <c:if test="${not empty messageSuccess}">
        <div class="alert alert-success">
                ${messageSuccess}
        </div>
    </c:if>
    <c:if test="${not empty messageDelete}">
        <div class="alert alert-success">
                ${messageDelete}
        </div>
    </c:if>
    <table id="assignTable" class="display">
        <thead>
        <tr>
            <th>Id</th>
            <th>Aktywność</th>
            <th>Opis</th>
            <th>Miasto</th>
            <th>Dokładna lokalizacja</th>
            <th>Przypisane osoby</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <div class="w3-container w3-light-grey" style="padding:128px 16px" id="team">
        <h3 class="w3-center">Osoby które chcą dołączyć do aktywności</h3>
        <div class="w3-row-padding w3-grayscale" style="margin-top:64px">
            <c:forEach items="${userList}" var="user">
                <div class="w3-col l3 m6 w3-margin-bottom">
                    <div class="w3-card">
                        <img src="/images/mainPeople.jpg" alt="${user.firstName}" style="width:100%">
                        <div class="w3-container">
                            <h3>${user.firstName} ${user.lastName}</h3>
                            <p class="w3-opacity">${user.city} wiek:${user.age}</p>
                            <p>${user.description}</p>
                            <p>
                                <a class="w3-button w3-light-grey w3-block"
                                   href="/gowithme/app/user/${user.id}" target="_blank"><i
                                        class="fa fa-eye"></i> Szczegóły
                                </a>
                                <a class="w3-button w3-light-grey w3-block"
                                   href="/gowithme/app/activity/assignUser?activityId=${activityId}&userId=${user.id}"><i
                                        class="fa fa-plus"></i> Dodaj
                                </a>
                                <a class="w3-button w3-light-grey w3-block" id="delete-user-from-list"
                                   href="/gowithme/app/activity/deleteUserFroWaitingList?activityId=${activityId}&userId=${user.id}"><i class="fa fa-minus"></i> Usuń
                                </a>
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>