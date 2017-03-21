<?php
	// include Database connection file
	include("../../db-connector/db_connection.php");

	// Design initial table header
	$data = '<table class="table table-bordered table-striped">
						<tr>
							<th>No.</th>
							<th>Book ID</th>
							<th>Book Name</th>
							<th>User ID</th>
							<th>User Name</th>
							<th>Order Status</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>';

	$query = "SELECT * FROM orders";

	if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }

    // if query results contains rows then featch those rows
    if(mysql_num_rows($result) > 0)
    {
    	$number = 1;
    	while($row = mysql_fetch_assoc($result))
    	{
    		$book_id = $row['book_id'];
    		$user_id = $row['user_id'];
    		$queryBookName = "SELECT book_name FROM books WHERE book_id = '$book_id'";
    		$queryUserName = "SELECT user_name FROM users WHERE user_id = '$user_id'";
    		if (!($queryBooksResult = mysql_query($queryBookName)) || !($queryUsersResult = mysql_query($queryUserName))) {
    			exit(mysql_error());
    		}
    		$bookNameRow = mysql_fetch_assoc($queryBooksResult);
    		$UserNameRow = mysql_fetch_assoc($queryUsersResult);
    		$data .= '<tr>
				<td>'.$number.'</td>
				<td>'.$row['book_id'].'</td>
				<td>'.$bookNameRow['book_name'].'</td>
				<td>'.$row['user_id'].'</td>
				<td>'.$UserNameRow['user_name'].'</td>
				<td>'.$row['order_status'].'</td>
				<td>
					<button onclick="GetOrderDetails('.$row['order_id'].')" class="btn btn-warning">Update</button>
				</td>
				<td>
					<button onclick="DeleteOrder('.$row['order_id'].')" class="btn btn-danger">Delete</button>
				</td>
    		</tr>';
    		$number++;
    	}
    }
    else
    {
    	// records now found
    	$data .= '<tr><td colspan="8">Records not found!</td></tr>';
    }

    $data .= '</table>';

    echo $data;
?>