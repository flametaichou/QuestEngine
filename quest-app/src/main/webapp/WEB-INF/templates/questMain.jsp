<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<div ng-controller="ControllerQuestMain">

    <div class="quest-grid">
        <a class="quest-grid-element a-white-button" ng-repeat="quest in quests"
           href="#/quest/{{quest.uniqueCode}}/">
            <span class="a-white-button-text">{{quest.name}}</span>
        </a>
    </div>

</div>
