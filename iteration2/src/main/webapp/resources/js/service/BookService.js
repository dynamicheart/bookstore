'use strict';

angular.module('BookModule')
    .service('BookService',['$resource',BookService]);

function BookService($resource) {
    return $resource(
        'http://localhost:8080/api/book/:id',
        {id: '@id'},//Handy for update & delete. id will be set with id of instance
        {
            update: {
                method: 'PUT' // To send the HTTP Put request when calling this custom update method.
            }

        }
    );
}