(function() {
    angular.module("UserModule").controller('UserController', ['$scope','$mdDialog', '$mdToast', 'UserService',function ($scope,$mdDialog, $mdToast, UserService) {

        $scope.selected = [];
        $scope.emptyUser = {};
        $scope.users = {
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
        $scope.fetchAllUsers = function () {
            $scope.users.data = UserService.query(function () {
                $scope.users.count = $scope.users.data.length;
                $scope.confirmToast("User Loaded");
            },function () {
                $scope.confirmToast("User Loading Failed");
            });

        };

        $scope.createUser = function (newUser) {
            UserService.save(newUser,function (user, responseHeaders, status, statusText) {
                    angular.copy(newUser, user);
                    user.id = responseHeaders("userID");
                    $scope.users.data.push(user);
                    $scope.users.count = $scope.users.count + 1;
                    $scope.confirmToast("User Added Successfully!");
                    angular.copy($scope.emptyUser, $scope.newUser);
                },
                function (httpResponse) {
                    $scope.confirmToast("User Adding Failed, cause:");
                    angular.copy($scope.emptyUser, $scope.newUser);
                });
            $mdDialog.hide();
        };

        $scope.deleteUser = function (ev, index, user) {

            var confirm = $mdDialog.confirm()
                .title('Would you like to delete User ' + user.name)
                .targetEvent(ev)
                .ok('Delete')
                .cancel('Cancel');
            $mdDialog.show(confirm).then(function () {
                UserService.delete(user,
                    function () {
                        for (var i = 0; i < $scope.users.data.length; i++) {
                            if ($scope.users.data[i].name == user.name) {
                                $scope.users.data.splice(i, 1);
                                $scope.users.count = $scope.users.count - 1;
                                $scope.confirmToast("User Deleted!");
                                break;
                            }
                        }
                    },
                    function () {
                        $scope.confirmToast("User Deletion Failed!");
                    }
                );

            }, function () {
                // cancel the operation
            });
        };

        $scope.updateUser = function (item) {
            UserService.update(item, function (user, responseHeaders, status, statusText) {
                for (var i = 0; i < $scope.users.data.length; i++) {
                    if ($scope.users.data[i].id == item.id) {
                        angular.copy(item,$scope.users.data[i]);
                        $mdDialog.hide();
                        $scope.confirmToast("User Updated!");
                        break;
                    }
                }
            },function (httpResponse) {
                $scope.confirmToast("User Updation Failed!");
            });
            $mdDialog.hide();
        };

        $scope.fetchAllUsers();


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
        $scope.openAddUserDialog = function ($event) {

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'add-user-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

        $scope.hideDialog = function () {
            $mdDialog.hide();
        };

        $scope.openUpdateUserDialog = function ($event, $index, user) {
            angular.copy(user,$scope.row);

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'edit-user-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

    }]);
})();