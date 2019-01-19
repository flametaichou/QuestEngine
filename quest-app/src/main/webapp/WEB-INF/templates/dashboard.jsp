<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<style>
    .quest-grid {
        display: table;
        border-collapse: separate;
        border-spacing: 15px;
    }
    .quest-grid-element {
        display: table-cell;
        vertical-align: middle;
        height: 200px;
        width: 200px;
        padding: 10px;
        border-radius: 15px;
        text-align: center;
    }
    .a-white-button {
        border: 1px solid rgba(255,255,255,0.2);
        text-decoration: none !important;
        color: white !important;
        text-transform: uppercase;
        cursor: pointer;
    }
    .a-white-button.disabled {
        border: 1px solid rgba(255,255,255,0.1) !important;
    }
    .a-white-button:hover {
        background: rgba(255,255,255,0.1);
    }
    .a-white-button.disabled:hover {
        background: rgba(255,255,255,0.0) !important;
    }
    .a-white-button-text {
        font-size: 2.4vh;
        height: 4.5vh;
        display: inline-block;
        top: 3vh;
    }
    .a-white-button-text.disabled {
        color: rgba(255,255,255,0.2) !important;
    }
</style>

<div ng-controller="ControllerDashboard">

    <div class="quest-grid">
        <a class="quest-grid-element a-white-button" ng-repeat="quest in quests"
           href="#pages/quest/{{quest.uniqueCode}}/editor">
            <span class="a-white-button-text">{{quest.name}}</span>
        </a>
        <a class="quest-grid-element a-white-button" ng-click="addNewQuest()">
            <span class="a-white-button-text">+ Добавить новый</span>
        </a>
    </div>

</div>
