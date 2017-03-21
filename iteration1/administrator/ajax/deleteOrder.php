<?php
// check request
if(isset($_POST['id']) && isset($_POST['id']) != "")
{
    // include Database connection file
    include("../../db-connector/db_connection.php");

    // get user id
    $order_id = $_POST['id'];

    // delete User
    $query = "DELETE FROM orders WHERE order_id = '$order_id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
}
?>