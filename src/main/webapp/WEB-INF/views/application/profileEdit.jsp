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

<div class="w3-light-grey">
    <p class="w3-center w3-jumbo cantact-info">Edycja danych</p>
    <div class="w3-center contact-info">
        <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350"
             height="200">
    </div>
    <div class="w3-center contact-info">
        <form:form modelAttribute="userDetails">
            <h3>
                <form:input path="id" type="hidden"/>
                <form:input path="user.id" type="hidden"/>

                <p>
                    <form:input class="input-contact w3-border w3-center" type="text" placeholder="Imię"
                                path="firstName"/>
                    <form:errors path="firstName" cssClass="alert alert-error"/>
                    <form:input class="input-contact w3-border w3-center" type="text" placeholder="Nazwisko"
                                path="lastName"/>
                    <form:errors path="lastName" cssClass="alert alert-error"/>
                </p>
<%--                <p>--%>
<%--                    --%>
<%--                </p>--%>
            </h3>
            <ul id="suggestions"></ul>
            <p><form:input id="cityInput" class="input-contact w3-border w3-center" type="text" placeholder="Miasto"
                           path="city"/>
            </p>
            <p><form:errors path="city" cssClass="alert alert-error"/></p>
            <p>
                <form:input class="input-contact w3-border w3-center" type="number" placeholder="Wiek"
                                  path="age"/>
            </p>
            <p><form:errors path="age" cssClass="alert alert-error"/></p>
            <p>Kilka słów o mnie: </p>
            <p><form:textarea class="w3-border edit-about-user" type="text" placeholder="Coś o sobie"
                              path="description"/></p>
            </p>
            <p><form:errors path="description" cssClass="alert alert-error"/></p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-check"></i> Aktualizuj
                </button>
            </p>
        </form:form>
    </div>


<jsp:include page="footer.jsp"/>