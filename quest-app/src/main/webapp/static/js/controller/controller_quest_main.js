'use strict';

angular.module('QuestViewerApp').controller('ControllerQuestMain', [
    '$scope',
    '$http',
    '$uibModal',
function (
    $scope,
    $http,
    $uibModal
) {
    $scope.quests = {};

    $scope.loadQuests = function () {
        $http.get('data/quests').then(
            function (response) {
                $scope.quests = response.data;
            },
            function (error) {
                alert("Ошибка при запросе списка квестов!");
            });
    };
    $scope.loadQuests();

}]);
