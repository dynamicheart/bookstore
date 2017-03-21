<?php
// include Database connection file
include("../../db-connector/db_connection.php");

// check request
if(isset($_POST['book_id']) && isset($_POST['book_id']) != "")
{
    // get book ID
    $book_id = $_POST['book_id'];

    // Get User Details
    $query = "SELECT * FROM books WHERE book_id = '$book_id'";
    if (!$result = mysql_query($query)) {
        exit(mysql_error());
    }
    if(mysql_num_rows($result) > 0){
        $row = mysql_fetch_assoc($result);
        $data = '
                <div class="row">
                <div class="col-xs-4 item-photo">
                    <img class="img-responsive detail-book-photo" src="'.$row['book_cover'].'" alt="Chania" />
                </div>
                <div class="col-xs-8">
                    <div class="detail-book-name">'.$row['book_name'].'</div>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="detail-book-author">'.$row['book_author'].'</div>
                        </div>
                        <div class="col-xs-4">
                            <div class="detail-book-card-rate">
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star"></span>
                                <span class="glyphicon glyphicon-star-empty"></span>
                                </div>
                            </div>
                        </div>
                    <div class="detail-book-price">$'.$row['book_price'].'</div>
                    <hr>
                    <div class="section detail-book-section pull-right">
                        <button class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart book-detail-addcart" aria-hidden="true"></span>&nbsp;Add to Cart</button>
                    </div>
                </div>
                </div>
                <hr>
                <div class=row>
                <h3 class="detail-description-header">Book Description</h3>
                <pclass="detail-description-content>'.$row['book_description'].'</p>
                </div>';
    }
    else
    {
        // records now found
        $data .= '<p>Records not found!</p>';
    }

    echo $data;

}
else
{
    $response['status'] = 200;
    $response['message'] = "Invalid Request!";
    $data = "Here";
}
