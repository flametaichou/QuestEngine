'use strict';

(function () {
    var app = angular.module('QuestApp', [
        'ngRoute',
        'route-segment',
        'view-segment',
        'ui.bootstrap'
    ]);

    app.config(function ($routeSegmentProvider, $routeProvider) {
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
        $routeSegmentProvider.when('/404', '404')
            .segment('404', {templateUrl: 'templates/404', statusCode: 404});

        $routeSegmentProvider.when('/pages/quest/:id/editor', 'questEditor')
            .segment('questEditor', {templateUrl: 'templates/questEditor'});

        $routeProvider.otherwise({redirectTo: '/404'});
    });

})();