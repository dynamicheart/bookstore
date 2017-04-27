(function() {
    angular.module("OrderModule").controller('OrderController', ['$scope', '$mdDialog', '$mdToast', 'OrderService',function ($scope, $mdDialog, $mdToast, OrderService) {

        $scope.selected = [];
        $scope.emptyOrder = {};
        $scope.orders = {
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
        $scope.fetchAllOrders = function () {
            $scope.orders.data = OrderService.query(function () {
                $scope.orders.count = $scope.orders.data.length;
                $scope.confirmToast("Order Loaded");
            },function () {
                $scope.confirmToast("Order Loading Failed");
            });

        };

        $scope.createOrder = function (newOrder) {
            OrderService.save(newOrder,function (order, responseHeaders, status, statusText) {
                    $scope.orders.data.push(order);
                    $scope.orders.count = $scope.orders.count + 1;
                    $scope.confirmToast("Order Added Successfully!");
                    angular.copy($scope.emptyOrder, $scope.newOrder);
                },
                function (httpResponse) {
                    $scope.confirmToast("Order Adding Failed, cause:");
                    angular.copy($scope.emptyOrder, $scope.newOrder);
                });
            $mdDialog.hide();
        };

        $scope.deleteOrder = function (ev, index, order) {

            var confirm = $mdDialog.confirm()
                .title('Would you like to delete Order ' + order.name)
                .targetEvent(ev)
                .ok('Delete')
                .cancel('Cancel');
            $mdDialog.show(confirm).then(function () {
                OrderService.delete(order,
                    function () {
                        for (var i = 0; i < $scope.orders.data.length; i++) {
                            if ($scope.orders.data[i].name == order.name) {
                                $scope.orders.data.splice(i, 1);
                                $scope.orders.count = $scope.orders.count - 1;
                                $scope.confirmToast("Order Deleted!");
                                break;
                            }
                        }
                    },
                    function () {
                        $scope.confirmToast("Order Deletion Failed!");
                    }
                );

            }, function () {
                // cancel the operation
            });
        };

        $scope.updateOrder = function (item) {
            OrderService.update(item, function (order, responseHeaders, status, statusText) {
                for (var i = 0; i < $scope.orders.data.length; i++) {
                    if ($scope.orders.data[i].id == item.id) {
                        angular.copy(item,$scope.orders.data[i]);
                        $mdDialog.hide();
                        $scope.confirmToast("Order Updated!");
                        break;
                    }
                }
            },function (httpResponse) {
                $scope.confirmToast("Order Updation Failed!");
            });
            $mdDialog.hide();
        };

        $scope.fetchAllOrders();


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
        $scope.openAddOrderDialog = function ($event) {

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'add-order-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

        $scope.hideDialog = function () {
            $mdDialog.hide();
        };

        $scope.openUpdateOrderDialog = function ($event, $index, order) {

            angular.copy(order,$scope.row);

            $mdDialog.show({
                scope: $scope,
                preserveScope: true,
                templateUrl: 'edit-order-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

        $scope.openOrderItemDialog = function ($event, order) {

            angular.copy(order,$scope.row);

            $mdDialog.show({
                controller: OrderItemController,
                preserveScope: true,
                templateUrl: 'edit-order-dialog.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                clickOutsideToClose: true
            });
        };

    }]);
})();