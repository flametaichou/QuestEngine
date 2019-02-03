'use strict';

(function () {
    var app = angular.module('QuestEngineApp', [
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
        $routeSegmentProvider.when('/pages/main', 'main')
            .segment('main', {templateUrl: 'templates/main',
                resolveFailed: {
                    templateUrl: '403'
                }
            });
        $routeSegmentProvider.when('/pages/dashboard', 'dashboard')
            .segment('dashboard', {templateUrl: 'templates/dashboard',
                resolveFailed: {
                    templateUrl: '403'
                }
            });
        $routeSegmentProvider.when('/pages/admin/accounts', 'accounts')
            .segment('accounts', {templateUrl: 'templates/admin/accounts',
                resolveFailed: {
                    templateUrl: '403'
                }
            });
        $routeSegmentProvider.when('/pages/quest/:id/editor', 'questEditor')
            .segment('questEditor', {templateUrl: 'templates/questEditor',
                resolveFailed: {
                    templateUrl: '403'
                }
            });

        $routeSegmentProvider.when('/404', '404')
            .segment('404', {templateUrl: 'templates/404', statusCode: 404});

        $routeProvider.otherwise({redirectTo: '/404'});
    });

    app.directive('fileUpload', function () {
        return {
            scope: true,
            link: function (scope, element, attributes) {
                element.bind('change', function (event) {
                    var files = event.target.files;
                    for (var i = 0; i < files.length; i++) {
                        scope.$emit('fileSelected', {file: files[i]});
                    }
                });
            }
        }
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
                $http.get('data/questFile/' + $scope.imageCode).then(
                    function (response) {
                        $scope.imageForm.image = response.data;
                        $scope.imageForm.error = undefined;
                    },
                    function (error) {
                        $scope.imageForm.error = error;
                    });
            }
        }
    });

})();