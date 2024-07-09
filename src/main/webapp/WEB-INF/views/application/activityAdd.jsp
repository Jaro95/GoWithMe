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
<div class="w3-display-container w3-light-grey contact" id="contact">
    <p class="w3-center w3-jumbo cantact-info">Dodaj aktywność</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <div class="w3-center cantact-info">
        <form:form method="post" class="cantact-details" modelAttribute="activitiesPlan">
            <form:input path="id" type="hidden"/>
            <p>
                    <form:select class="input-contact w3-border" path="category.id" items="${categories}"
                                 itemLabel="name" itemValue="id"/>
            <p><form:errors path="category.name" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Opis" path="description"/>

            <p><form:errors path="description" cssClass="alert alert-error"/></p>
            </p>
            <p>
            <ul id="suggestions"></ul>
            <form:input id="cityInput" class="input-contact w3-border" type="text" placeholder="Miejscowość"
                        path="city"/>

            <p><form:errors path="city" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Dokładna lokalizacja"
                                path="location"/>
            <p><form:errors path="location" cssClass="alert alert-error"/></p>
            </p>
            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-person-walking"></i> Dodaj
                </button>
            </p>
        </form:form>
    </div>


    <div class="w3-container w3-light-grey" style="padding:28px 16px" id="team">
        <h3 class="w3-center">Aktywności w Twojej miejscowości</h3>
        <div class="w3-row-padding w3-grayscale" style="margin-top:64px">
            <c:forEach items="${activitiesPlanList}" var="activity">
                <div class="w3-col l3 m6 w3-margin-bottom">
                    <div class="w3-card">
                        <img src="/images/mainPeople.jpg" alt="${activity.user.firstName}" style="width:100%">
                        <div class="w3-container">
                            <h3>${activity.user.firstName} ${activity.user.lastName}</h3>
                            <p class="w3-opacity">Wiek:${activity.user.age}</p>
                            <p>${activity.city}: ${activity.category.name}</p>
                            <p>${activity.description}</p>
                            <p>
                                <a class="w3-button w3-light-grey w3-block"
                                   href="${pageContext.request.contextPath}/gowithme/app/activity/details?id=${activity.user.id}&activityId=${activity.id}"><i
                                        class="fa fa-eye"></i> Szczegóły
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