<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<div class="w3-display-container w3-light-grey main-height" id="contact">
    <p class="w3-center w3-jumbo contact-info">Napisz do nas:</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <div class="w3-center ">
        <p><i class="fa fa-map-marker fa-fw w3-xlarge w3-margin-right"></i>Addres: ${address}</p>
        <p><i class="fa fa-phone fa-fw w3-xlarge w3-margin-right"></i>Telefon: +48 ${phone}</p>
        <p><i class="fa fa-envelope fa-fw w3-xlarge w3-margin-right"></i>Email: ${email}</p>
        <form:form method="post" class="cantact-details" modelAttribute="contactForm">
            <p>
                <form:input class="input-contact w3-border" type="text" placeholder="Imię" path="name"/>
                <p><form:errors path="name" cssClass="alert alert-error"/></p>
            </p>
            <p>
                <form:input class="input-contact w3-border" type="email" placeholder="Email" path="email"/>
                <p><form:errors path="email" cssClass="alert alert-error"/></p>
            </p>
            <p>
                <form:input class="input-contact w3-border" type="text" placeholder="Temat" path="subject"/>
                <p><form:errors path="subject" cssClass="alert alert-error"/></p>
            </p>
            <p>
                <form:textarea rows="4" class="input-contact w3-border" placeholder="Wiadomość" path="message"></form:textarea>
                <p><form:errors path="message" cssClass="alert alert-error"/></p>
            </p>

            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-paper-plane"></i> Wyślij
                </button>
            </p>
        </form:form>
    </div>
</div>
    <jsp:include page="footer.jsp"/>

