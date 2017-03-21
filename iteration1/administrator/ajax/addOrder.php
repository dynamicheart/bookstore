<?php
	if(isset($_POST['user_id']) && isset($_POST['book_id']) && isset($_POST['order_status'])){;}
	{
		// include Database connection file 
		include("../../db-connector/db_connection.php");

		// get values 
		$user_id = $_POST['user_id'];
		$book_id = $_POST['book_id'];
		$order_status = $_POST['order_status'];

		$query = "INSERT INTO orders(user_id, book_id, order_status) VALUES('$user_id', '$book_id', '$order_status')";
		if (!$result = mysql_query($query)) {
	        exit(mysql_error());
	    }
	    echo "1 Record Added!";
	}
?>