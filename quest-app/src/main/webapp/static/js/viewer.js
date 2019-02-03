'use strict';

(function () {
    var app = angular.module('QuestViewerApp', [
        'ngRoute',
        'route-segment',
        'view-segment',
        'ui.bootstrap'
    ]);

    app.config(function ($routeSegmentProvider, $routeProvider) {

        $routeSegmentProvider.when('/', 'index')
            .segment('index', {templateUrl: 'templates/main',
                resolveFailed: {
                    templateUrl: '403'
                }
            });
        $routeSegmentProvider.when('/quest/:id', 'quest')
            .segment('quest', {templateUrl: 'templates/quest',
                resolveFailed: {
                    templateUrl: '403'
                }
            });

        $routeSegmentProvider.when('/404', '404')
            .segment('404', {templateUrl: 'templates/404', statusCode: 404});

        $routeProvider.otherwise({redirectTo: '/404'});
    });

    app.directive('questImage', function () {
        return {
            scope: {
                imageCode: '=code'
            },
            templateUrl: 'templates/directive/image',
            link: function (scope, element, attributes) {},
            controller: function ($scope, $http) {
                $scope.imageForm = {};

                $scope.$watch('imageCode', function (newValue, oldValue) {
                    $scope.loadImage();
                });

                $scope.loadImage = function () {
                    $scope.imageForm.error = undefined;
                    $http.get('data/questFile/' + $scope.imageCode).then(
                        function (response) {
                            $scope.imageForm.image = response.data;
                        },
                        function (error) {
                            $scope.imageForm.error = error;
                        });
                };
                $scope.loadImage();
            }
        }
    });

})();