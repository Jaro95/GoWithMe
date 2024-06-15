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

<div class="w3-container w3-light-grey" >
    <div class="w3-row-padding">
        <div class="w3-col m6" style="padding:128px 200px">
            <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350" height="200">
        </div>
        <div class="w3-col m6" style="padding:128px 200px">
            <h3>Full name</h3>
            <p>Miejscowość</p>
            <p>Wiek: </p>
            <p>Kilka słów o mnie: </p>
            <p> </p>
            <p><a href="/gowithme/app/profile/edit" class="w3-button w3-black"><i class="fa fa-square-pen"></i> Edycja</a></p>
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
            <th>Brzmi spoko</th>
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
                            onclick="location.href='/gowithme/app/activity?id=${activity.id}'">
                        <i class="fa fa-right-to-bracket"></i>Szczegóły
                    </button>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>