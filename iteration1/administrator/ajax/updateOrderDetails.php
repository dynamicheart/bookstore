<?php
// include Database connection file
include("../../db-connector/db_connection.php");

// check request
if(isset($_POST))
{
    // get values
    $id = $_POST['id'];
    $user_id = $_POST['user_id'];
    $book_id = $_POST['book_id'];
    $order_status = $_POST['order_status'];

    // Updaste User details
    $query = "UPDATE orders SET user_id = '$user_id', book_id = '$book_id', order_status = '$order_status' WHERE order_id = '$id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
}
