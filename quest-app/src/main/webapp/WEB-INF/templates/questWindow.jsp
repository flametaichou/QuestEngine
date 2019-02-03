<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div ng-controller="ControllerQuest">
    <div ng-if="!scene">
        <div style="height: 30%">
        </div>
        <div class="inner cover text-white text-center message-window" style="width: 40%;">
            <h1 class="message-text">{{quest.name}}</h1>
            <div class="message-tooltip" style="font-size: 1.3em;">{{quest.description}}</div>
            <div class="indent-xs"></div>
            <button class="btn btn-lg btn-outline-light"
                    ng-click="startQuest()">
                <spring:message code="button.start"/>
            </button>
        </div>
    </div>

    <div class="vertically-center-outer" style="position: absolute" ng-if="scene">
        <div style="height: 100vh; width: 100vw; position: absolute; z-index: -2;">
            <quest-image code="scene.backgroundFile.uniqueCode" style="z-index: -1;"></quest-image>
            <div style="background-color:rgba(0,0,0,0.7); width: 100%; height: 100%; z-index: 0; position: absolute; top: 0;"></div>
        </div>

        <div class="vertically-center-middle text-white" style="z-index: 100;">
            <div style="height: 200px; width: 200px; display: inline-block;">
                <quest-image code="scene.portraitFile.uniqueCode"></quest-image>
                <div class="indent-xs"></div>
            </div>
            <div>
                {{scene.text}}
            </div>
            <div class="indent-xs"></div>
            <div ng-repeat="option in scene.options">
                <button class="btn btn-outline-light" ng-style="getButtonStyle()" ng-click="openScene(option.targetScene.id)">
                    {{option.name}}
                </button>
            </div>
        </div>
    </div>
</div>