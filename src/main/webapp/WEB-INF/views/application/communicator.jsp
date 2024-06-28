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

<nav class="w3-sidebar w3-bar-block w3-collapse w3-white w3-animate-left w3-card nav-chat"
     style="z-index:3;width:300px;"
     id="mySidebar">

    <a href="javascript:void(0)" onclick="w3_close()" title="Close Sidemenu"
       class="w3-bar-item w3-button w3-hide-large w3-large">Close <i class="fa fa-remove"></i></a>
    <%--    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-dark-grey w3-button w3-hover-black w3-left-align"--%>
    <%--       onclick="document.getElementById('id01').style.display='block'">New Message <i--%>
    <%--            class="w3-padding fa fa-pencil"></i></a>--%>
    <a id="myBtn" onclick="myFunc('Demo1')" href="javascript:void(0)" class="w3-bar-item w3-button"><i
            class="fa fa-inbox w3-margin-right"></i>Inbox<i class="fa fa-caret-down w3-margin-left"></i></a>
    <div id="Demo1" class="w3-hide w3-animate-left user-list user-buttons">
        <c:if test="${not empty emptyChat}">

            <div class="w3-container">
                <span>${emptyChat}</span>
            </div>
        </c:if>

        <c:if test="${empty emptyChat}">
            <div class="user-buttons">
                <c:if test="${currentPage > 1}">
                    <a href="/gowithme/app/chat?page=${currentPage - 1}&size=${pageSize}"
                       class="prev-user">
                        <div class="dropdown-item ">^</div>
                    </a>
                </c:if>
            </div>
            <c:forEach items="${userList}" var="user">
                <a href="/gowithme/app/chat?userReceiverId=${user.id}"
                   class="w3-bar-item w3-button w3-border-bottom test w3-hover-light-grey"
                   onclick="openMail('${user.firstName}');w3_close();">
                    <div class="w3-container">
                        <img class="w3-round w3-margin-right" src="/images/mainPeople.jpg" style="width:15%;"><span
                            class="w3-opacity w3-large">${user.firstName} ${user.lastName}</span>
                        <h6>${user.city}: ${user.age}"</h6>
                    </div>
                </a>
            </c:forEach>
            <div class="user-buttons">
                <c:if test="${currentPage < totalPages}">
                    <a href="${pageContext.request.contextPath}/gowithme/app/chat?page=${currentPage + 1}&size=${pageSize}"
                       class="next-user">
                        <div class="dropdown-item ">v</div>
                    </a>
                </c:if>
            </div>

        </c:if>
    </div>
</nav>

<!-- Overlay effect when opening the side navigation on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="Close Sidemenu" id="myOverlay"></div>

<!-- Page content -->
<div class="w3-main chat-wrapper chat-box" id="chat-box">
    <i class="fa fa-bars w3-button w3-white w3-hide-large w3-xlarge w3-margin-left w3-margin-top"
       onclick="w3_open()"></i>

    <c:if test="${not empty userSenderMessage}">
        <div id="${userSenderMessage}" class="w3-container person chat-container">
            <c:forEach items="${userConversation}" var="message">
                <div class="chat-bubble ${message.senderMessage == currentUserMessage ? 'user2' : 'user1'}">
                    <p>${message.content}</p>
                </div>
            </c:forEach>
            <form method="post">
            <div class="input-container">
                <textarea id="chat-input" class="chat-input" placeholder="Napisz wiadomość..."></textarea>
                <button class="w3-button w3-black send-button" onclick="sendMessage()">Wyślij</button>
            </div>
            </form>
        </div>
    </c:if>

</div>

<script>
    var openInbox = document.getElementById("myBtn");
    openInbox.click();

    function w3_open() {
        document.getElementById("mySidebar").style.display = "block";
        document.getElementById("myOverlay").style.display = "block";
    }

    function w3_close() {
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("myOverlay").style.display = "none";
    }

    function myFunc(id) {
        var x = document.getElementById(id);
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
            x.previousElementSibling.className += " w3-red";
        } else {
            x.className = x.className.replace(" w3-show", "");
            x.previousElementSibling.className =
                x.previousElementSibling.className.replace(" w3-red", "");
        }
    }


    function openMail(personName) {
        var i;
        var x = document.getElementsByClassName("person");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        x = document.getElementsByClassName("test");
        for (i = 0; i < x.length; i++) {
            x[i].className = x[i].className.replace(" w3-light-grey", "");
        }
        document.getElementById(personName).style.display = "block";
        event.currentTarget.className += " w3-light-grey";
    }
</script>

<script>
    //var openTab = document.getElementById("firstTab");
    //openTab.click();
    let socket;
    const currentUser = "${currentUserMessage}";
    window.onload = function () {
        socket = new WebSocket("ws://" + window.location.host + "/gowithme/app/chat");
        socket.onmessage = function (event) {
            const chatContainer = document.querySelector(".chat-container");
            const message = JSON.parse(event.data);

            const messageBubble = document.createElement("div");
            messageBubble.className = "chat-bubble " + (message.senderMessage === currentUser ? "user2" : "user1");
            messageBubble.innerHTML = `<p>${message.content}</p>`;
            chatContainer.appendChild(messageBubble);

            const chatBox = document.querySelector(".chat-box");
            chatBox.scrollTop = chatBox.scrollHeight;
        };
    };

    function sendMessage() {
        const input = document.getElementById("chat-input");
        const message = {
            senderMessage: currentUser,
            content: input.value,
            timestamp: new Date().toISOString()
        };
        socket.send(JSON.stringify(message));
        input.value = "";
    }
</script>

<jsp:include page="footer.jsp"/>