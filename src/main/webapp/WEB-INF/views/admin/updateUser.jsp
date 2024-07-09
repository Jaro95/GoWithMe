<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<div class="w3-display-container w3-light-grey contact" id="admin">
    <p class="w3-center w3-jumbo cantact-info">Edit user</p>
    <form:form modelAttribute="userDetails">
        <table id="adminTable" class="display">
            <thead>
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Enabled</th>
                <th>Role</th>
                <th>Full name</th>
                <th>City</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td>${userId}</td>
                <td>${userEmail}</td>
                <form:input path="user.id" type="hidden"/>
                <form:input path="user.email" type="hidden"/>
                <form:input path="user.password" type="hidden"/>
                <form:input path="user.token" type="hidden"/>
                <form:input path="description" type="hidden"/>
                <form:input path="age" type="hidden"/>
                <form:input path="id" type="hidden"/>


                <td>
                    <form:select class="w3-border" path="user.enabled">
                        <form:option value="true">Tak</form:option>
                        <form:option value="false">Nie</form:option>
                    </form:select>
                </td>
                <sec:authorize access="hasRole('SUPER_ADMIN')">
                    <td>
                        <c:forEach items="${roles}" var = "role" >
                           <p>${role.name}: <form:checkbox path="user.roles"
                                             value="${role}"/></p>
                        </c:forEach>

                    </td>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <td>
                        <c:forEach items="${userRole}" var="role">
                        <p>${role.name}</p>
                        </c:forEach>
                        <form:input path="user.roles" type="hidden"/>
                    </td>
                </sec:authorize>
                <td><form:input path="firstName"/> <form:input path="lastName"/>
                <p><form:errors path="firstName" cssClass="alert alert-error"/>
                    <form:errors path="lastName" cssClass="alert alert-error"/></p>
                </td>
                <td><form:input path="city"/>
                <form:errors path="city" cssClass="alert alert-error"/>
                </td>
                <td>
                    <button class="w3-button w3-black" type="submit">
                        <i class="fa fa-save"></i> Zapisz
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </form:form>
</div>


<jsp:include page="footer.jsp"/>

