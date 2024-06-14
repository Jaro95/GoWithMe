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
    <h3 class="w3-center"></h3>
    <p class="w3-center w3-jumbo cantact-info">Rejestracja</p>
    <div class="w3-center cantact-info">
        <form method="post" target="_blank" class="cantact-details">
            <p><input class="input-contact w3-border" type="text" placeholder="Imę" required name="firstName"></p>
            <p><input class="input-contact w3-border" type="text" placeholder="Nazwisko" required name="lastName"></p>
            <p><input class="input-contact w3-border" type="text" placeholder="Miejscowość" required name="city"></p>
            <p><input class="input-contact w3-border" type="email" placeholder="Email" required name="email"></p>
            <p><input class="input-contact w3-border" type="password" placeholder="Hasło" required name="password"></p>
            <p><input class="input-contact w3-border" type="password" placeholder="Powtórz hasło" required name="repeatPassword"></p>
                <button class="w3-button w3-black" type="submit">
                    <i class="fa fa-plus"></i> Utwórz konto
                </button>
            </p>
        </form>
    </div>
</div>
    <jsp:include page="footer.jsp"/>
