<?php
	if(isset($_POST['user_name']) && isset($_POST['user_password']))
	{
		// include Database connection file 
		include("../../db-connector/db_connection.php");

		// get values 
		$user_name = $_POST['user_name'];
		$user_password = $_POST['user_password'];

		$query = "INSERT INTO users(user_id, user_name, user_password) VALUES(NULL,'$user_name', '$user_password')";
		if (!$result = mysql_query($query)) {
	        exit(mysql_error());
	    }
	    echo "1 Record Added!";
	}
?>