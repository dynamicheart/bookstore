<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

<c:set value="/admin/item/create" var="createItemUrl" scope="request"/>

<div>

    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                <c:choose>
                    <c:when test="${order.order.id!=null && order.order.id>0}">
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

    <div class="btn-group">
        <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><s:message code="label.generic.moreoptions"
                                                                                       text="More options"/> ... <span
                class="caret"></span></button>
        <ul class="dropdown-menu">
            <li><a href="<c:url value="${createItemUrl}" />"><s:message code="label.customer.createcustomer"
                                                                    text="Create Item"/></a></li>
        </ul>
    </div>

    <hr>

    <c:url var="saveOrder" value="/admin/order/save"/>


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <form:form method="POST" enctype="multipart/form-data" commandName="order"
                                       action="${saveOrder}">
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

                                <div class="form-group">
                                    <label><s:message code="label.customer.bookImage" text="Customer"/></label>
                                    <div>
                                        <form:select items="${customers}" cssClass="form-control" itemValue="id"
                                                     itemLabel="nick" path="order.customerId">
                                            <form:options/>
                                        </form:select>
                                    </div>
                                </div>

                                <address>
                                    <div class="form-group">
                                        <label><s:message code="label.customer.firstname"
                                                          text="Billing First Name"/></label>
                                        <div>
                                            <form:input id="customerFirstName" cssClass="form-control"
                                                        path="order.billing.firstName"/>
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <label><s:message code="label.customer.lastname"
                                                          text="Billing Last Name"/></label>
                                        <div>
                                            <form:input id="customerLastName" cssClass="form-control"
                                                        path="order.billing.lastName"/>
                                        </div>
                                    </div>
                                </address>

                                <div class="form-group">
                                    <label><s:message code="label.customer.order.date" text="Order Date"/></label>
                                    <div>
                                        <form:input cssClass="form-control" readonly="true" path="datePurchased"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label><s:message code="label.entity.status" text="Status"/></label>
                                    <div>
                                        <form:select path="order.status" cssClass="form-control">
                                            <form:options items="${orderStatusList}"/>
                                        </form:select>
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
                        <c:if test="${order.order.id!=null && order.order.id>0}">
                            <div class="col-lg-6">
                                <label><s:message code="label.customer.bookImage" text="Order Items"/></label>
                                <table class="table table-bordered table-striped">
                                    <thead>
                                    <tr>
                                        <th colspan="2" width="55%"><s:message code="label.order.item"
                                                                               text="Item"/></th>
                                        <th colspan="1" width="15%"><s:message code="label.quantity"
                                                                               text="Quantity"/></th>
                                        <th width="15%"><s:message code="label.order.price" text="Price"/></th>
                                        <th width="15%"><s:message code="label.order.total" text="Total"/></th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${order.order.orderItems}" var="orderItem" varStatus="counter">
                                        <c:set var="total"
                                               value="${orderItem.oneTimeCharge * orderItem.itemQuantity }"/>
                                        <tr>
                                            <td colspan="2">
                                                <c:out value="${orderItem.itemName}"/>
                                            </td>
                                            <td><c:out value="${orderItem.itemQuantity}"/></td>
                                            <td><strong><c:out value="${orderItem.oneTimeCharge}"/></strong></td>
                                            <td><strong><c:out value="${total}"/></strong></td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td></td>
                                        <td><Strong>Total</Strong></td>
                                        <td><c:out value="${order.order.total}"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
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

