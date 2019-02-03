<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div style="width: 100%; height: 100%; text-align: center; background-color: #0e0e0e;">
    <div style="display: inline-block;">
        <div ng-if="imageForm.image">
            <img style="height: 100%; max-width: 100%;"
                 ng-src="{{'data:' + imageForm.image.format + ';base64,' + imageForm.image.data}}"/>
        </div>
        <div ng-if="!imageForm.image">
            <div ng-if="imageForm.error"
                 style="height: 100%; max-width: 100%; text-align: center; vertical-align: middle; display: table-cell; color: lightgray;">
                Изображение не найдено
            </div>
            <div ng-if="!imageForm.error"
                 style="height: 100%; max-width: 100%; text-align: center; vertical-align: middle; display: table-cell; color: lightgray;">
                Идет загрузка
            </div>
        </div>
    </div>
</div>