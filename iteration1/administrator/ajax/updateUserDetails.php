<?php
// include Database connection file
include("../../db-connector/db_connection.php");

// check request
if(isset($_POST))
{
    // get values
    $id = $_POST['id'];
    $user_name = $_POST['user_name'];
    $user_password = $_POST['user_password'];

    // Updaste User details
    $query = "UPDATE users SET user_name = '$user_name', user_password = '$user_password' WHERE user_id = '$id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
}
