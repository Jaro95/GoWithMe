<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <div class="w3-center cantact-info">
        <form:form method="post" class="cantact-details"  modelAttribute="registrationWrapper">
            <p><form:input class="input-contact w3-border" type="text" placeholder="Imę" path="userDetails.firstName"/></p>
            <p><form:input class="input-contact w3-border" type="text" placeholder="Nazwisko" path="userDetails.lastName"/></p>
            <p><form:input class="input-contact w3-border" type="text" placeholder="Miejscowość" path="city.name"/></p>
            <p><form:input class="input-contact w3-border" type="email" placeholder="Email" path="user.email"/></p>
            <p><form:input class="input-contact w3-border" type="password" placeholder="Hasło" path="user.password"/></p>
            <p><form:input class="input-contact w3-border" type="password" placeholder="Powtórz hasło" path="repeatPassword"/></p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-plus"></i> Utwórz konto
                </button>
            </p>
        </form:form>
    </div>
</div>
    <jsp:include page="footer.jsp"/>
