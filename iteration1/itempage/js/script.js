function readBookDetails() {
    var book_id = getParameterByName("book_id");
    $.post("ajax/readBookDetails.php", {
            book_id: book_id
        },
        function(data, status) {
            $("#book_details").html(data);
        }
    );

}

function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

$(document).ready(function() {
    readBookDetails();
});
