<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">


    $(document).ready(function() {
        isFormValid();
        $("input[type='text']").on("change keyup paste", function(){
            isFormValid();
        });

        $("input[type='password']").on("change keyup paste", function(){
            isFormValid();
        });

    });


    function isFormValid() {

        if($('.alert-error').is(":visible")) {
            return true;
        }

        if($('.alert-success').is(":visible")) {
            return true;
        }

        $('#registrationError').hide();//reset error message
        var msg = isCustomerFormValid($('#registrationForm'));

        if(msg!=null) {//disable submit button
            $('#submitRegistration').addClass('btn-disabled');
            $('#submitRegistration').prop('disabled', true);
            $('#registrationError').html(msg);
            $('#registrationError').show();
            return false;
        } else {
            $('#submitRegistration').removeClass('btn-disabled');
            $('#submitRegistration').prop('disabled', false);
            $('#registrationError').hide();
            return true;
        }
    }

</script>


<c:set var="register_url" value="${pageContext.request.contextPath}/store/customer/registration"/>

<div id="main-content" class="container clearfix row-fluid">
    <div id="registrationError"  class="alert alert-warning common-row" style="display:none;"></div>
    <div class="span7 col-md-7 no-padding">
        <form:form method="post" action="${register_url}" id="registrationForm" class="form-horizontal" commandName="customer">
            <form:errors path="*" cssClass="alert alert-error alert-danger form-group" element="div" />
            <fieldset>

                <div class="form-group">
                    <label><s:message code="label.generic.username" text="User name" /></label>
                    <div>
                        <s:message code="NotEmpty.customer.userName" text="User name is required" var="msgUserName"/>
                        <form:input path="userName" cssClass="span8 required userName form-control form-control-md" id="userName" title="${msgUserName}"/>
                        <form:errors path="userName" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label><s:message code="label.generic.email" text="Email address"/></label>
                    <div>
                        <s:message code="NotEmpty.customer.emailAddress" text="Email address is required" var="msgEmail"/>
                        <form:input path="emailAddress" cssClass="span8 required email form-control form-control-md" id="email" title="${msgEmail}"/>
                        <form:errors path="emailAddress" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label><s:message code="label.generic.genre" text="Genre"/></label>
                    <div class="controls">
                        <form:select path="gender" class="form-control form-control-lg">
                            <form:option value="M"><s:message code="label.generic.male" text="Male"/></form:option>
                            <form:option value="F"><s:message code="label.generic.female" text="Female"/></form:option>
                        </form:select>
                        <form:errors path="gender" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label><s:message code="label.generic.password" text="Password"/></label>
                    <div>
                        <s:message code="message.password.required" text="Password is required" var="msgPassword"/>
                        <form:password path="password" class="span8 required password form-control form-control-md" id="password" title="${msgPassword}"/>
                        <form:errors path="password" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label><s:message code="label.generic.repeatpassword" text="Repeat password"/></label>
                    <div>
                        <s:message code="message.password.repeat.required" text="Repeated password is required" var="msgRepeatPassword"/>
                        <form:password path="checkPassword" class="span8 required checkPassword form-control form-control-md" id="passwordAgain" title="${msgRepeatPassword}"/>
                        <form:errors path="checkPassword" cssClass="error" />
                    </div>
                </div>


                <div class="form-actions">
                    <div class="pull-right">
                        <input id="submitRegistration" type="submit" value="<s:message code="label.generic.register" text="Register"/>" name="register" class="btn btn-primary btn-large">
                    </div>
                </div>
            </fieldset>
        </form:form>
        <!-- end registration form-->

    </div>
    <!--close .span7-->
</div>