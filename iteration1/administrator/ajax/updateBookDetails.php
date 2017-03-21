<?php
// include Database connection file
include("../../db-connector/db_connection.php");

// check request
if(isset($_POST))
{
    // get values
    $id = $_POST['id'];
    $book_name = $_POST['book_name'];
    $book_author = $_POST['book_author'];
    $book_price = $_POST['book_price'];
    $book_rate = $_POST['book_rate'];
    $book_stocks = $_POST['book_stocks'];

    // Updaste User details
    $query = "UPDATE books SET book_name = '$book_name', book_author = '$book_author' ,book_price = '$book_price', book_rate = '$book_rate', book_stocks = '$book_stocks' WHERE book_id = '$id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
}
