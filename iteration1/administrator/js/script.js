function addUser() {
    // get values
    var user_name = $("#user_name").val();
    var user_password = $("#user_password").val();

    // Add record
    $.post("ajax/addUser.php", {
        user_name: user_name,
        user_password: user_password,
    }, function(data, status) {
        // close the popup
        $("#add_new_user_modal").modal("hide");

        // read records again
        readRecords();

        // clear fields from the popup
        $("#user_name").val("");
        $("#user_password").val("");
    });
}

function addBook() {
    // get values
    var book_name = $("#book_name").val();
    var book_author = $("#book_author").val();
    var book_price = $("#book_price").val();
    var book_stocks = $("#book_stocks").val();
    var book_rate = $("#book_rate").val();

    // Add record
    $.post("ajax/addBook.php", {
        book_name: book_name,
        book_author: book_author,
        book_price: book_price,
        book_stocks: book_stocks,
        book_rate: book_rate,
    }, function(data, status) {
        // close the popup
        $("#add_new_book_modal").modal("hide");

        // read records again
        readRecords();

        // clear fields from the popup
        $("#book_name").val("");
        $("#book_author").val("");
        $("#book_price").val("");
        $("#book_stocks").val("");
        $("#book_rate").val("");
    });
}

function addOrder() {
    // get values
    var user_id = $("#order_user_id").val();
    var book_id = $("#order_book_id").val();
    var order_status = $("#order_status").val();
    // Add record
    $.post("ajax/addOrder.php", {
        user_id: user_id,
        book_id: book_id,
        order_status: order_status
    }, function(data, status) {
        // close the popup
        $("#add_new_order_modal").modal("hide");

        // read records again
        readRecords();

        // clear fields from the popup
        $("#order_user_id").val("");
        $("#order_book_id").val("");
        $("#order_status").val("Ordered");
    });
}

// READ records
function readRecords() {
    $.get("ajax/readUsers.php", {}, function(data, status) {
        $("#user_table").html(data);
    });
    $.get("ajax/readBooks.php", {}, function(data, status) {
        $("#book_table").html(data);
    });
    $.get("ajax/readOrders.php", {}, function(data, status) {
        $("#order_table").html(data);
    });
}


function DeleteUser(id) {
    var conf = confirm("Are you sure, do you really want to delete User?");
    if (conf == true) {
        $.post("ajax/deleteUser.php", {
                id: id
            },
            function(data, status) {
                // reload Users by using readRecords();
                readRecords();
            }
        );
    }
}

function DeleteBook(id) {
    var conf = confirm("Are you sure, do you really want to delete Book?");
    if (conf == true) {
        $.post("ajax/deleteBook.php", {
                id: id
            },
            function(data, status) {
                // reload Users by using readRecords();
                readRecords();
            }
        );
    }
}

function DeleteOrder(id) {
    var conf = confirm("Are you sure, do you really want to delete Order?");
    if (conf == true) {
        $.post("ajax/deleteOrder.php", {
                id: id
            },
            function(data, status) {
                // reload Users by using readRecords();
                readRecords();
            }
        );
    }
}

function GetUserDetails(id) {
    // Add User ID to the hidden field for furture usage
    $("#hidden_user_id").val(id);
    $.post("ajax/readUserDetails.php", {
            id: id
        },
        function(data, status) {
            // PARSE json data
            var user = JSON.parse(data);
            // Assing existing values to the modal popup fields
            $("#update_user_name").val(user.user_name);
            $("#update_user_password").val(user.user_password);
        }
    );
    // Open modal popup
    $("#update_user_modal").modal("show");
}

function GetBookDetails(id) {
    // Add User ID to the hidden field for furture usage
    $("#hidden_book_id").val(id);
    $.post("ajax/readBookDetails.php", {
            id: id
        },
        function(data, status) {
            // PARSE json data
            var book = JSON.parse(data);
            // Assing existing values to the modal popup fields
            $("#update_book_name").val(book.book_name);
            $("#update_book_author").val(book.book_author);
            $("#update_book_price").val(book.book_price);
            $("#update_book_stocks").val(book.book_price);
            $("#update_book_rate").val(book.book_rate);
        }
    );
    // Open modal popup
    $("#update_book_modal").modal("show");
}

function GetOrderDetails(id) {
    // Add User ID to the hidden field for furture usage
    $("#hidden_order_id").val(id);
    $.post("ajax/readOrderDetails.php", {
            id: id
        },
        function(data, status) {
            // PARSE json data
            var order = JSON.parse(data);
            // Assing existing values to the modal popup fields
            $("#update_order_user_id").val(order.user_id);
            $("#update_order_book_id").val(order.book_id);
            $("#update_order_status").val(order.order_status);
        }
    );
    // Open modal popup
    $("#update_order_modal").modal("show");
}

function UpdateUserDetails() {
    // get values
    var user_name = $("#update_user_name").val();
    var user_password = $("#update_user_password").val();

    // get hidden field value
    var id = $("#hidden_user_id").val();

    // Update the details by requesting to the server using ajax
    $.post("ajax/updateUserDetails.php", {
            id: id,
            user_name: user_name,
            user_password: user_password,
        },
        function(data, status) {
            // hide modal popup
            $("#update_user_modal").modal("hide");
            // reload Users by using readRecords();
            readRecords();
        }
    );
}

function UpdateBookDetails() {
    // get values
    var update_book_name = $("#update_book_name").val();
    var update_book_author = $("#update_book_author").val();
    var update_book_price = $("#update_book_price").val();
    var update_book_stocks = $("#update_book_stocks").val();
    var update_book_rate = $("#update_book_rate").val();

    // get hidden field value
    var id = $("#hidden_book_id").val();

    // Update the details by requesting to the server using ajax
    $.post("ajax/updateBookDetails.php", {
            id: id,
            book_name: update_book_name,
            book_author: update_book_author,
            book_price: update_book_price,
            book_stocks: update_book_stocks,
            book_rate: update_book_rate,
        },
        function(data, status) {
            // hide modal popup
            $("#update_book_modal").modal("hide");
            // reload Users by using readRecords();
            readRecords();
        }
    );
}

function UpdateOrderDetails() {
    // get values
    var user_id = $("#update_order_user_id").val();
    var book_id = $("#update_order_book_id").val();
    var order_status = $("#update_order_status").val();

    // get hidden field value
    var id = $("#hidden_order_id").val();

    // Update the details by requesting to the server using ajax
    $.post("ajax/updateOrderDetails.php", {
            id: id,
            user_id: user_id,
            book_id: book_id,
            order_status: order_status,
        },
        function(data, status) {
            // hide modal popup
            $("#update_order_modal").modal("hide");
            // reload Users by using readRecords();
            readRecords();
        }
    );
}

$(document).ready(function() {
    // READ recods on page load
    readRecords(); // calling function
});
