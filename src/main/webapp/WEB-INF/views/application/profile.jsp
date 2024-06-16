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
        <div class="w3-col m6" style="padding:128px 200px">

                <h3>${firstName} ${lastName}</h3>
                <p>${city}</p>
                <p>Wiek: ${age}</p>
                <p style="text ">Kilka słów o mnie: </p>
                <p>${description}</p>
                <p><a href="/gowithme/app/profile/edit" class="w3-button w3-black">
                    <i class="fa fa-user-pen"></i> Edycja</a></p>
        </div>

    </div>
</div>

<div class="w3-display-container w3-light-grey contact" id="appMain">
    <div class="w3-center w3-xlarge ">Twoje aktywności</div>
    <table id="activitiesTable" class="display">
        <thead>
        <tr>
            <th>Aktywność</th>
            <th>Opis</th>
            <th>Miasto</th>
            <th>Dokładna lokalizacja</th>
            <th>Chętne osoby</th>
            <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="activity" items="${activities}">
            <tr>
                <td>${activity.activity.activity}</td>
                <td>${activity.description}</td>
                <td>${activity.city}</td>
                <td>${activity.location}</td>
                <td>
                    <c:forEach var="user" items="${activity.userDetails}">
                        <p>${user.firstName} ${user.lastName}</p>
                    </c:forEach>
                </td>
                <td>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/add_users?id=${activity.id}'">
                        <i class="fa fa-right-to-bracket"></i>Przypisz
                    </button>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/edit?id=${activity.id}'">
                        <i class="fa fa-right-to-bracket"></i>Edycja
                    </button>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/app/activity/delete?id=${activity.id}'">
                        <i class="fa fa-right-to-bracket"></i>Usuń
                    </button>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>