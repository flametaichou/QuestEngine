<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div class="modal-content">
    <div class="modal-header">
        <span class="modal-title modal-title-ex">
            Выбор картинки
        </span>
    </div>

    <div class="modal-body">
        <form name="form" novalidate>
            <div class="form-group">
                <label>Файлы</label>
                <div ng-repeat="file in files" style="width: 200px; height: 200px; margin: 15px; display: inline-block;" ng-click="select(file)">
                    <quest-image code="file.uniqueCode"></quest-image>
                    <span>{{file.name}}</span>
                </div>
            </div>
        </form>
    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-primary"
                ng-click="submit()">
            <spring:message code="button.select"/>
        </button>
        <button type="button" class="btn btn-default"
                ng-click="close()">
            <spring:message code="button.close"/>
        </button>
    </div>
</div>