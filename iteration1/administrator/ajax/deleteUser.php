<?php
// check request
if(isset($_POST['id']) && isset($_POST['id']) != "")
{
    // include Database connection file
    include("../../db-connector/db_connection.php");

    // get user id
    $user_id = $_POST['id'];

    // delete User
    $query = "DELETE FROM users WHERE user_id = '$user_id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
}
?>