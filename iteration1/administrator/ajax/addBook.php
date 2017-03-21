<?php
	if(isset($_POST['book_name'])&&
		isset($_POST['book_author'])&&
		isset($_POST['book_price'])&&
		isset($_POST['book_rate'])&&
		isset($_POST['book_stocks']))

	{
		// include Database connection file 
		include("../../db-connector/db_connection.php");

		// get values 
		$book_name = $_POST['book_name'];
		$book_author = $_POST['book_author'];
		$book_price = $_POST['book_price'];
		$book_rate = $_POST['book_rate'];
		$book_stocks = $_POST['book_stocks'];

		$query = "INSERT INTO books(book_name, book_author,book_price,book_rate,book_stocks) VALUES($book_name,$book_author,$book_price,$book_rate,$book_stocks)";
		if (!$result = mysql_query($query)) {
	        exit(mysql_error());
	    }
	    echo "1 Record Added!";
	}
?>
