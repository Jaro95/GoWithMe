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

    <div class="form-bg">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <div class="form-container">
                        <h3 class="title">Register</h3>
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label>User Name</label>
                                <input type="text" class="form-control" placeholder="User Name">
                            </div>
                            <div class="form-group">
                                <label>Email ID</label>
                                <input type="email" class="form-control" placeholder="Email Address">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label>Confirm Password</label>
                                <input type="password" class="form-control" placeholder="Confirm Password">
                            </div>
                            <h4 class="sub-title">Personal Information</h4>
                            <div class="form-group">
                                <label>Phone No.</label>
                                <input type="text" class="form-control" placeholder="Phone Number">
                            </div>
                            <div class="form-group">
                                <label>City</label>
                                <select class="form-control">
                                    <option value="paris">Paris</option>
                                    <option value="new york">New York</option>
                                </select>
                            </div>
                            <div class="check-terms">
                                <input type="checkbox" class="checkbox">
                                <span class="check-label">I agree to the terms</span>
                            </div>
                            <span class="signin-link">Already have an account? Click here to <a href="">Login</a></span>
                            <button class="btn signup">Create Account</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
