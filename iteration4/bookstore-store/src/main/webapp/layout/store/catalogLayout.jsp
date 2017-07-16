<%
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="java.util.Calendar" %>
<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Online Bookstore</title>

    <link rel="icon" href="<c:url value="/static/favicon.ico"/>">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="<c:url value="/resources/css/bookstore-store.css" />"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>

<c:set var="home_url" value="${pageContext.request.contextPath}/"/>
<c:set value="/store/customer/logout" var="logoutUrl" scope="request"/>

<body>
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${home_url}">Online Bookstore</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasRole('AUTH_CUSTOMER')">
                <!-- logged in user -->
                <c:if test="${requestScope.CUSTOMER!=null}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span class="normal-label">
                            <s:message code="label.generic.welcome" text="Welcome"/>
                            <c:if test="${not empty requestScope.CUSTOMER.nick}">
                                <c:out value="${sessionScope.CUSTOMER.nick}"/>
                            </c:if>
                        </span>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a onClick="javascript:location.href='<c:url
                                    value="/store/customer/profile"/>';" href="#"><i
                                    class="fa fa-user" aria-hidden="true"></i>&nbsp;<s:message code="label.my.profile"
                                                                                               text="My Profile"/></a>
                        </li>
                        <li>
                            <a onClick="javascript:location.href='<c:url
                                    value="/store/customer/order/orders"/>';" href="#"><i class="fa fa-table" aria-hidden="true"></i>&nbsp;<s:message code="label.order.title"
                                                                                               text="My Orders"/></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a onClick="javascript:location.href='<c:url value="/store/customer/logout"/>';"
                               href="#"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;<s:message code="button.label.logout"
                                                                                  text="Logout"/></a>
                        </li>
                    </ul>
                </li>
                </c:if>
                </sec:authorize>
                <sec:authorize access="!hasRole('AUTH_CUSTOMER')">
                <!-- login box -->
                <li>
                    <a href="<c:url value="/store/customer/logon"/>" >
                                <span class="signininfo normal-label"><s:message code="button.label.signin"
                                                                                 text="Signin"/></span> <span><i
                            class="fa fa-sign-in" aria-hidden="true"></i></span>
                    </a>
                </li>
                </sec:authorize>
                <li>
                    <a href="<c:url value="/store/cart/shoppingCart"/>"><span><i class="fa fa-shopping-cart" aria-hidden="true"></i></span>&nbsp;Cart</a>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input id="searchInput" type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container">

    <tiles:insertAttribute name="body"/>

    <hr>

    <footer class="footer">
        <div class="container">
            <p class="text-muted">&copy; dynamicheart 2017-<%=Calendar.getInstance().get(Calendar.YEAR)%>
            </p>
        </div>
    </footer>
</div>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>

    $("#searchInput").bind("keypress", {}, keypressInBox);

    function keypressInBox(e) {
        var code = (e.keyCode ? e.keyCode : e.which);
        if (code == 13) { //Enter keycode
            e.preventDefault();
            var keyword = $('#searchInput').val();
            location.href='<c:url value="/store/search/book"/>' + "?keyword=" + keyword;
        }
    };
</script>

</body>
</html>
