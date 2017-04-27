'use strict';

angular.module('UserModule')
    .service('UserService',['$resource',UserService]);

function UserService($resource) {
    return $resource(
        'http://localhost:8080/api/user/:id',
        {id: '@id'},//Handy for update & delete. id will be set with id of instance
        {
            update: {
                method: 'PUT' // To send the HTTP Put request when calling this custom update method.
            }

        }
    );
}
