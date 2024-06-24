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

<div class="w3-container w3-light-grey">

    <div class="w3-row-padding">
        <div class="w3-col m6" style="padding:128px 200px">
            <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350"
                 height="200">
        </div>
        <div class="user-information">

                <h3>${firstName} ${lastName}</h3>
                <p>${city}</p>
                <p>Wiek: ${age}</p>
                <p>Kilka słów o mnie: </p>
                <p>${description}</p>
                <p><a href="/gowithme/app/profile/edit" class="w3-button w3-black">
                    <i class="fa fa-user-pen"></i> Edycja</a></p>
        </div>
        <div class="user-information">
            <p>Login:  ${login} </p>
            <p><a href="/gowithme/app/profile/edit/email" class="w3-button w3-black">
                <i class="fa fa-envelope"></i> Edytuj login</a></p>

            <p><a href="/gowithme/app/profile/edit/password" class="w3-button w3-black">
                <i class="fa fa-user-gear"></i> Edytuj hasło</a></p>
        </div>

    </div>
    <c:if test="${not empty messageUpdate}">
        <div class="w3-center alert alert-success">
                ${messageUpdate}
        </div>
    </c:if>
</div>

<div class="w3-display-container w3-light-grey contact" id="appMain">
    <div class="w3-center w3-xlarge ">Twoje aktywności</div>
    <c:if test="${not empty messageActivity}">
        <div class="alert alert-success">
                ${messageActivity}
        </div>
    </c:if>
    <table id="activitiesTable" class="display">
        <thead>
        <tr>
            <th>Id</th>
            <th>Aktywność</th>
            <th>Opis</th>
            <th>Miasto</th>
            <th>Dokładna lokalizacja</th>
            <th>Przypisane osoby</th>
            <th>Aktywne</th>
            <th>Akcje</th>
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
                <c:if test="${activity.enabled}">
                    <td>Tak</td>
                </c:if>
                <c:if test="${!activity.enabled}">
                    <td>Nie</td>
                </c:if>
                <td>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/assign?id=${activity.id}'">
                        <i class="fa-solid fa-person-group"></i>Przypisz
                    </button>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/edit?id=${activity.id}'">
                        <i class="fa fa-rotate"></i>Edycja
                    </button>
                    <a class="w3-button w3-black"
                            href='${pageContext.request.contextPath}/gowithme/app/activity/delete?id=${activity.id}&url=/gowithme/app/profile'
                       id="delete-activity">
                        <i class="fa fa-trash"></i>Usuń
                    </a>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>