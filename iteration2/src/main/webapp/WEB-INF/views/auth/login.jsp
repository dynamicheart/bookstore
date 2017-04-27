<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String ctx = request.getContextPath(); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>
<body style="background-image:url('<%=ctx%>/resources/images/christmas-time-decorations-still-life-picjumbo-com.jpg')" onload='document.loginForm.username.focus();'>
<div class="container" style="padding-top:150px">
    <div class="row">
        <div class="panel panel-default col-sm-4 col-sm-offset-4 align-middle">
            <div class="panel-body">
                <h2>Please login</h2>

                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">
                        <p>Invalid username and password.</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        <p>You have been logged out successfully.</p>
                    </div>
                </c:if>

                <form name='loginForm' action="${loginUrl}" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label>User Name:</label>
                        <input type="text" class="form-control" name='username' placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" class="form-control" name='password' placeholder="Enter password">
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox"> Remember me</label>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                    <button class="btn btn-lg btn-primary btn-block" name="submit" type="submit">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
