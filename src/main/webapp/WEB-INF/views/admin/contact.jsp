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
    <p class="w3-center w3-jumbo cantact-info">Contact:</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>

    <p class="w3-center w3-jumbo cantact-info"><button class="w3-button w3-black"
            onclick="location.href='/gowithme/admin/contactAdd'">
        <i class="fa fa-pencil"></i> Add new Contact
    </button>
    </p>
    <table id="adminTable" class="display">
        <thead>
        <tr>
            <th>Id</th>
            <th>Address</th>
            <th>Email</th>
            <th>Phone number</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${contactList}" var="contact" varStatus="status">
            <tr>

                <c:if test="${not empty updateId}">
                <c:if test="${contact.id eq updateId}">
                <td>${status.count}</td>
                <form:form method="post" >
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input name="contactId" type="hidden" value="${contact.id}">
                    <td><input name="address" type="text" value="${contact.address}"></td>
                    <td><input name="email" type="email" value="${contact.email}"></td>
                    <td><input name="phoneNumber" type="number" pattern="[0-9]*" value="${contact.phoneNumber}"></td>
                    <td>
                        <button class="w3-button w3-black" type="submit">
                            <i class="fa fa-save"></i> Zapisz
                        </button>
                    </td>
                </form:form>
                    </c:if>
                    </c:if>
                    <c:if test="${empty updateId}">
                    <td>${status.count}</td>
                    <td>${contact.address}</td>
                    <td>${contact.email}</td>
                    <td>${contact.phoneNumber}</td>
                    <td>

                        <button class="w3-button w3-black"
                                onclick="location.href='/gowithme/admin/contact?updateId=${contact.id}'">
                            <i class="fa fa-pencil"></i> Edytuj
                        </button>
                        <sec:authorize access="hasRole('SUPER_ADMIN')">
                            <a class="w3-button w3-black" id ="delete-contact"
                               href="${pageContext.request.contextPath}/gowithme/admin/contactDelete?deleteId=${contact.id}">
                                <i class="fa fa-trash"></i>Usuń</a>
                        </sec:authorize>
                    </td>
                    </c:if>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<jsp:include page="footer.jsp"/>

