<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 13.06.2024
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<section>
    <form>
        <h1>Login Form</h1>
        <div>
            <input type="text" class="form-control" placeholder="Username" required="">
        </div>
        <div>
            <input type="password" class="form-control" placeholder="Password" required="">
        </div>
        <div>
            <a class="btn btn-default submit" href="index.html">Log in</a>
            <a class="reset_pass" href="#">Lost your password?</a>
        </div>

        <div class="clearfix"></div>

        <div class="separator">
            <p class="change_link">New to site?
                <a href="#signup" class="to_register"> Create Account </a>
            </p>

            <div class="clearfix"></div>
            <div>
                <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
            </div>
        </div>
    </form>

        <jsp:include page="footer.jsp"/>
