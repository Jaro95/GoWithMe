<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-dark-grey w3-button w3-hover-black w3-left-align"
       onclick="document.getElementById('id01').style.display='block'">New Message <i
            class="w3-padding fa fa-pencil"></i></a>
    <a id="myBtn" onclick="myFunc('Demo1')" href="javascript:void(0)" class="w3-bar-item w3-button"><i
            class="fa fa-inbox w3-margin-right"></i>Inbox (3)<i class="fa fa-caret-down w3-margin-left"></i></a>
    <div id="Demo1" class="w3-hide w3-animate-left">

        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-border-bottom test w3-hover-light-grey"
           onclick="openMail('Borge');w3_close();" id="33">
            <div class="w3-container">
                <img class="w3-round w3-margin-right" src="/images/mainPeople.jpg" style="width:15%;"><span
                    class="w3-opacity w3-large">Borge Refsnes</span>
                <h6>this place is for "City: category"</h6>
            </div>
        </a>



        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-border-bottom test w3-hover-light-grey"
           onclick="openMail('Jane');w3_close();">
            <div class="w3-container">
                <img class="w3-round w3-margin-right" src="/images/mainPeople.jpg" style="width:15%;"><span
                    class="w3-opacity w3-large">Jane Doe</span>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit...</p>
            </div>
        </a>
        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-border-bottom test w3-hover-light-grey"
           onclick="openMail('John');w3_close();">
            <div class="w3-container">
                <img class="w3-round w3-margin-right" src="/images/mainPeople.jpg" style="width:15%;"><span
                    class="w3-opacity w3-large">John Doe</span>
                <p>Welcome!</p>
            </div>
        </a>

    </div>
</nav>

<!-- Modal that pops up when you click on "New Message" -->
<div id="id01" class="w3-modal" style="z-index:4">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-padding w3-red">
       <span onclick="document.getElementById('id01').style.display='none'"
             class="w3-button w3-red w3-right w3-xxlarge"><i class="fa fa-remove"></i></span>
            <h2>Send Mail</h2>
        </div>
        <div class="w3-panel">
            <label>To</label>
            <input class="w3-input w3-border w3-margin-bottom" type="text">
            <label>From</label>
            <input class="w3-input w3-border w3-margin-bottom" type="text">
            <label>Subject</label>
            <input class="w3-input w3-border w3-margin-bottom" type="text">
            <input class="w3-input w3-border w3-margin-bottom" style="height:150px" placeholder="What's on your mind?">
            <div class="w3-section">
                <a class="w3-button w3-red" onclick="document.getElementById('id01').style.display='none'">Cancel  <i
                        class="fa fa-remove"></i></a>
                <a class="w3-button w3-light-grey w3-right"
                   onclick="document.getElementById('id01').style.display='none'">Send  <i
                        class="fa fa-paper-plane"></i></a>
            </div>
        </div>
    </div>
</div>

<!-- Overlay effect when opening the side navigation on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer"
     title="Close Sidemenu" id="myOverlay"></div>

<!-- Page content -->
<div class="w3-main">
    <i class="fa fa-bars w3-button w3-white w3-hide-large w3-xlarge w3-margin-left w3-margin-top"
       onclick="w3_open()"></i>
    <a href="javascript:void(0)" class="w3-hide-large w3-red w3-button w3-right w3-margin-top w3-margin-right"
       onclick="document.getElementById('id01').style.display='block'"><i class="fa fa-pencil"></i></a>

    <div id="Borge" class="w3-container person chat-wrapper">
        <br>

        <div class="chat-bubble user1">
            <p>Cześć, jak się masz?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>

        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="input-container">
            <textarea class="chat-input" placeholder="Napisz wiadomość..."></textarea>
            <button class="w3-button w3-black send-button" type="submit">
                <i class="fa fa-paper-plane"></i> Wyślij
            </button>
        </div>
    </div>

    <div id="Jane" class="w3-container person chat-wrapper">
        <br>

        <div class="chat-bubble user1">
            <p>Cześć, jak się masz?</p>
        </div>



        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>

        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user1">
            <p>Też dobrze, dzięki za zapytanie.</p>
        </div>
        <div class="chat-bubble user2">
            <p>W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A
                Ty
                ?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?
                W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?W porządku, dziękuję! A Ty?</p>
        </div>
        <div class="input-container">
            <textarea class="chat-input" placeholder="Napisz wiadomość..."></textarea>
            <button class="w3-button w3-black send-button" type="submit">
                <i class="fa fa-paper-plane"></i> Wyślij
            </button>
        </div>
    </div>

    <div id="John" class="w3-container person">
        <br>

        <h5 class="w3-opacity">Subject: None</h5>
        <h4><i class="fa fa-clock-o"></i> From John Doe, Sep 23, 2015.</h4>
        <a class="w3-button w3-light-grey">Reply<i class="w3-margin-left fa fa-mail-reply"></i></a>
        <a class="w3-button w3-light-grey">Forward<i class="w3-margin-left fa fa-arrow-right"></i></a>
        <hr>
        <p>Welcome.</p>
        <p>That's it!</p>
    </div>

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

    openMail("Borge")

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
    var openTab = document.getElementById("firstTab");
    openTab.click();
</script>

<jsp:include page="footer.jsp"/>