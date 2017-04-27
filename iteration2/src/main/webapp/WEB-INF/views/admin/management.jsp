<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String ctx = request.getContextPath(); %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Bookstore Management System</title>
    <link rel="icon" href="<%=ctx%>/resources/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.1/angular-material.min.css">
    <link rel="stylesheet" href="<%=ctx%>/resources/css/management.css">
    <link rel="stylesheet" href="<%=ctx%>/resources/css/md-data-table.css">
</head>
<body ng-app="ManagementApp" ng-controller="ManagementController" layout="column" ng-cloak>
<md-toolbar layout="row">
    <md-button class="menu" aria-label="Menu Button" ng-click="toggleMenu();" >
        <md-icon md-svg-icon="<%=ctx%>/resources/svg/menu-bar.svg"></md-icon>
    </md-button>
    <h1>Online Bookstore Management System</h1>
</md-toolbar>
<div class="container" layout="row" flex>
    <md-sidenav class="md-whiteframe-4dp" md-component-id="left">
        <md-list ng-click="toggleMenu();">
            <md-list-item>
                <md-button href="#!welcome">
                    <md-icon md-svg-src="<%=ctx%>/resources/svg/avatar-admin.svg" class="avatar"></md-icon>
                    Welcome
                </md-button>
            </md-list-item>
            <md-list-item>
                <md-button href="#!datas">
                    <md-icon md-svg-src="<%=ctx%>/resources/svg/avatar-data.svg" class="avatar"></md-icon>
                    Data Management
                </md-button>
            </md-list-item>
        </md-list>
    </md-sidenav>
    <md-content id="content" flex>
        <div ng-view></div>
    </md-content>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-aria.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-resource.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.1.1/angular-material.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-resource.js"></script>
<script src="<%=ctx%>/resources/js/lib/md-data-table.js"></script>
<script src="<%=ctx%>/resources/js/module/UserModule.js"></script>
<script src="<%=ctx%>/resources/js/service/UserService.js"></script>
<script src="<%=ctx%>/resources/js/controller/UserController.js"></script>
<script src="<%=ctx%>/resources/js/module/BookModule.js"></script>
<script src="<%=ctx%>/resources/js/service/BookService.js"></script>
<script src="<%=ctx%>/resources/js/controller/BookController.js"></script>
<script src="<%=ctx%>/resources/js/module/OrderModule.js"></script>
<script src="<%=ctx%>/resources/js/service/OrderService.js"></script>
<script src="<%=ctx%>/resources/js/controller/OrderController.js"></script>

<script type="text/ng-template" id="datas.html">
    <md-tabs md-dynamic-height>
        <md-tab id="userTab">
            <md-tab-label>User</md-tab-label>
            <md-tab-body>
                <div ng-controller="UserController">
                    <md-card>
                        <md-data-table-container>
                            <table md-data-table class="md-primary"> <!-- md-progress="deferred" -->
                                <thead md-order="query.order" md-trigger="onorderchange">
                                <tr>
                                    <th name="#"></th>
                                    <th name="ID"></th>
                                    <th name="Name"></th>
                                    <th name='Password'></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="user in users.data| orderBy: query.id | limitTo: query.limit: (query.page - 1) * query.limit">
                                    <td>{{query.limit * (query.page - 1) + $index + 1}}</td>
                                    <td>{{user.id}}</td>
                                    <td>{{user.name}}</td>
                                    <td>{{user.password}}</td>
                                    <td>
                                        <div layout="row" layout-sm="column" layout-align="left left">

                                            <md-button class="md-raised md-primary"
                                                       ng-click="openUpdateUserDialog($event, $index, user);">Update
                                            </md-button>
                                            <md-button class="md-raised md-warn"
                                                       ng-click="deleteUser($event, $index, user);">Delete
                                            </md-button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </md-data-table-container>

                        <md-data-table-pagination md-limit="query.limit" md-page="query.page" md-total="{{users.count}}"/>

                    </md-card>

                    <md-button ng-click="openAddUserDialog($event);" class="md-raised md-primary">Create User</md-button>
                </div>
            </md-tab-body>
        </md-tab>
        <md-tab id="bookTab">
            <md-tab-label>Book</md-tab-label>
            <md-tab-body>
                <div ng-controller="BookController">
                    <md-card>
                        <md-data-table-container>
                            <table md-data-table class="md-primary"> <!-- md-progress="deferred" -->
                                <thead md-order="query.order" md-trigger="onorderchange">
                                <tr>
                                    <th name="#"></th>
                                    <th name="ID"></th>
                                    <th name="ISBN"></th>
                                    <th name="Name"></th>
                                    <th name='Author'></th>
                                    <th name="Publisher"></th>
                                    <th name="Price"></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="book in books.data| orderBy: query.id | limitTo: query.limit: (query.page - 1) * query.limit">
                                    <td>{{query.limit * (query.page - 1) + $index + 1}}</td>
                                    <td>{{book.id}}</td>
                                    <td>{{book.isbn}}</td>
                                    <td>{{book.name}}</td>
                                    <td>{{book.author}}</td>
                                    <td>{{book.publisher}}</td>
                                    <td>{{book.price | currency}}</td>
                                    <td>
                                        <div layout="row" layout-sm="column" layout-align="left left">

                                            <md-button class="md-raised md-primary"
                                                       ng-click="openUpdateBookDialog($event, $index, book);">Update
                                            </md-button>
                                            <md-button class="md-raised md-warn"
                                                       ng-click="deleteBook($event, $index, book);">Delete
                                            </md-button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </md-data-table-container>

                        <md-data-table-pagination md-limit="query.limit" md-page="query.page" md-total="{{books.count}}"/>

                    </md-card>

                    <md-button ng-click="openAddBookDialog($event);" class="md-raised md-primary">Create Book</md-button>
                </div>
            </md-tab-body>
        </md-tab>
        <md-tab id="orderTab">
            <md-tab-label>Order</md-tab-label>
            <md-tab-body>
                <div ng-controller="OrderController">
                    <md-card>
                        <md-data-table-container>
                            <table md-data-table class="md-primary"> <!-- md-progress="deferred" -->
                                <thead md-order="query.order" md-trigger="onorderchange">
                                <tr>
                                    <th name="#"></th>
                                    <th name="Order ID"></th>
                                    <th name="User ID"></th>
                                    <th name='Status'></th>
                                    <th name="Place Date"></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="order in orders.data| orderBy: query.id | limitTo: query.limit: (query.page - 1) * query.limit">
                                    <td>{{query.limit * (query.page - 1) + $index + 1}}</td>
                                    <td>{{order.id}}</td>
                                    <td>{{order.userId}}</td>
                                    <td>{{order.status}}</td>
                                    <td>{{order.placeTime}}</td>
                                    <td>
                                        <div layout="row" layout-sm="column" layout-align="left left">

                                            <md-button class="md-raised"
                                                       ng-click="openOrderItemDialog($event, order);">Items
                                            </md-button>
                                            <md-button class="md-raised md-primary"
                                                       ng-click="openUpdateOrderDialog($event, $index, order);">Update
                                            </md-button>
                                            <md-button class="md-raised md-warn"
                                                       ng-click="deleteOrder($event, $index, order);">Delete
                                            </md-button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </md-data-table-container>

                        <md-data-table-pagination md-limit="query.limit" md-page="query.page" md-total="{{orders.count}}"/>

                    </md-card>

                    <md-button ng-click="openAddOrderDialog($event);" class="md-raised md-primary">Create Order</md-button>
                </div>
            </md-tab-body>
        </md-tab>
    </md-tabs>
</script>
<script type="text/ng-template" id="add-user-dialog.html">
    <md-dialog aria-label="Add User" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Add A New User</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="newUserFrom">
                    <md-input-container>
                        <label>Name</label>
                        <input ng-model="newUser.name" value="{{newUser.name}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Password</label>
                        <input ng-model="newUser.password" value="{{newUser.password}}">
                    </md-input-container>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="row">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="createUser(newUser);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>
<script type="text/ng-template" id="edit-user-dialog.html">
    <md-dialog aria-label="Edit User" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Edit User</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="editUserFrom">
                    <md-input-container>
                        <label>Name</label>
                        <input ng-model="row.name" value="{{row.name}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Password</label>
                        <input ng-model="row.password" value="{{row.password}}">
                    </md-input-container>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="row">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="updateUser(row);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>
<script type="text/ng-template" id="add-book-dialog.html">
    <md-dialog aria-label="Add Book" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Add A New Book</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="newBookFrom">
                    <md-input-container>
                        <label>ISBN</label>
                        <input ng-model="newBook.isbn" value="{{newBook.isbn}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Name</label>
                        <input ng-model="newBook.name" value="{{newBook.name}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Author</label>
                        <input ng-model="newBook.author" value="{{newBook.author}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Publisher</label>
                        <input ng-model="newBook.publisher" value="{{newBook.publisher}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Price</label>
                        <input ng-model="newBook.price" value="{{newBook.price}}">
                    </md-input-container>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="row">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="createBook(newBook);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>
<script type="text/ng-template" id="edit-book-dialog.html">
    <md-dialog aria-label="Add Book" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Add A New Book</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="rowFrom">
                    <md-input-container>
                        <label>ISBN</label>
                        <input ng-model="row.isbn" value="{{row.isbn}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Name</label>
                        <input ng-model="row.name" value="{{row.name}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Author</label>
                        <input ng-model="row.author" value="{{row.author}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Publisher</label>
                        <input ng-model="row.publisher" value="{{row.publisher}}">
                    </md-input-container>
                    <md-input-container>
                        <label>Price</label>
                        <input ng-model="row.price" value="{{row.price}}">
                    </md-input-container>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="row">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="updateBook(row);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>
<script type="text/ng-template" id="add-order-dialog.html">
    <md-dialog aria-label="Add Order" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Add A New Order</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="newOrderFrom">
                    <md-input-container>
                        <label>User ID</label>
                        <input ng-model="newOrder.userId" value="{{newOrder.userId}}">
                    </md-input-container>
                    <md-select ng-model="newOrder.status" placeholder="Status" class="md-no-underline">
                        <md-option value="ORDERED">Ordered</md-option>
                        <md-option value="PROCEEDING">Proceeding</md-option>
                        <md-option value="COMPLETED">Completed</md-option>
                        <md-option value="CANCELED">Canceled</md-option>
                    </md-select>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="newOrder">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="createOrder(newOrder);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>
<script type="text/ng-template" id="edit-order-dialog.html">
    <md-dialog aria-label="Add Order" flex="30">
        <form ng-cloak>
            <md-toolbar>
                <div class="md-toolbar-tools">
                    <h2>Add A New Order</h2>
                    <span flex></span>
                    <md-button class="md-icon-button" ng-click="hideDialog();">
                        &times;
                    </md-button>
                </div>
            </md-toolbar>
            <md-dialog-content class="md-padding" layout="column">
                <form name="rowFrom">
                    <md-input-container>
                        <label>User ID</label>
                        <input ng-model="row.userId" value="{{row.userId}}">
                    </md-input-container>
                    <md-select ng-model="row.status" placeholder="Status" class="md-no-underline">
                        <md-option value="ORDERED">Ordered</md-option>
                        <md-option value="PROCEEDING">Proceeding</md-option>
                        <md-option value="COMPLETED">Completed</md-option>
                        <md-option value="CANCELED">Canceled</md-option>
                    </md-select>
                </form>
            </md-dialog-content>
            <md-dialog-actions layout="row">
                <md-button class="md-raised md-warn" ng-click="hideDialog();">Cancel</md-button>
                <md-button class="md-raised md-primary" ng-click="updateOrder(row);">Save</md-button>
            </md-dialog-actions>
        </form>
    </md-dialog>
</script>

<script>
    var managementApp = angular.module("ManagementApp", ['ngMaterial', 'md.data.table', 'ngRoute', 'UserModule', 'BookModule', 'OrderModule']);
    managementApp.config(function ($routeProvider,$mdIconProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'datas.html'
            })
            .when('/datas', {
                templateUrl: 'datas.html'
            });
    });

    managementApp.controller("ManagementController",function ($scope,$mdSidenav) {
        $scope.toggleMenu = function () {
            $mdSidenav('left').toggle();
        }
    });
</script>

</body>
</html>
