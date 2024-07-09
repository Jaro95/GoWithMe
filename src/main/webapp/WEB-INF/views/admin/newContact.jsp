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
<div class="w3-display-container w3-light-grey main-height" id="contact">
    <p class="w3-center w3-jumbo cantact-info">Dodaj nowy kontakt</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <div class="w3-center cantact-info">
        <form:form method="post" class="cantact-details" modelAttribute="contact">
            <p>
                    <form:input class="input-contact w3-border" type="text" placeholder="Address" path="address"/>
            <p><form:errors path="address" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border" type="email" placeholder="Email" path="email"/>
            <p><form:errors path="email" cssClass="alert alert-error"/></p>
            </p>
            <p>
                    <form:input class="input-contact w3-border"  type="number" pattern="[0-9]*" placeholder="Numer telefonu"
                                path="phoneNumber"/>

            <p><form:errors path="phoneNumber" cssClass="alert alert-error"/></p>
            </p>
            <button class="w3-button w3-black" type="submit">
                <i class="fa fa-plus"></i> Dodaj
            </button>
            </p>
        </form:form>
    </div>
</div>
<jsp:include page="footer.jsp"/>
