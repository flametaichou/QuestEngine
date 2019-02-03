'use strict';

angular.module('QuestEngineApp')
    .controller('ControllerAccounts', [
        '$scope',
        "$http",
        function (
            $scope,
            $http
        ) {

            $scope.accounts = {};
            $scope.loadUsers = function () {

                $http.get('admin/data/accounts').then(
                    function (response) {
                        $scope.accounts = response.data;
                    },
                    function (error) {
                        alert("Ошибка при запросе списка пользователей!");
                    });
            };
            $scope.loadUsers();

            $scope.addNewUser = function () {
                $scope.creatingUser = true;
            };

            $scope.cancel = function () {
                $scope.error = "";
                $scope.creatingUser = false;
                $scope.edit = false;
                $scope.newUser = {};
                $scope.newUser.accountRole = {};
            };
            $scope.cancel();

            $scope.submitUser = function () {
                if (!$scope.edit) {
                    if (!$scope.newUser.username || !$scope.newUser.password) {
                        $scope.error = "Имя или пароль не заполнены!";
                        return;
                    }
                    if ($scope.newUser.password !== $scope.newUser.confirmPassword) {
                        $scope.error = "Пароли не совпадают!";
                        return;
                    }
                }

                $http.post('admin/data/submitUser', null, {
                    params: { id: $scope.newUser.id,
                              username: $scope.newUser.username,
                              password: $scope.newUser.password,
                              role: $scope.newUser.accountRole.role,
                              }}).success(
                    function (response) {
                        $scope.creatingUser = false;
                        $scope.edit = true;
                        $scope.loadUsers();
                    }).error(function (error) {
                        alert("Ошибка сохранения аккаунта!")
                    });
            };


            $scope.roles = {};
            $scope.loadRoles = function () {

                $http.get('admin/data/roles').then(
                    function (response) {
                        $scope.roles = response.data;
                    },
                    function (error) {
                        alert("Ошибка при запросе списка ролей!");
                    });
            };
            $scope.loadRoles();

            $scope.deleteUser = function (username) {
                $http.post('admin/data/account/delete', null, {
                    params: { username: username}}).success(
                    function (response) {
                        $scope.loadUsers();
                    }).error(function (error) {
                        alert("Ошибка удаления аккаунта!")
                    });
            };

            $scope.editUser = function (user) {
                $scope.creatingUser = true;
                $scope.edit = true;
                $scope.newUser = Object.assign({}, user);
            };

        }]);