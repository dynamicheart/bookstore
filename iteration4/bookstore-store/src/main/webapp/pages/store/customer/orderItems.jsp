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
        <c:when test="${not empty orderItems}">
            <table id="orderItemsTable" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th class="dt-head-center">Isbn</th>
                    <th class="dt-head-center">Name</th>
                    <th class="dt-head-center">Quantity</th>
                    <th class="dt-head-center">Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${orderItems}" var="item">
                    <tr class="dt-body-center">
                        <td class="dt-body-center">${item.isbn}</td>
                        <td class="dt-body-center">${item.itemName}</td>
                        <td class="dt-body-center">${item.itemQuantity}</td>
                        <td class="dt-body-center">${item.oneTimeCharge} </td>
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
    $(document).ready(function () {
        $('#orderItemsTable').DataTable({
            paging: false,
            ordering: false,
            searching: false
        });
    });
</script>

