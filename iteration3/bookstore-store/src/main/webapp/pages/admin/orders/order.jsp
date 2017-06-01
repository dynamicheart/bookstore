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
                <c:when test="${order.id!=null && order.id>0}">
                    <s:message code="label.order.editorder" text="Edit Order"/>
                </c:when>
                <c:otherwise>
                    <s:message code="label.order.createorder" text="Create Order"/>
                </c:otherwise>
            </c:choose>

        </h3>
        <br/>
        </div>
    </div>

    <c:url var="saveOrder" value="/admin/order/save"/>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form:form method="POST" commandName="order" action="${saveOrder}">

                                <form:errors id="order.error" path="*" cssClass="alert alert-error" element="div"/>
                                <div id="orderError" class="alert alert-error" style="display:none;"></div>
                                <div id="orderSuccess" class="alert alert-success"
                                     style="
                                     <c:choose>
                                     <c:when test="${success!=null}">display:block;</c:when>
                                     <c:otherwise>display:none;</c:otherwise></c:choose>">
                                    <s:message code="message.success" text="Request successful"/>
                                </div>

                                <form:hidden id="orderId" path="order.id"/>
                                <form:hidden path="order.customerId"/>

                                <div class="form-group">
                                    <label><s:message code="label.order.isbn" text="Order ISBN"/></label>
                                    <div>
                                        <form:input cssClass="form-control" readonly="true" path="isbn"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.order.title" text="Order Title"/></label>
                                    <div>
                                        <form:input cssClass="form-control" readonly="true" path="title"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.order.price" text="Order Price"/></label>
                                    <div>
                                        <form:input cssClass="form-control" readonly="true" path="price"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.order.quantity" text="Order Quantity"/></label>
                                    <div>
                                        <form:input cssClass="form-control" readonly="true" path="quantity"/>
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

