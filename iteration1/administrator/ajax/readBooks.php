<?php
	// include Database connection file 
	include("../../db-connector/db_connection.php");

	// Design initial table header 
	$data = '<table class="table table-bordered table-striped">
						<tr>
							<th>No.</th>
							<th>Name</th>
							<th>Author</th>
							<th>Price</th>
							<th>Stocks</th>
							<th>Rate</th>
							<th>Update</th>
							<th>Delete</th>
						</tr>';

	$query = "SELECT * FROM books";

	if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }

    // if query results contains rows then featch those rows 
    if(mysql_num_rows($result) > 0)
    {
    	$number = 1;
    	while($row = mysql_fetch_assoc($result))
    	{
    		$data .= '<tr>
				<td>'.$number.'</td>
				<td>'.$row['book_name'].'</td>
				<td>'.$row['book_author'].'</td>
				<td>'.$row['book_price'].'</td>
				<td>'.$row['book_stocks'].'</td>
				<td>'.$row['book_rate'].'</td>
				<td>
					<button onclick="GetBookDetails('.$row['book_id'].')" class="btn btn-warning">Update</button>
				</td>
				<td>
					<button onclick="DeleteBook('.$row['book_id'].')" class="btn btn-danger">Delete</button>
				</td>
    		</tr>';
    		$number++;
    	}
    }
    else
    {
    	// records now found
    	$data .= '<tr><td colspan="7">Records not found!</td></tr>';
    }

    $data .= '</table>';

    echo $data;
?>