<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div ng-controller="ControllerQuestEditor">

    <div class="text-center">
        <h1 class="message-text-small">{{staticQuest.name}}</h1>
        <div class="message-tooltip">{{staticQuest.description}}</div>

        <div class="indent-xs"></div>

        <div class="btn-group" style="width: 60%;">
            <button ng-class="section == 1 ? 'btn btn-outline-light active' : 'btn btn-outline-light'"
                    ng-click="setSection(1)"
                    style="width: 30%;">
                <spring:message code="quest.editor.main"/>
            </button>
            <button ng-class="section == 2 ? 'btn btn-outline-light active' : 'btn btn-outline-light'"
                    ng-click="setSection(2)"
                    style="width: 40%;">
                <spring:message code="quest.editor.scenes"/>
            </button>
            <button ng-class="section == 3 ? 'btn btn-outline-light active' : 'btn btn-outline-light'"
                    ng-click="setSection(3)"
                    style="width: 30%;">
                <spring:message code="quest.editor.files"/>
            </button>
        </div>
    </div>

    <div class="indent-xs"></div>

    <div ng-if="section == 1">
        <form role="form">
            <div class="glass-window">
                <div class="glass-window-header text-center">
                    <spring:message code="quest.editor.main"/>
                    <button type="submit"
                            class="btn btn-sm btn-outline-light"
                            style="position: absolute; right: 0;"
                            ng-disabled=""
                            ng-click="submitQuest()">
                        <spring:message code="button.submit"/>
                    </button>
                </div>

                <div style="width: 100%; padding-top: 7px;" ng-if="error">
                    <label class="text-danger">{{error}}</label>
                </div>

                <div class="indent-xs"></div>

                <div class="row form-group">
                    <label class="col-lg-4">
                        <spring:message code="quest.name"/>
                    </label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control"
                               ng-model="quest.name"
                               ng-required="true">
                    </div>
                </div>

                <div class="row form-group">
                    <label class="col-lg-4">
                        <spring:message code="quest.uniqueCode"/>
                    </label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control"
                               ng-model="quest.uniqueCode">
                    </div>
                </div>

                <div class="row form-group">
                    <label class="col-lg-4">
                        <spring:message code="quest.description"/>
                    </label>
                    <div class="col-lg-8">
                        <textarea class="form-control" ng-model="quest.description"></textarea>
                    </div>
                </div>

                <div class="row form-group">
                    <label class="col-lg-4">
                        <spring:message code="quest.available"/>
                    </label>
                    <div class="col-lg-8">
                        <input type="checkbox" class="form-check-input" ng-model="quest.available">
                    </div>
                </div>

                <div class="row form-group">
                    <label class="col-lg-4">
                        <spring:message code="quest.firstScene"/>
                    </label>
                    <div class="col-lg-8">
                        <select class="form-control form-control-sm"
                                ng-model="quest.firstScene.id"
                                ng-options="sc.id as sc.name for sc in scenes"
                                ng-required="true">
                            <option value=""><spring:message code="common.select"/></option>
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <div ng-if="section == 2">
        <div class="row">
            <div class="col-lg-6">
                <div class="glass-window">
                    <div class="glass-window-header text-center">
                        <spring:message code="quest.editor.scenes"/>
                        <button type="submit"
                                class="btn btn-sm btn-outline-light"
                                style="position: absolute; right: 0;"
                                ng-disabled=""
                                ng-click="newScene()">
                            <spring:message code="button.add"/>
                        </button>
                    </div>

                    <div class="indent-xs"></div>

                    <div ng-repeat="sc in scenes"
                         ng-click="openScene(sc)"
                         ng-class="sc.id == scene.id ? 'glass-table-row selected' : 'glass-table-row'">
                        {{sc.name}}
                        <span class="fa fa-star"
                              ng-if="quest.firstScene && sc.id == quest.firstScene.id"
                              style="float: right; margin-top: 5px;"
                              title="<spring:message code="quest.firstScene"/>">
                        </span>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="glass-window" ng-if="scene">
                    <div class="glass-window-header text-center">
                        <spring:message code="scene"/>
                        <button type="submit"
                                class="btn btn-sm btn-outline-light"
                                style="position: absolute; right: 0;"
                                ng-disabled=""
                                ng-click="submitScene()">
                            <spring:message code="button.submit"/>
                        </button>
                    </div>

                    <div style="width: 100%; padding-top: 7px;" ng-if="error">
                        <label class="text-danger">{{error}}</label>
                    </div>

                    <div class="indent-xs"></div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.name"/>
                        </label>
                        <div class="col-lg-8">
                            <input type="text" class="form-control"
                                   ng-model="scene.name"
                                   ng-required="true">
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.text"/>
                        </label>
                        <div class="col-lg-8">
                            <textarea class="form-control" ng-model="scene.text"></textarea>
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.type"/>
                        </label>
                        <div class="col-lg-8">
                            <select ng-model="scene.type" class="form-control form-control-sm">
                                <option value=""><spring:message code="common.select"/></option>
                                <option value="{{sceneType.name}}" ng-repeat="sceneType in sceneTypes">{{sceneType.displayName}}</option>
                            </select>
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.portraitImage"/>
                        </label>
                        <div class="col-lg-8">
                            <div class="input-group mb-3">
                                <input type="text"
                                       class="form-control"
                                       ng-disabled="true"
                                       ng-model="scene.portraitFile.name">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-light" type="button" ng-click="selectPortraitImage()">Выберите</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.backgroundImage"/>
                        </label>
                        <div class="col-lg-8">
                            <div class="input-group mb-3">
                                <input type="text"
                                       class="form-control"
                                       ng-disabled="true"
                                       ng-model="scene.backgroundFile.name">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-light" type="button" ng-click="selectBackgroundImage()">Выберите</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-lg-4">
                            <spring:message code="scene.options"/>
                        </label>
                        <div class="col-lg-8">
                            <div>
                                <button class="btn btn-sm btn-light"
                                        ng-if="showAddOptionButton(scene)"
                                        ng-click="openOption()">
                                        +
                                </button>
                            </div>
                            <div class="glass-table-row"
                                 ng-repeat="opt in scene.options"
                                 ng-click="openOption(opt)"
                                 ng-class="opt.id == option.id ? 'glass-table-row selected' : 'glass-table-row'">
                                <span>{{opt.name}}</span>
                                <span style="float: right;">→ {{opt.targetScene.name}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div ng-if="section == 3">
        <div ng-if="quest.id" style="margin-left: 15px;">
            <button class="btn btn-default" ng-click="uploadPicture()">
                <spring:message code="button.add"/>
            </button>
        </div>

        <div ng-repeat="file in files" style="width: 200px; height: 200px; margin: 15px; display: inline-block;">
            <quest-image code="file.uniqueCode"></quest-image>
            <div class="text-center text-white">{{file.name}}</div>
        </div>
    </div>

</div>
