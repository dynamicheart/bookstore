<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="mainContent" class="container">
    <c:forEach items="${books.getBooks()}" var="book">
        <div class="col-sm-3 col-lg-3 col-md-3">
            <div class="thumbnail">
                <img class="img-responsive" src="${book.getDefaultImage().getImageUrl()}" alt="Chania">
                <div class="caption">
                    <p><a class="book-card-name" href="#">${book.getDescription().getName()}</a></p>
                    <p  class="book-card-author">${book.getPublisher().getDescription().getName()}</p>
                    <h4 class="book-card-price">${book.getDisplayPrice()}</h4>
                </div>
            </div>
        </div>
    </c:forEach>
</div>