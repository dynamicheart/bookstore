<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<div id="main-content" class="row-fluid show-grid container">
    <h1 class="cart-title"><s:message code="label.mycart" text="My Cart" /></h1>
    <div id="store.error" class="alert alert-error alert-danger" style="display:none;"><s:message code="message.error.shoppingcart.update" text="An error occurred while updating the shopping cart"/></div>

    <c:choose>
        <c:when test="${not empty cart}">
            <table id="cartTable" class="row-border" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th></th>
                    <th class="dt-head-center">Cover</th>
                    <th class="dt-head-center">Name</th>
                    <th class="dt-head-center">Price</th>
                    <th class="dt-head-center">Quantity</th>
                    <th class="dt-head-center"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.shoppingCartItems}" var="item">
                    <tr class="dt-body-center">
                        <td class="dt-body-center"><img src="${item.value.image.imageUrl}" class="cart-image"></td>
                        <td class="dt-body-center">${item.value.name}</td>
                        <td class="dt-body-center">${item.value.bookPrice}</td>
                        <td class="dt-body-center">${item.value.quantity}</td>
                        <td class="dt-body-center"><button class='dt-delete btn btn-default' onclick="" type='button'><i class='fa fa-trash'></i></button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Cart is empty</p>
        </c:otherwise>
    </c:choose>
</div>

<script>
    $(document).ready(function () {
       var cartTable = $('#cartTable').DataTable({
           "paging": false,
           "ordering": false,
           "searching": false
       });
    });
</script>