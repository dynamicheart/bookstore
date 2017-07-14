<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="mainContent" class="container">
    <div class="row">
        <div class="col-sm-4 col-lg-4 col-md-4">
            <img class="img-responsive detail-book-photo" src="${book.getDefaultImage().getImageUrl()}" alt="Chania" />
        </div>
        <div class="col-sm-8 col-lg-8 col-md-8">
            <div class="detail-book-name">${book.getDescription().getName()}</div>
            <div class="row">
                <div class="col-sm-4 col-lg-4 col-md-4">
                    <div class="detail-book-author">${book.getPublisher().getDescription().getName()}</div>
                </div>
            </div>
            <div class="detail-book-price">${book.getDisplayPrice()}</div>
            <hr>
            <div class="row">
                <div class="col-sm-2 col-lg-2 col-md-2">
                    <input type="number" class="form-control" placeholder="Quantity" value="0" min="0" step="1" >
                </div>
            </div>
            <hr>
            <div class="row pull-right">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary"><i class="fa fa-shopping-cart" aria-hidden="true"></i></span>&nbsp;Add to Cart</button>
                    <button type="button" class="btn btn-success"><i class="fa fa-smile-o" aria-hidden="true"></i></i></span>&nbsp;Buy now!</button>
                </div>
            </div>

        </div>
    </div>
</div>
