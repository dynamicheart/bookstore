(function() {
    angular.module("BookModule").controller('BookController', ['$scope','$mdDialog', '$mdToast', 'BookService',function ($scope, $mdDialog, $mdToast, BookService) {

        $scope.selected = [];
        $scope.emptyBook = {};
        $scope.books = {
            "count":0,
            "data":[]
        };
        $scope.row = {};
        var last = {
            bottom: false,
            top: true,
            left: false,
            right: true
        };
        $scope.toastPosition = angular.extend({}, last);
        $scope.query = {
            order: 'id',
            limit: 5,
            page: 1
        };


        /* ngResource Service Wrapper */
        $scope.fetchAllBooks = function () {
            $scope.books.data = BookService.query(function () {
                $scope.books.count = $scope.books.data.length;
                $scope.confirmToast("Book Loaded");
            },function () {
                $scope.confirmToast("Book Loading Failed");
            });

        };

        $scope.createBook = function (newBook) {
            BookService.save(newBook,function (book, responseHeaders, status, statusText) {
                    angular.copy(newBook,book);
                    book.id = responseHeaders("bookID");
                    $scope.books.data.push(book);
                    $scope.books.count = $scope.books.count + 1;
                    $scope.confirmToast("Book Added Successfully!");
                    angular.copy($scope.emptyBook, $scope.newBook);
                },
                function (httpResponse) {
                    $scope.confirmToast("Book Adding Failed, cause:");
                    angular.copy($scope.emptyBook, $scope.newBook);
                });
            $mdDialog.hide();
        };

        $scope.deleteBook = function (ev, index, book) {

            var confirm = $mdDialog.confirm()
                .title('Would you like to delete Book ' + book.name)
                .targetEvent(ev)
                .ok('Delete')
                .cancel('Cancel');
            $mdDialog.show(confirm).then(function () {
                BookService.delete(book,
                    function () {
                        for (var i = 0; i < $scope.books.data.length; i++) {
                            if ($scope.books.data[i].name == book.name) {
                                $scope.books.data.splice(i, 1);
                                $scope.books.count = $scope.books.count - 1;
                                $scope.confirmToast("Book Deleted!");
                                break;
                            }
                        }
                    },
                    function () {
                        $scope.confirmToast("Book Deletion Failed!");
                    }
                );

            }, function () {
                // cancel the operation
            });
        };

        $scope.updateBook = function (item) {
            BookService.update(item, function (book, responseHeaders, status, statusText) {
                for (var i = 0; i < $scope.books.data.length; i++) {
                    if ($scope.books.data[i].id == item.id) {
                        angular.copy(item, $scope.books.data[i]);
                        $mdDialog.hide();
                        $scope.confirmToast("Book Updated!");
                        break;
                    }
                }
            },function (httpResponse) {
                $scope.confirmToast("Book Updation Failed!");
            });
            $mdDialog.hide();
        };

        $scope.fetchAllBooks();


        /* Toast */
        $scope.getToastPosition = function () {
            sanitizePosition();
            return Object.keys($scope.toastPosition)
                .filter(function (pos) {
                    return $scope.toastPosition[pos];
                })
                .join(' ');
        };
        function sanitizePosition() {
            var current = $scope.toastPosition;
            if (current.bottom && last.top)
                current.top = false;
            if (current.top && last.bottom)
                current.bottom = false;
            if (current.right && last.left)
                current.left = false;
            if (current.left && last.right)
                current.right = false;
            last = angular.extend({}, current);
        }

        $scope.confirmToast = function (content) {
            $mdToast.show(
                $mdToast.simple()
                    .content(content)
                    .position($scope.getToastPosition())
                    .hideDelay(3000)
            );
        };

        /* Dialog */
        $scope.openAddBookDialog = function ($event) {

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'add-book-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

        $scope.hideDialog = function () {
            $mdDialog.hide();
        };

        $scope.openUpdateBookDialog = function ($event, $index, book) {

            angular.copy(book,$scope.row);

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'edit-book-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

    }]);
})();