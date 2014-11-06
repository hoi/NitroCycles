/** @namespace avgme **/
'use strict';
// Declare app level module which depends on filters, and services
angular.module('NitroCyclesNg', [
    'ngRoute', 'ngResource'
])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/', {templateUrl: 'assets/partials/welcome.html', controller: 'welcomeController'});
    $routeProvider.when('/project/new', {templateUrl: 'assets/partials/projects/new.html', controller: 'addProjectsController'});
    $routeProvider.when('/project/:id', {templateUrl: 'assets/partials/projects/view.html', controller: 'viewProjectsController'});
    $routeProvider.when('/project/:id/edit', {templateUrl: 'assets/partials/projects/edit.html', controller: 'editProjectsController'});
    $routeProvider.when('/project/:id/add', {templateUrl: 'assets/partials/parts/add.html', controller: 'addPartsController'});
    $routeProvider.otherwise({redirectTo: '/'});
}])
.value('constants', {'project_part_states' : ["new", "ordered", "arrived", "installed"]});
