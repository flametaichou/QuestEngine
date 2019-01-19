'use strict';

angular.module('QuestApp').controller('ControllerQuestEditor', [
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
        $http.get('admin/data/loadQuestByRef', {params: {questRef: $scope.questRef}}).then(
            function (response) {
                $scope.quest = response.data;
            },
            function (error) {
                alert("Ошибка при запросе квеста на редактирование: " + error.message);
            });
    };
    $scope.loadQuest();

}]);
