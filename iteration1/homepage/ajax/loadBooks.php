<?php
	// include Database connection file 
	include("../../db-connector/db_connection.php");

	$query = "SELECT * FROM books";
	$data = '';

	if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }

    // if query results contains rows then featch those rows 
    if(mysql_num_rows($result) > 0)
    {
    	$number = 1;
    	while($row = mysql_fetch_assoc($result))
    	{
    		$data .= ' <div class="col-sm-3 col-lg-3 col-md-3">
                            <div class="thumbnail">
                                <img class="img-responsive" src="'.$row['book_cover'].'" alt="Chania">
                                <div class="caption">
                                    <p><a class="book-card-name" href="../itempage/item.html?book_id='.$row['book_id'].'">'.$row['book_name'].'</a></p>
                                    <p  class="book-card-author">'.$row['book_author'].'</p>
                                    <h4 class="book-card-price">$'.$row['book_price'].'</h4>
                                    <div class="ratings">
                                    	<div class="pull-right book-card-review">(12 reviews)</div>
                                    	<div class="book-card-rate">
                                        	<span class="glyphicon glyphicon-star"></span>
                                        	<span class="glyphicon glyphicon-star"></span>
                                        	<span class="glyphicon glyphicon-star"></span>
                                        	<span class="glyphicon glyphicon-star"></span>
                                        	<span class="glyphicon glyphicon-star-empty"></span>
                                    	</div>
                                	</div>
                                </div>
                            </div>
                        </div>';
    		$number++;
    	}
    }
    else
    {
    	// records now found 
    	$data .= '<p>Records not found!</p>';
    }

    echo $data;
?>
