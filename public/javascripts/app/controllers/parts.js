angular.module('NitroCyclesNg').controller('addPartsController', ["$scope", "$routeParams", "$resource", "$http", "$location", function ($scope, $routeParams, $resource, $http, $location) {

    var Parts = $resource("parts");
    $scope.partsList = Parts.query();

    $scope.selected = {};

    $scope.submitProject = function () {
        angular.forEach($scope.selected, function (is_selected, selected_id) {
            if (is_selected) {
                $http({
                    method: "post",
                    url: "project_parts",
                    data: {
                        state: "new",
                        part: {id: selected_id},
                        project: {id: $routeParams.id}
                    }
                }).success(
                    function(response) {
                        $location.path("/project/" + response.project.id);
                    }
                );
            }
        });
    }

}]);
