<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/styles2.css">
</head>
<body>
<div >
    <h1>Add new author</h1>
</div>
<div>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:hidden path="id" />
        </div>

        <div class="form-group">
            First name:
            <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="error" />
        </div>

        <div class="form-group">
            Last name:
            <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="error" />
        </div>

        <input type="submit"/>
    </form:form>
</div>
<%--<c:if test="${not empty errors}">--%>
<%--    <div class="error form-group">--%>
<%--        <h3>Validation Errors:</h3>--%>
<%--        <ul>--%>
<%--            <c:forEach var="error" items="${errors}">--%>
<%--                <li>${error.field} : ${error.defaultMessage}</li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</c:if>--%>
</body>
</html>
