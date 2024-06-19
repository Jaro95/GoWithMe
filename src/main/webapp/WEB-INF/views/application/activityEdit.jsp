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
    <p class="w3-center w3-jumbo cantact-info">Edytuj aktywność</p>
    <div class="w3-center cantact-info">
        <form:form method="post" class="cantact-details" modelAttribute="activitiesPLan">
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
                    <form:input id="cityInput" class="input-contact w3-border" type="text" placeholder="Miejscowość" path="city"/>

            <p><form:errors path="city" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Dokładna lokalizacja"
                                path="location"/>
            <p><form:errors path="location" cssClass="alert alert-error"/></p>
            </p>
            <%--            <p>--%>
            <%--                    <form:select class="input-contact w3-border" path="friendList" items="${friend}" multiple="true"/>--%>
            <%--            <p><form:errors path="friendList" cssClass="alert alert-error"/></p>--%>
            <%--            </p>--%>
            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-person-walking"></i> Edytuj
                </button>
            </p>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>