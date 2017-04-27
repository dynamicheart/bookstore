/**
 * Created by Colota on 4/23/2017.
 */
'use strict';

angular.module('OrderModule')
    .service('OrderService',['$resource',OrderService]);

function OrderService($resource) {
    return $resource(
        'http://localhost:8080/api/order/:id',
        {id: '@id'},//Handy for update & delete. id will be set with id of instance
        {
            update: {
                method: 'PUT' // To send the HTTP Put request when calling this custom update method.
            }

        }
    );
}