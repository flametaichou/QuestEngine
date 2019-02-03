'use strict';

angular.module('QuestEngineApp').controller('ControllerDashboard', [
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

    $scope.addNewQuest = function () {
        var modalInstance = $uibModal.open({
            templateUrl: 'templates/modal/formQuest',
            controller: function ($scope, $uibModalInstance, $http) {

                $scope.quest = {};
                $scope.error = '';

                $scope.submit = function () {
                    $scope.requesting = true;
                    $http.post('data/submitQuest', $scope.quest).success(
                        function (response) {
                            $scope.requesting = false;
                            $uibModalInstance.close();
                        }).error(function (error) {
                        $scope.requesting = false;
                        $scope.error = 'Ошибка сохранения: ' + error.message;
                    });
                };

                $scope.close = function () {
                    $uibModalInstance.dismiss();
                };

            },
            size: 'lg',
            windowClass: 'wide-modal-dialog',
            backdrop: false
        });
        modalInstance.result.then(function () {
            $scope.loadQuests();
        });
    };

    $scope.deleteQuest = function (key) {
        $http.post('admin/data/quest/delete', null, {
            params: {key: key}
        }).success(
            function (response) {
                $scope.loadQuests();
            }).error(function (error) {
            alert("Ошибка удаления лицензии!")
        });
    };

}]);
