function loadBooks(){
	$.get("ajax/loadBooks.php",{}, function(data,status){
		$("#book_cards").html(data);
	});
}

$(document).ready(function (){
	loadBooks();
});