<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<div class="w3-display-container w3-light-grey contact" id="login">

    <p class="w3-center w3-jumbo cantact-info">Logowanie</p>
    <div class="w3-center cantact-info">
        <form method="post" class="cantact-details">
            <p><input class="input-contact w3-border" type="email" placeholder="email" name="username"></p>
            <p><input class="input-contact w3-border" type="password" placeholder="password"  name="password"></p>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div><input class="w3-button w3-black" type="submit" value="Sign In"/></div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </p>
        </form>
    </div>
</div>
        <jsp:include page="footer.jsp"/>

<%--<form method="post">--%>
<%--    <div><label> Email : <input type="text" name="username"/> </label></div>--%>
<%--    <div><label> Password: <input type="password" name="password"/> </label></div>--%>
<%--    <div><input type="submit" value="Sign In"/></div>--%>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--</form>--%>
