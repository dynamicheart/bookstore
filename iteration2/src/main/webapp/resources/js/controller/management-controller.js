/**
 * Created by dynamicheart on 4/20/2017.
 */
'use strict';

ManagementApp.controller('UserController', ['$scope', 'User', function($scope, User) {
    var self = this;
    self.user= new User();

    self.users=[];

    self.fetchAllUsers = function(){
        self.users = User.query();
    };

    self.createUser = function(){
        self.user.$save(function(){
            self.fetchAllUsers();
        });
    };

    self.updateUser = function(){
        self.user.$update(function(){
            self.fetchAllUsers();
        });
    };

    self.deleteUser = function(identity){
        var user = User.get({id:identity}, function() {
            user.$delete(function(){
                console.log('Deleting user with id ', identity);
                self.fetchAllUsers();
            });
        });
    };

    self.fetchAllUsers();

    self.submit = function() {
        if(self.user.id==null){
            console.log('Saving New User', self.user);
            self.createUser();
        }else{
            console.log('Upddating user with id ', self.user.id);
            self.updateUser();
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//If it is the one shown on screen, reset screen
            self.reset();
        }
        self.deleteUser(id);
    };
}]);

ManagementApp.controller('ModalController', ['$scope', function ($scope, $uibModalInstance) {
    var self = this;

    self.save = function () {
        $uibModalInstance.dismiss('cancel');
    }

    self.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    }
}]);