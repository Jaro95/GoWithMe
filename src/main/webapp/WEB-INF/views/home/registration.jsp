<%@ taglib prefix="c" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.06.2024
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<jsp:include page="header.jsp" />--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css" />
</head>
<body>
<h1>Logowanie</h1>
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
<%--    <div class="form-group">--%>
<%--        Publishers:--%>
<%--        <div>--%>
<%--            <form:select path="city.id" items="${cities}" itemLabel="name" itemValue="id"/>--%>
<%--            <form:errors path="city.id" cssClass="error" />--%>
<%--        </div>--%>
<%--    </div>--%>
    <div class="form-group">
        Age:
        <form:input path="age"/>
        <form:errors path="age" cssClass="error" />
    </div>
    <div class="form-group">
        Description:
        <form:input path="description"/>
        <form:errors path="description" cssClass="error" />
    </div>
    <input type="submit"/>
</form:form>
</body>
</html>
