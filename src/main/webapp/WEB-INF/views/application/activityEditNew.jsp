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


<div class="w3-display-container w3-light-grey contact" id="appMain">
    <div class="w3-center w3-jumbo cantact-info">Edytuj aktywność</div>
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
    <form:form method="post" modelAttribute="activitiesPLan">
        <table id="assignTable" class="display">
            <thead>
            <tr>

                <th>Kategoria</th>
                <th>Opis</th>
                <th>Miasto</th>
                <th>Dokładna lokalizacja</th>
                <th>Akcja</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>
                    <form:select class="input-contact w3-border" path="category.id" items="${categories}"
                                 itemLabel="name" itemValue="id"/>
                    <p><form:errors path="category.name" cssClass="alert alert-error"/></p>
                </td>
                <td>
                    <form:textarea class="edit-about-user w3-border" type="text" placeholder="Opis"
                                   path="description"/>
                    <p><form:errors path="description" cssClass="alert alert-error"/></p>
                </td>
                <td>
                    <ul id="suggestions"></ul>
                    <form:input id="cityInput" class=" w3-border" type="text" placeholder="Miejscowość" path="city"/>
                    <p><form:errors path="city" cssClass="alert alert-error"/></p>
                </td>
                <td>
                    <form:textarea class="edit-about-user w3-border" type="text"
                                   placeholder="Dokładna lokalizacja"
                                   path="location"/>
                    <p><form:errors path="location" cssClass="alert alert-error"/></p>
                </td>
                <form:input path="usersJoined" type="hidden"/>
                <td>
                    <button class="w3-button w3-black" type="submit">
                        <i class="fa fa-person-save"></i> Zapisz
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
    <div class="w3-container w3-light-grey" style="padding:128px 16px" id="team">
        <h3 class="w3-center">Osoby przypisane do aktywności</h3>
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
                                <a class="w3-button w3-light-grey w3-block" id="delete-user-from-joined-list"
                                   href="/gowithme/app/activity/deleteUserFromJoinedList?activityId=${activityId}&userId=${user.id}"><i
                                        class="fa fa-minus"></i> Usuń
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