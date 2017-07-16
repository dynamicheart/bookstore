<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.2/css/select.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/select/1.2.2/js/dataTables.select.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>

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
                    <th class="dt-head-center">ISBN</th>
                    <th class="dt-head-center">Price</th>
                    <th class="dt-head-center">Quantity</th>
                    <th class="dt-head-center">SubTotal</th>
                    <th class="dt-head-center">Operation</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart.shoppingCartItems}" var="item">
                    <tr class="dt-body-center">
                        <td></td>
                        <td class="dt-body-center"><img src="${item.value.image.imageUrl}" class="cart-image"></td>
                        <td class="dt-body-center">${item.value.name}</td>
                        <td class="dt-body-center">${item.value.bookIsbn}</td>
                        <td class="dt-body-center">${item.value.bookPrice}</td>
                        <td class="dt-body-center"><input id="quantity_${item.value.bookIsbn}" min="1" step="1" type="number" class="spinner" onchange="reCalculate(${item.value.bookIsbn})" value="${item.value.quantity}"/> </td>
                        <td id="subTotal_${item.value.bookIsbn}" class="dt-body-center">${item.value.subTotal}</td>
                        <td class="dt-body-center"><button class='dt-delete btn btn-default' onclick="removeItem(event,${item.value.bookIsbn})" type='button'><i class='fa fa-trash'></i></button></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pull-right">
                <div class="row">
                    <h3>Total:</h3><h3 id="total">${cart.total}</h3>
                </div>
                <button id="checkoutButton" class="btn btn-primary"">Checkout</button>
            </div>
        </c:when>
        <c:otherwise>
            <p>Cart is empty</p>
        </c:otherwise>
    </c:choose>
</div>

<script>
    $(".spinner").unbind("keypress");
    $(".spinner").bind("keydown", function (event) {
        event.preventDefault();
    });
    $(".spinner").focus(function () {
        $(this).blur();
    });

    function removeItem(e, isbn) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: "/store/cart/removeShoppingCartItem",
            data: "bookIsbn=" + isbn,
            cache:false,
            dataType:'json',
            'success': function(response) {
                if (response.status==0) {
                    location.reload();
                } else {
                }
            }
        });
    }

    function reCalculate(isbn) {
        var quantity = $("#quantity_"+ isbn).val();
        $.ajax({
            type: "GET",
            url: "/store/cart/updateShoppingCartItem",
            data: "bookIsbn=" + isbn + "&quantity=" + quantity,
            cache:false,
            dataType:'json',
            'success': function(response) {
                if (response.status==0) {
                    $('#subTotal_'+ isbn).html(response.result.subTotal);
                    $('#total').html(response.result.total);
                } else {
                }
            }
        });
    }

    $(document).ready(function () {
       var cartTable = $('#cartTable').DataTable({
           dom: 'Bfrtip',
           paging: false,
           ordering: false,
           searching: false,
           columns: [
               { "": "" },
               { "": "" },
               { "": "" },
               { "data": "isbn" },
               { "": "" },
               { "": "" },
               { "": ""},
               { "": ""}
           ],
           columnDefs: [ {
               orderable: false,
               className: 'select-checkbox',
               targets:   0
           } ],
           select: {
               style:    'multi',
               selector: 'td:first-child'
           },
           buttons: [
               {
                   text: 'Select all',
                   action: function () {
                       cartTable.rows().select();
                   }
               },
               {
                   text: 'Select none',
                   action: function () {
                       cartTable.rows().deselect();
                   }
               }
           ]
       });


       $('#checkoutButton').on('click',function (){
           var itemList = [];

           var selectedItem = cartTable.rows({selected:true}).data();

           for(var i = 0; i < selectedItem.length; i++){
               itemList.push(selectedItem[i]["isbn"]);
           }

           $.ajax({
               type: "POST",
               url: "/store/order/checkout",
               data: JSON.stringify(itemList),
               contentType: "application/json; charset=utf-8",
               async: false,
               cache:false,
               dataType:'json',
               'success': function(response) {
                   if (response.status==0) {
                       window.location = "/store/customer/order/orders";
                   } else if(response.status == -2){
                       window.location = "/store/customer/logon";
                   }
               }
           });
       });

    });
</script>