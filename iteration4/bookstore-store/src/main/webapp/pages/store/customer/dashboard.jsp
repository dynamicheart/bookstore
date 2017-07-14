<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:set var="profile_url" value="${pageContext.request.contextPath}/store/customer/profile"/>
<c:set var="orders_url" value="${pageContext.request.contextPath}/store/customer/orders"/>
<c:set var="logout_url" value="${pageContext.request.contextPath}/store/customer/logout"/>

<div id="main-content" class="container clearfix row-fluid">
    <div class="row">
        <div class="col-sm-6 col-lg-6 col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">My Account</div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="${profile_url}"><s:message code="label.my.profile" text="Profile"/></a></li>
                        <li class="list-group-item">Change Password</li>
                        <li class="list-group-item"><a href="${logout_url}"><s:message code="button.label.logout" text="Logout"/></a></li>
                        <li class="list-group-item">&nbsp;</li>
                        <li class="list-group-item">&nbsp;</li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-6 col-lg-6 col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">Orders</div>
                <div class="panel-body">
                    <ul class="list-group">
                        <li class="list-group-item"><a href="${orders_url}"><s:message code="label.order.title" text="Orders"/></a></li></li>
                        <li class="list-group-item">&nbsp;</li>
                        <li class="list-group-item">&nbsp;</li>
                        <li class="list-group-item">&nbsp;</li>
                        <li class="list-group-item">&nbsp;</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>