<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="w3-display-container w3-light-grey contact" id="contact">
    <p class="w3-center w3-jumbo cantact-info">Rejestracja</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <div class="w3-center cantact-info">
        <form:form method="post" class="cantact-details" modelAttribute="registrationDTO">
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Imę" path="firstName"/>
            <p><form:errors path="firstName" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Nazwisko" path="lastName"/>
            <p><form:errors path="lastName" cssClass="alert alert-error"/></p>
            </p>
            <p>
            <ul id="suggestions"></ul>
                    <form:input id="cityInput" class="input-contact w3-border" type="text" placeholder="Miejscowość"
                                path="city"/>

            <p><form:errors path="city" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="email" placeholder="Email" path="email"/>
            <p><form:errors path="email" cssClass="alert alert-error"/></p>
            <c:if test="${not empty usedEmail}">
                <p class="alert alert-error">${usedEmail}</p>
            </c:if>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="password" placeholder="Hasło" path="password"/>
            <p><form:errors path="password" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="password" placeholder="Powtórz hasło"
                                path="repeatPassword"/>
            <p><form:errors path="repeatPassword" cssClass="alert alert-error"/></p>
            <c:if test="${not empty wrongPassword}">
                <p class="alert alert-error">${wrongPassword}</p>
            </c:if>
            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-plus"></i> Utwórz konto
                </button>
            </p>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
