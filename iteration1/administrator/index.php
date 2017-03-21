<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Online Bookstore Management System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS File  -->
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
    <!-- Content Section -->
    <div class="container">
        <div>
            <div class="row">
                <div class="col-md-12">
                    <h1>Online Bookstore Management System</h1>
                </div>
            </div>
            <ul class="nav nav-pills list-inline">
                <li class="active">
                    <a data-toggle="pill" href="#user_tab">Users</a></li>
                <li>
                    <a data-toggle="pill" href="#book_tab">Books</a></li>
                <li>
                    <a data-toggle="pill" href="#order_tab">Orders</a></li>
            </ul>
            <div class="tab-content">
                <div id="user_tab" class="row tab-pane fade in active">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <button class="btn btn-success" id="add_user_button" data-toggle="modal" data-target="#add_new_user_modal">Add New User</button>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h3>User Records:</h3>
                        <div id="user_table"></div>
                    </div>
                </div>
                <div id="book_tab" class="row tab-pane fade in">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <button class="btn btn-success" id="add_book_button" data-toggle="modal" data-target="#add_new_book_modal">Add New Book</button>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h3>Book Records:</h3>
                        <div id="book_table"></div>
                    </div>
                </div>
                <div id="order_tab" class="row tab-pane fade in">
                    <div class="col-md-12">
                        <div class="pull-right">
                            <button class="btn btn-success" id="add_order_button" data-toggle="modal" data-target="#add_new_order_modal">Add New Order</button>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <h3>Order Records:</h3>
                        <div id="order_table"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /Content Section -->
    <!-- Bootstrap Modals -->
    <!-- Modal - Add New User -->
    <div class="modal fade" id="add_new_user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Record</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="user_name">Name</label>
                        <input type="text" id="user_name" placeholder="Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="user_password">Password</label>
                        <input type="text" id="user_password" placeholder="Password" class="form-control" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="addUser()">Add User</button>
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <!-- Modal - Update User details -->
    <div class="modal fade " id="update_user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="update_user_name">User Name</label>
                        <input type="text" id="update_user_name" placeholder="User Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_user_password">Password</label>
                        <input type="text" id="update_user_password" placeholder="Password" class="form-control" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="UpdateUserDetails()">Save Changes</button>
                    <input type="hidden" id="hidden_user_id">
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <!-- Modal - Add New Record/User -->
    <div class="modal fade" id="add_new_book_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Book</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="book_name">Book Name</label>
                        <input type="text" id="book_name" placeholder="Book Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="book_author">Author</label>
                        <input type="text" id="book_author" placeholder="Author Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="book_price">Price</label>
                        <input type="text" id="book_price" placeholder="Price" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="book_stocks ">Stocks</label>
                        <input type="text" id="book_stocks" placeholder="Stocks" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="book_rate">Rate</label>
                        <input type="text" id="book_rate" placeholder="Rate" class="form-control" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="addBook()">Add Book</button>
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <!-- Modal - Update User details -->
    <div class="modal fade" id="update_book_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="update_book_name">Book Name</label>
                        <input type="text" id="update_book_name" placeholder="Book Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_book_author">Author</label>
                        <input type="text" id="update_book_author" placeholder="Author Name" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_book_price">Price</label>
                        <input type="text" id="update_book_price" placeholder="Price" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_book_stocks ">Stocks</label>
                        <input type="text" id="update_book_stocks" placeholder="Stocks" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_book_rate">Rate</label>
                        <input type="text" id="update_book_rate" placeholder="Rate" class="form-control" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="UpdateBookDetails()">Save Changes</button>
                    <input type="hidden" id="hidden_book_id">
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <!-- Modal - Add New Record/User -->
    <div class="modal fade" id="add_new_order_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Add New Order</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="order_user_id">User ID</label>
                        <input type="text" id="order_user_id" placeholder="User ID" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="order_book_id">Book ID</label>
                        <input type="text" id="order_book_id" placeholder="Book ID" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="order_status">Order Status</label>
                        <select class="form-control" id="order_status">
                            <option>Ordered</option>
                            <option>Processing</option>
                            <option>Completed</option>
                            <option>Canceled</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="addOrder()">Add Order</button>
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <!-- Modal - Update User details -->
    <div class="modal fade" id="update_order_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Update</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="update_order_user_id">User ID</label>
                        <input type="text" id="update_order_user_id" placeholder="User ID" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_order_book_id">Book ID</label>
                        <input type="text" id="update_order_book_id" placeholder="Book ID" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="update_order_status">Order Status</label>
                        <select class="form-control" id="update_order_status">
                            <option>Ordered</option>
                            <option>Processing</option>
                            <option>Completed</option>
                            <option>Canceled</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-primary" onclick="UpdateOrderDetails()">Save Changes</button>
                    <input type="hidden" id="hidden_order_id">
                </div>
            </div>
        </div>
    </div>
    <!-- // Modal -->
    <div class="container">
        <hr>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; dynamicheart 2017</p>
                </div>
            </div>
        </footer>
    </div>
    <!-- Jquery JS file -->
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <!-- Bootstrap JS file-->
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Custom JS file -->
    <script type="text/javascript" src="js/script.js"></script>
</body>

</html>
