<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="/store" var="home_url" scope="request"/>

<div id="mainContent" class="container">
    <c:forEach items="${books.getBooks()}" var="book">
        <div class="col-sm-3 col-lg-3 col-md-3">
            <div class="thumbnail">
                <img class="img-responsive" src="${book.getDefaultImage().getImageUrl()}" alt="Chania">
                <div class="caption">
                    <p><a class="book-card-name"
                          href="<c:url value="/store/book/"/><c:out value="${book.description.friendlyUrl}"/>">${book.getDescription().getName()}</a>
                    </p>
                    <p class="book-card-author">${book.getPublisher().getDescription().getName()}</p>
                    <h4 class="book-card-price">${book.getDisplayPrice()}</h4>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="row">
    <ul class="pagination">
        <li class="${paginationData.currentPage eq 1 ? 'disabled' : ''}">
            <c:choose>
                <c:when test="${paginationData.currentPage eq 1}">
                    <span aria-hidden="true">&laquo;</span>
                </c:when>
                <c:otherwise>
                    <a href="${home_url}?page=${paginationData.currentPage - 1}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
        <c:forEach begin="1" end="${paginationData.totalPages}" varStatus="paginationDataStatus">
            <li class="${paginationData.currentPage eq (paginationDataStatus.index) ? 'active' : ''}"><a
                    href="${home_url}?page=${paginationDataStatus.index}">${paginationDataStatus.index}</a></li>
        </c:forEach>
        <li class="${paginationData.currentPage eq paginationData.totalPages ? 'disabled' : ''}">

            <c:choose>
                <c:when test="${paginationData.currentPage eq paginationData.totalPages}">
                    <span aria-hidden="true">&raquo;</span>
                </c:when>
                <c:otherwise>
                    <a href="${home_url}?page=${paginationData.currentPage + 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>

</div>