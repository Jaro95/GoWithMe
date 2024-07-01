<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <p class="w3-center w3-jumbo cantact-info">All users:</p>
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
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
        <c:forEach items="${userDetails}" var="userDetail">
            <tr>
                <td>${userDetail.user.id}</td>
                <td>${userDetail.user.email}</td>
                <td>${userDetail.user.enabled}</td>
                <td>
                    <c:forEach items="${userDetail.user.roles}" var="role">
                        <p>${role.name}</p>
                    </c:forEach>
                </td>
                <td>${userDetail.firstName} ${userDetail.lastName}</td>
                <td>${userDetail.city}</td>
                <td>
                    <button class="w3-button w3-black"
                            onclick="location.href='/gowithme/admin/user/update?id=${userDetail.user.id}'">
                        <i class="fa fa-pencil"></i> Edytuj
                    </button>
                    <sec:authorize access="hasRole('SUPER_ADMIN')">
                        <a class="w3-button w3-black" id="confirm-delete-user"
                           href="${pageContext.request.contextPath}/gowithme/admin/user/delete?id=${userDetail.user.id}">
                            <i class="fa fa-trash"></i>Usuń</a>
                    </sec:authorize>
                </td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<jsp:include page="footer.jsp"/>

