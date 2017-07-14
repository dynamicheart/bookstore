<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="main-content" class="row-fluid show-grid container">
    <h1 class="cart-title"><s:message code="label.cart.revieworder" text="Review your order" /></h1>
    <div id="store.error" class="alert alert-error alert-danger" style="display:none;"><s:message code="message.error.shoppingcart.update" text="An error occurred while updating the shopping cart"/></div>

    <table id="mainCartTable" class="table table-hover table-condensed">

        <c:if test="${not empty cart}">
            <c:choose>
                <c:when test="${not empty cart.shoppingCartItems}">
                    <c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem"
                               varStatus="itemStatus">
                        <c:if test="${itemStatus.index eq 0}">

                            <thead>
                            <tr>
                                <th><s:message code="label.generic.item.title" text="Item"/></th>
                                <th><s:message code="label.quantity" text="Quantity"/></th>
                                <th><s:message code="label.generic.price" text="Price"/></th>
                                <th><s:message code="label.order.total" text="Total"/></th>
                                <th></th>

                            </tr>
                            </thead>
                            <tbody>
                        </c:if>
                        <form:form action="${updateShoppingCartItemUrl}"
                                   id="shoppingCartLineitem_${shoppingCartItem.id}">
                            <tr>
                                <td data-th="<s:message code="label.generic.item.title" text="Item"/>">
                                    <div class="row-cart">

                                        <div class="col-sm-4 hidden-xs">
                                            <c:if test="${shoppingCartItem.image!=null}">
                                                <img width="60" src="<c:url value="${shoppingCartItem.image}"/>"
                                                     class="">
                                            </c:if>
                                        </div>
                                        <div class="col-sm-8">
                                            <span class="nomargin"><strong>${shoppingCartItem.name}</strong></span>

                                            <c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
                                                <p>
                                                <ul>
                                                    <c:forEach items="${shoppingCartItem.shoppingCartAttributes}"
                                                               var="option">
                                                        <li>${option.optionName} - ${option.optionValue}</li>
                                                    </c:forEach>
                                                </ul>
                                                </p>
                                            </c:if>
                                        </div>
                                    </div>
                                </td>
                                <td width="10%" data-th="<s:message code="label.quantity" text="Quantity"/>">

                                    <input type="number" class="input-small quantity form-control text-center"
                                           value="${shoppingCartItem.quantity}" name="quantity"
                                           id="${shoppingCartItem.id}"
                                           <c:if test="${shoppingCartItem.productVirtual==true}">readonly</c:if>>

                                </td>

                                <td data-th="<s:message code="label.generic.price" text="Price"/>">
                                    <strong>${shoppingCartItem.price}</strong></td>
                                <td data-th="<s:message code="label.order.total" text="Total"/>" class="">
                                    <strong>${shoppingCartItem.subTotal}</strong></td>

                                <td width="10%" class="actions" data-th="">
                                    <button type="button" class="btn btn-danger btn-sm"
                                            onclick="javascript:updateLineItem('${shoppingCartItem.id}','${removeShoppingCartItemUrl}'); return false;"
                                            style="margin-top:0px !important;"><i
                                            class="fa fa-trash-o"></i>&nbsp;<s:message code="label.generic.remove"
                                                                                       text="Remove"/></button>
                                    <input type="hidden" name="lineItemId" id="lineItemId"
                                           value="${shoppingCartItem.id}"/>

                                </td>

                            </tr>
                        </form:form>


                    </c:forEach>
                    <c:forEach items="${cart.totals}" var="total">
                        <tr class="subt" class="hidden-xs">
                            <td colspan="2">&nbsp;</td>
                            <td><strong><s:message code="${total.code}"
                                                   text="label [${total.code}] not found"/></strong></td>
                            <td colspan="2"><strong><sm:monetary value="${total.value}"/></strong></td>
                        </tr>
                    </c:forEach>


                    </tbody>

                    <tfoot>

                    <tr>
                        <td colspan="3">

                            <a href="#" onClick="javascript:updateCart('#mainCartTable');"
                               class="btn btn-regular"><s:message code="label.order.recalculate"
                                                                  text="Racalculate"/></a>

                            <a href="<c:url value="/shop"/>" class="btn btn-warning"><i
                                    class="fa fa-angle-left"></i> <s:message code="button.label.continue"
                                                                             text="Continue shopping"/></a>
                        </td>
                        <td colspan="2">
                            <button id="checkoutButton" type="submit" class="btn btn-success btn-block"><s:message
                                    code="label.cart.placeorder" text="Place your order"/> <i
                                    class="fa fa-angle-right"></i></button>
                        </td>
                    </tr>
                    </tfoot>

                </c:when>
                <c:otherwise>
                    <tr>
                        <td><s:message code="cart.empty" text="Your Shopping cart is empty"/></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:if>
    </table>
</div>