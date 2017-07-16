<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="mainContent" class="container">
    <c:forEach items="${books.books}" var="book">
        <div class="col-sm-3 col-lg-3 col-md-3">
            <div class="thumbnail">
                <img class="img-responsive" src="${book.defaultImage.imageUrl}" alt="Chania">
                <div class="caption">
                    <p><a class="book-card-name" onclick="showDetail(event,'${book.description.friendlyUrl}')"
                          href="#">${book.description.name}</a>
                    <p class="book-card-author">${book.publisher.description.name}</p>
                    <h4 class="book-card-price">${book.price}</h4>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<div class="modal fade product_view" id="modalView">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <h3 id="modalTitle" class="modal-title detail-book-name"></h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <img id="modalImage" src="" class="img-responsive">
                    </div>
                    <div class="col-md-6">
                        <h3 id="modalAuther" class="detail-book-author" ></h3>
                        <h3 id="modalPrice" class="cost detail-book-price"></h3>
                        <input id="modalIsbn" hidden>
                        <hr>
                        <div class="row">
                            <!-- end col -->
                            <div class="col-md-4 col-sm-12">
                                <label for="modalSelect">Quantity</label>
                                <select id="modalSelect" class="form-control" name="select">
                                    <option value="1" selected>1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                            </div>
                            <!-- end col -->
                        </div>
                        <br>
                        <div class="btn-ground">
                            <button type="button" onclick="addToCart(event)" class="btn btn-primary"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Add To Cart</button>
                            <button type="button" class="btn btn-primary"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Buy Now</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function showDetail(e,friendlyUrl) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: "/store/book/" + friendlyUrl,
            cache:false,
            dataType:'json',
            'success': function(response) {
                if (response.status == 0) {
                    var book = response.result.book;
                    $("#modalTitle").html(book.description.name);
                    $("#modalImage").prop("src",book.defaultImage.imageUrl);
                    $('#modalAuther').html(book.publisher.description.name);
                    $('#modalIsbn').val(book.isbn);
                    $("#modalPrice").html(book.price);
                    $("#modalView").modal('show');
                }
            }
        });
    }

    function addToCart(e) {
        e.preventDefault();
        var quantity = $('#modalSelect').val();
        var isbn = $('#modalIsbn').val();
        $.ajax({
            type: "POST",
            url: "/store/cart/addShoppingCartItem",
            data: "bookIsbn=" + isbn + "&quantity=" + quantity,
            cache: false,
            dataType: 'json',
            'success': function (response) {
                if (response.status == 0) {
                    $("#modalView").modal('hide');
                }
            }
        });
    }
</script>
