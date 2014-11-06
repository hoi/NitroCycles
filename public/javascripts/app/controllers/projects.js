angular.module('NitroCyclesNg').controller('listProjectsController', ["$scope", "$resource", function ($scope, $resource) {

    var Projects = $resource("projects");
    $scope.projectsList = Projects.query();

    $scope.$on("projectsUpdated",function(event,args) {
        $scope.projectsList = Projects.query();
    });

}]);

angular.module('NitroCyclesNg').controller('viewProjectsController', ["$scope", "$route", "$routeParams", "$resource", "$http", "$location", "$rootScope", "constants", function ($scope, $route, $routeParams, $resource, $http, $location, $rootScope, constants) {

    var Project = $resource("projects/:id", {id:$routeParams.id});
    $scope.projectDetail = Project.get();

    var ProjectParts = $resource("projects/:id/project_parts", {id:$routeParams.id});
    $scope.projectPartsList = ProjectParts.query();

    $scope.project_part_states = constants.project_part_states;

    $scope.goToAddPart = function () {
        $location.path("/project/" + $routeParams.id + "/add");
    }

    $scope.goToEditProject = function () {
        $location.path("/project/" + $routeParams.id + "/edit");
    }

    $scope.deleteProject = function () {
        Project.delete();
        $rootScope.$broadcast('projectsUpdated');
        $location.path("/");
    }

    $scope.deletePart = function (id) {
        var ProjectPart = $resource("project_parts/:id", {id:id});
        ProjectPart.delete();
        $scope.projectPartsList = ProjectParts.query();
        $route.reload();
    }

    $scope.changeState = function (id, part_id) {
        $http({
            method: "put",
            url: "project_parts/" + id,
            data: {
                state: $("#project_part_state_" + id).val(),
                part: {id: part_id},
                project: {id: $routeParams.id}
            }
        }).success(
            function(response) {
                $route.reload();
            }
        );
    }

}]);

angular.module('NitroCyclesNg').controller('addProjectsController', ["$scope", "$http", "$location", "$rootScope", function ($scope, $http, $location, $rootScope) {

    $scope.newProject = {};
    $scope.newProject.name = "";

    $scope.submitProject = function () {
        $http({
            method: "post",
            url: "projects",
            data: {
                name: $scope.newProject.name
            }
        }).success(
            function(response) {
                $rootScope.$broadcast('projectsUpdated');
                $location.path("/project/" + response.id);
            }
        );
    }

    $scope.cancelProject = function () {
        $location.path("/");
    }

}]);

angular.module('NitroCyclesNg').controller('editProjectsController', ["$scope", "$routeParams", "$resource", "$http", "$location", "$rootScope", function ($scope, $routeParams, $resource, $http, $location, $rootScope) {

    var Project = $resource("projects/:id", {id:$routeParams.id});
    $scope.projectDetail = Project.get();

    $scope.editedProject = {};
    $scope.editedProject.name = $scope.projectDetail.name;

    $scope.submitProject = function () {
        $http({
            method: "put",
            url: "projects/" + $routeParams.id,
            data: {
                name: $scope.editedProject.name
            }
        }).success(
            function(response) {
                $rootScope.$broadcast('projectsUpdated');
                $location.path("/project/" + $routeParams.id);
            }
        );
    }

    $scope.cancelProject = function () {
        $location.path("/project/" + $routeParams.id);
    }

}]);
