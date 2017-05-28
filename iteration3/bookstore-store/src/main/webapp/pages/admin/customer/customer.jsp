<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


<div>

    <h3>
        <c:choose>
            <c:when test="${customer.id!=null && customer.id>0}">
                <s:message code="label.customer.editcustomer" text="Edit Customer" />
            </c:when>
            <c:otherwise>
                <s:message code="label.customer.createcustomer" text="Create Customer" />
            </c:otherwise>
        </c:choose>

    </h3>
    <br/>


    <c:set var="customerAttr" value="${customer}"/>

    <c:url var="saveCustomer" value="/admin/customer/save"/>


    <form:form method="POST" commandName="customer" action="${saveCustomer}">

        <form:errors id="customer.error" path="*" cssClass="alert alert-error" element="div" />
        <div id="customerError" class="alert alert-error" style="display:none;"></div>
        <div id="customerSuccess" class="alert alert-success"
             style="<c:choose>
             <c:when test="${success!=null}">display:block;</c:when>
             <c:otherwise>display:none;</c:otherwise></c:choose>">
            <s:message code="message.success" text="Request successful"/>
        </div>

        <form:hidden id="customerId" path="id" />


        <div class="control-group">
            <label><s:message code="label.generic.username" text="User Name"/></label>
            <div class="controls">
                <span class="input-large uneditable-input">${customer.nick}</span><form:hidden path="nick" />
            </div>
        </div>


        <div class="control-group">
            <label><s:message code="label.customer.email" text="Email"/></label>
            <div class="controls">
                <form:input cssClass="input-large highlight"  maxlength="96" path="emailAddress" />
                <span class="help-inline"><form:errors path="emailAddress" cssClass="error" /></span>
            </div>
        </div>


        <div class="control-group">
            <div class="controls">
                <form:select path="gender">
                    <form:options/>
                </form:select>
            </div>
        </div>

        <!--</div>-->
        <div class="form-actions">
            <div class="pull-right">
                <button type="submit" class="btn btn-success"><s:message code="button.label.save" text="Save"/></button>
            </div>
        </div>

    </form:form>


