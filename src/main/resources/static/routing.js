var app = angular.module('app', ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'homepage.html'
        })
        .when('/shop', {
            templateUrl: 'shop.html',
            controller: 'shopController'
        })
        .when('/add_or_edit_product', {
            templateUrl: 'add_or_edit_product.html',
            controller: 'addOrEditProductController'
        })
});