<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<div class="w3-display-container w3-light-grey contact" id="contact">
    <p class="w3-center w3-jumbo cantact-info">Napisz do nas:</p>
    <div class="w3-center cantact-info">
        <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i>${address}</p>
        <p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i>Phone: +48 ${phone}</p>
        <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> Email: ${email}</p>
        <br>
        <form:form method="post" target="_blank" class="cantact-details" modelAttribute="messageContact">
            <p><form:input class="input-contact w3-border" type="text" placeholder="Name" path="name"/></p>
            <p><form:input class="input-contact w3-border" type="email" placeholder="Email" path="email"/></p>
            <p><form:input class="input-contact w3-border" type="text" placeholder="Subject" path="subject"/></p>
            <p><form:textarea rows="4" class="input-contact w3-border" placeholder="Message" path="message"></form:textarea></p>
            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-paper-plane"></i> Wyślij
                </button>
            </p>
        </form:form>
    </div>
</div>
    <jsp:include page="footer.jsp"/>

