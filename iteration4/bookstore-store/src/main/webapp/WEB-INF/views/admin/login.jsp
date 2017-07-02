<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>

    <%
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>





<head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title><s:message code="label.storeadministration"
                      text="Store administration" />
    </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
    <style type=text/css>
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #eee;
        }

        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: 0 auto;
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
        }
        .form-signin .checkbox {
            font-weight: normal;
        }
        .form-signin .form-control {
            position: relative;
            height: auto;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            padding: 10px;
            font-size: 16px;
        }
        .form-signin .form-control:focus {
            z-index: 2;
        }
        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>

</head>
    <body>

    <div class="container">

        <form method="post" class="form-signin" action="<c:url value="/login"/>">
            <h2 class="form-signin-heading">Please sign in</h2>
            <div class="row">
                <c:if test="${not empty param.login_error}">
                    <div class="alert alert-error">
                        <s:message code="errors.invalidcredentials"
                                   text="Invalid username or password" />
                    </div>
                </c:if>
            </div>


            <div class="form-group">
                <div>
                    <input class="form-control" type="text" id="username" bookImage="username"
                           placeholder="<s:message code="label.generic.username" text="Username"/>">
                </div>
            </div>


            <div class="form-group">
                <div>
                    <input class="form-control" type="password" id="password" bookImage="password"
                           placeholder="<s:message code="label.generic.password" text="Password"/>">
                </div>
            </div>


            <div class="form-group">
                <div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>


            <div class="form-actions">
                <div>
                    <button type="submit" class="btn btn-lg btn-primary btn-block"><s:message
                            code="button.label.save"
                            text="Sign in"/></button>
                </div>
            </div>
        </form>

    </div> <!-- /container -->

    </body>
</html>