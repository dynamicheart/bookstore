<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<div id="main-content" class="row-fluid show-grid container">
    <h1><s:message code="label.order.title" text="Orders" /></h1>

    <c:choose>
        <c:when test="${not empty orders}">
            <table id="ordersTable" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="dt-head-center">Order Id</th>
                    <th class="dt-head-center">Status</th>
                    <th class="dt-head-center">Purchase Date</th>
                    <th class="dt-head-center">Customer Id</th>
                    <th class="dt-head-center">Total</th>
                    <th class="dt-head-center"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orders}" var="order">
                    <tr class="dt-body-center">
                        <td class="dt-body-center">${order.id}</td>
                        <td class="dt-body-center">${order.status}</td>
                        <td class="dt-body-center">${order.datePurchased}</td>
                        <td class="dt-body-center">${order.customerId} </td>
                        <td class="dt-body-center">${order.total}</td>
                        <td class="dt-body-center">
                            <button class='dt-edit btn btn-default' onclick="getItems(event,${order.id})" type='button'><i class='fa fa-edit'></i></button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No orders</p>
        </c:otherwise>
    </c:choose>
</div>

<script>
    function getItems(e,id) {
        e.preventDefault();
        window.location = "/store/customer/order/items?orderId="+ id;
    }

    $(document).ready(function () {
        $('#ordersTable').DataTable({
            paging: false,
            ordering: false,
            searching: false
        });
    });
</script>
