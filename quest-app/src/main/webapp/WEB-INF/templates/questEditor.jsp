<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<div ng-controller="ControllerQuestEditor">

    <div class="text-center">
        <h1 class="greeting-text-small">{{quest.name}}</h1>
        <span class="greeting-tooltip">{{quest.description}}</span>

        <div class="indent-xs"/>

        <div class="btn-group btn-group-toggle" data-toggle="buttons" style="width: 100%;">
            <button class="btn btn-outline-light btn-block">
                Общая информация
            </button>
            <button class="btn btn-outline-light btn-block">
                Сцены
            </button>
            <button class="btn btn-outline-light btn-block">
                Файлы
            </button>
        </div>
    </div>

</div>
