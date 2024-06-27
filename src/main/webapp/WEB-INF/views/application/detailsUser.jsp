<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 15.06.2024
  Time: 01:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>

<div class="w3-container w3-light-grey">

    <div class="w3-row-padding">
        <div class="w3-col m6" style="padding:128px 200px">
            <img class="w3-image w3-round-large" src="/images/mainPeoplePedro.gif" alt="Buildings" width="350"
                 height="200">
        </div>
        <div class="user-information">
            <h3>${firstName} ${lastName}</h3>
            <p>${city}</p>
            <p>Wiek: ${age}</p>
            <p>Kilka słów o mnie: </p>
            <p>${description}</p>
        </div>
        <div class="user-information">
            <p><a href="javascript:void(0)" class="w3-button w3-black"
                  onclick="document.getElementById('user').style.display='block'">
                <i class="fa fa-paper-plane"></i> Wiadomość</a></p>
        </div>
    </div>

</div>

<div id="user" class="w3-modal" style="z-index:4">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-padding w3-red">
       <span onclick="document.getElementById('user').style.display='none'"
             class="w3-button w3-red w3-right w3-xxlarge"><i class="fa fa-remove"></i></span>
            <h2>Wiadomość</h2>
        </div>
        <div class="w3-panel">
            <form:form modelAttribute="SendMessageDTO" action="/gowithme/app/sendMessage">
                <form:input path="userReceiver" type="hidden"/>
                <form:textarea path="content" class="w3-input w3-border w3-margin-bottom" style="height:150px"
                               placeholder="What's on your mind?"></form:textarea>
                <input type="hidden" name="url" value="/gowithme/app/user/${id}">
                <div class="w3-section">
                    <a class="w3-button w3-red" onclick="document.getElementById('user').style.display='none'"> Zamknij<i
                            class="fa fa-remove"></i>
                    </a>
                    <button type="submit" class="w3-button w3-light-grey w3-right"> Wyślij<i class="fa fa-paper-plane"></i>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<c:if test="${not empty messageSend}">
    <div class="alert alert-success">
            ${messageSend}
    </div>
</c:if>

<jsp:include page="footer.jsp"/>