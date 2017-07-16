<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<div>

    <div class="row">
        <div class="col-lg-12">
        <h3 class="page-header">
            <c:choose>
                <c:when test="${customer.id!=null && customer.id>0}">
                    <s:message code="label.customer.editcustomer" text="Edit Customer"/>
                </c:when>
                <c:otherwise>
                    <s:message code="label.customer.createcustomer" text="Create Customer"/>

                </c:otherwise>
            </c:choose>

        </h3>
        <br/>
        </div>
    </div>

    <c:url var="saveCustomer" value="/admin/customer/save"/>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form:form method="POST" commandName="customer" action="${saveCustomer}">

                                <form:errors id="customer.error" path="*" cssClass="alert alert-error" element="div"/>
                                <div id="customerError" class="alert alert-error" style="display:none;"></div>
                                <div id="customerSuccess" class="alert alert-success"
                                     style="
                                     <c:choose>
                                     <c:when test="${success!=null}">display:block;</c:when>
                                     <c:otherwise>display:none;</c:otherwise></c:choose>">
                                    <s:message code="message.success" text="Request successful"/>
                                </div>

                                <form:hidden id="customerId" path="id"/>


                                <div class="form-group">
                                    <label><s:message code="label.generic.username" text="User Name"/></label>
                                    <div>
                                        <form:input cssClass="form-control" path="nick"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.generic.language" text="Language"/></label>
                                    <div>
                                        <form:select items="${languages}" cssClass="form-control" itemValue="id" itemLabel="code" path="defaultLanguage.id">
                                            <form:options/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.customer.email" text="Email"/></label>
                                    <div>
                                        <form:input cssClass="form-control" maxlength="96" path="emailAddress"/>
                                        <span class="help-inline"><form:errors path="emailAddress"
                                                                               cssClass="error"/></span>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label><s:message code="label.customer.gender" text="Gender"/></label>
                                    <div>
                                        <form:select cssClass="form-control" path="gender">
                                            <form:options/>
                                        </form:select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.generic.password" text="Password"/></label>
                                    <div>
                                        <form:input type="password" cssClass="form-control" path="password"/>

                                    </div>
                                </div>

                                <hr>

                                <div class="form-actions">
                                    <div class="pull-right">
                                        <button type="submit" class="btn btn-success"><s:message
                                                code="button.label.save"
                                                text="Save"/></button>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.min.js"/>

<script>
    $(document).ready(function () {
    });
</script>

