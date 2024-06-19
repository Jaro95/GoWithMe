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
    <p class="w3-center w3-jumbo cantact-info">Przypisz do aktywności</p>
    <c:forEach items="${userList}" var="user">
        <div class="w3-row-padding" id="user${user.id}">
            <div class="w3-col m6" style="padding:128px 200px">
                <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350"
                     height="200">
            </div>
            <div class="w3-col m6" style="padding:128px 200px">

                <h3>${user.firstName} ${user.lastName}</h3>
                <p>${user.city}</p>
                <p>Wiek: ${user.age}</p>
                <p>Kilka słów o mnie: </p>
                <p>${user.description}</p>


            </div>
        </div>
    </c:forEach>
</div>

<div class="w3-display-container w3-light-grey contact" id="appMain">

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
            <th>Chętne osoby</th>
            <th>Akcje</th>
        </tr>
        </thead>
        <tbody>
        <form method="post">

        </form>
<%--        <c:forEach var="activity" items="${activities}" varStatus="status">--%>
<%--            <tr>--%>
<%--                <td>${status.count}</td>--%>
<%--                <td>${activity.category.name}</td>--%>
<%--                <td>${activity.description}</td>--%>
<%--                <td>${activity.city}</td>--%>
<%--                <td>${activity.location}</td>--%>
<%--                <td>--%>
<%--                    <c:forEach var="user" items="${activity.userDetails}">--%>
<%--                        <p>${user.firstName} ${user.lastName}</p>--%>
<%--                    </c:forEach>--%>
<%--                </td>--%>
<%--                --%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
        </tbody>
    </table>
    <form method="post">

        <button class="w3-button w3-black" type="submit">
            <i class="fa fa-check"></i> Zapisz
        </button>
    </form>

    <%--    <form:form method="post">--%>
    <%--        <div class="w3-center w3-xlarge ">Aktywność:</div>--%>
    <%--        <c:forEach items="${userList}" var="user">--%>
    <%--            <form:checkbox path="user" value="${user.id}"/>--%>
    <%--            <form:label path="user">${user.firstName} ${user.lastName}</form:label><br/>--%>
    <%--            &lt;%&ndash;            <input type="checkbox" id="${user.id}" name="fruit" value="${user.firstName} ${user.lastName}">&ndash;%&gt;--%>
    <%--            &lt;%&ndash;            <label for="${user.id}">${user.firstName} ${user.lastName}</label><br>&ndash;%&gt;--%>
    <%--        </c:forEach>--%>
    <%--        <button class="w3-button w3-plus" type="submit">--%>
    <%--            <i class="fa fa-check"></i> Dodaj--%>
    <%--        </button>--%>
    <%--    </form:form>--%>
    <%--    <form:form method="post">--%>
    <%--        <form:checkboxes items="${userDetails}" path="userdetails" itemLabel="firtName" itemValue="id"/>--%>

    <%--    </form:form>--%>

</div>
<jsp:include page="footer.jsp"/>