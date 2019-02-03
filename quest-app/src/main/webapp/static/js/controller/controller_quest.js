'use strict';

angular.module('QuestViewerApp').controller('ControllerQuest', [
    '$scope',
    '$http',
    '$uibModal',
    '$routeParams',
    function (
        $scope,
        $http,
        $uibModal,
        $routeParams
    ) {
        $scope.questRef = $routeParams.id;

        $scope.quest = {};
        $scope.loadQuest = function () {
            $http.get('data/loadQuest', {params: {questRef: $scope.questRef}}).then(
                function (response) {
                    $scope.quest = response.data;
                },
                function (error) {
                    alert("Ошибка при запросе квеста: " + error.message);
                });
        };
        $scope.loadQuest();

        $scope.scene = undefined;
        $scope.startQuest = function () {
            $scope.scene = $scope.quest.firstScene;
        };

        $scope.openScene = function (sceneId) {
            $http.get('data/loadScene', {params: {sceneId: sceneId}}).then(
                function (response) {
                    $scope.scene = response.data;
                },
                function (error) {
                    alert("Ошибка при запросе сцены: " + error.message);
                });
        };

        // Проверяем, как повернут экран (портретная\альбомная ориентация)
        $scope.heightIsBiggerThanWidth = function () {
            var pageWidth = window.innerWidth;
            var pageHeight = window.innerHeight;
            if (pageHeight > pageWidth) {
                return true;
            } else {
                return false;
            }
        };

        $scope.getButtonStyle = function () {
            if ($scope.heightIsBiggerThanWidth()) {
                return {'width' : '90%', 'margin-left' : 'auto', 'margin-right' : 'auto', 'margin-bottom' : '15px'};
            } else {
                return {'width' : '30%', 'margin-left' : 'auto', 'margin-right' : 'auto', 'margin-bottom' : '15px'};
            }
        };
}]);