<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<section class="w3-display-container w3-light-grey contact"  id="contact">
    <h3 class="w3-center"></h3>
    <p class="w3-center w3-jumbo cantact-info">Logowanie</p>
    <div class="w3-center cantact-info">

        <form action="/send_contact" target="_blank" class="cantact-details">
            <p><input class="input-contact w3-border" type="text" placeholder="Email" required name="name"></p>
            <p><input class="input-contact w3-border" type="email" placeholder="Password" required name="email"></p>
            <p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-paper-plane"></i> Wyślij
                </button>
            </p>
        </form>
    </div>
        <jsp:include page="footer.jsp"/>
