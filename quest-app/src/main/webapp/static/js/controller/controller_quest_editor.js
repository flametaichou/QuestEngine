'use strict';

angular.module('QuestEngineApp').controller('ControllerQuestEditor', [
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
        $scope.staticQuest = {};
        $scope.loadQuest = function () {
            $http.get('data/loadQuestByRef', {params: {questRef: $scope.questRef}}).then(
                function (response) {
                    $scope.quest = response.data;
                    $scope.staticQuest.name = $scope.quest.name;
                    $scope.staticQuest.description = $scope.quest.description;
                    if (!$scope.quest.firstScene) {
                        $scope.quest.firstScene = {};
                    }
                    $scope.loadScenes();
                },
                function (error) {
                    alert("Ошибка при запросе квеста на редактирование: " + error.message);
                });
        };
        $scope.loadQuest();

        $scope.section = 1;
        $scope.setSection = function (section) {
            $scope.section = section;
            if (section == 2) {
                $scope.loadScenes();
            }
            if (section == 3) {
                $scope.loadFiles();
            }
            $scope.error = '';
        };

        $scope.error = '';

        $scope.submitQuest = function () {
            $scope.requesting = true;
            $http.post('data/submitQuest', $scope.quest)
                .success(
                    function (response) {
                        $scope.requesting = false;
                        $scope.error = '';
                        $scope.questRef = $scope.quest.uniqueCode;
                        $scope.loadQuest();
                    })
                .error(
                    function (error) {
                        $scope.requesting = false;
                        $scope.error = 'Ошибка сохранения: ' + error.message;
                    });
        };

        $scope.scenes = [];
        $scope.loadScenes = function () {
            $http.get('data/scenes', {params: {questId: $scope.quest.id}}).then(
                function (response) {
                    $scope.scenes = response.data;
                },
                function (error) {
                    $scope.error = "Ошибка при запросе сцен: " + error.message;
                });
        };

        $scope.sceneTypes = [];
        $scope.loadSceneTypes = function () {
            $http.get('data/sceneTypes', null).then(
                function (response) {
                    $scope.sceneTypes = response.data;
                },
                function (error) {
                    alert("Ошибка при запросе типов сцен: " + error.message);
                });
        };
        $scope.loadSceneTypes();

        $scope.scene = undefined;
        $scope.openScene = function (scene) {
            $scope.scene = scene;
            $scope.option = undefined;
        };
        $scope.newScene = function () {
            $scope.scene = {}
        };
        $scope.submitScene = function () {
            $scope.requesting = true;
            $scope.scene.quest = $scope.quest;
            $http.post('data/submitScene', $scope.scene)
                .success(
                    function (response) {
                        $scope.requesting = false;
                        $scope.error = '';
                        $scope.scene = undefined;
                        $scope.loadScenes();
                    })
                .error(
                    function (error) {
                        $scope.requesting = false;
                        $scope.error = 'Ошибка сохранения: ' + error.message;
                    });
        };
        $scope.showAddOptionButton = function (scene) {
            if (scene.type) {
                if (scene.type === 'DIALOG') {
                    return !scene.options || scene.options.length < 11;
                } else if (scene.type === 'PICTURE') {
                    return !scene.options || scene.options.length < 0;
                }
            }
            return false;
        };

        $scope.option = undefined;
        $scope.openOption = function (option) {
            $scope.option = option;
            var modalInstance = $uibModal.open({
                templateUrl: 'templates/modal/formOption',
                controller: function ($scope, $uibModalInstance, $http, option, scene, scenes) {

                    $scope.scene = scene;
                    $scope.scenes = scenes;
                    $scope.option = option;
                    $scope.error = '';

                    $scope.submit = function () {
                        $scope.requesting = true;
                        $scope.option.scene = $scope.scene;
                        $scope.option.targetScene = {id: $scope.option.targetSceneId};
                        $http.post('data/submitOption', $scope.option)
                            .success(
                                function (response) {
                                    $scope.requesting = false;
                                    $uibModalInstance.close();
                                })
                            .error(
                                function (error) {
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
                backdrop: false,
                resolve: {
                    option: function () {
                        return option;
                    },
                    scene: function () {
                        return $scope.scene;
                    },
                    scenes: function () {
                        return $scope.scenes;
                    }
                }
            });
            modalInstance.result.then(function () {
                $scope.loadScenes();
            });
        };

        $scope.files = [];
        $scope.loadFiles = function () {
            $http.get('data/questFiles', {params: {questId: $scope.quest.id}}).then(
                function (response) {
                    $scope.files = response.data;
                },
                function (error) {
                    $scope.error = "Ошибка при запросе файлов: " + error.message;
                });
        };

        $scope.uploadPicture = function () {
            $scope.uploadFile('data/quest/' + $scope.quest.id + '/questFile/save');
        };

        $scope.uploadFile = function (uploadUrl) {
            var modalInstance = $uibModal.open({
                templateUrl: 'templates/modal/formUpload',
                controller: function ($scope, $uibModalInstance, $http) {

                    $scope.request = {
                        file: undefined
                    };

                    $scope.$on('fileSelected', function (event, args) {
                        $scope.$apply(function () {
                            $scope.request.file = args.file;
                        });
                    });

                    $scope.submit = function () {
                        $http({
                            method: 'POST',
                            url: uploadUrl,
                            headers: {'Content-Type': undefined},
                            transformRequest: function (data) {
                                var formData = new FormData();
                                formData.append('file', data.file);
                                return formData;
                            },
                            data: {
                                file: $scope.request.file
                            }

                        }).success(function () {
                            $uibModalInstance.close();
                            alert('Успешно загружено');
                        }).error(function (e) {
                            alert('Ошибка' + e.message ? e.message : e.data.message);
                        });
                    };

                    $scope.close = function () {
                        $uibModalInstance.dismiss();
                    };
                },
                size: 'lg',
                backdrop: false
            });
            modalInstance.result.then(function () {
                $scope.loadFiles();
            });
        };

        $scope.selectPortraitImage = function () {
            $scope.selectFile().result.then(function (file) {
                $scope.scene.portraitFile = file;
            });
        };

        $scope.selectBackgroundImage = function () {
            $scope.selectFile().result.then(function (file) {
                $scope.scene.backgroundFile = file;
            })
        };

        $scope.selectFile = function () {
            var modalInstance = $uibModal.open({
                templateUrl: 'templates/modal/formImages',
                controller: function ($scope, $uibModalInstance, $http, quest) {

                    $scope.quest = quest;
                    $scope.files = [];
                    $scope.loadFiles = function () {
                        $http.get('data/questFiles', {params: {questId: $scope.quest.id}}).then(
                            function (response) {
                                $scope.files = response.data;
                            },
                            function (error) {
                                $scope.error = "Ошибка при запросе файлов: " + error.message;
                            });
                    };
                    $scope.loadFiles();

                    $scope.select = function (file) {
                        $scope.selectedFile = file;
                    };

                    $scope.submit = function () {
                        $uibModalInstance.close($scope.selectedFile);
                    };

                    $scope.close = function () {
                        $uibModalInstance.dismiss();
                    };
                },
                size: 'lg',
                backdrop: false,
                resolve: {
                    quest: function () {
                        return $scope.quest;
                    }
                }
            });
            return modalInstance;
        };

    }]);
