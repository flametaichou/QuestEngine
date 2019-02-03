<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div class="modal-content">
    <div class="modal-header">
        <span class="modal-title modal-title-ex">
            Загрузка файла
        </span>
    </div>

    <div class="modal-body">
        <form name="form" novalidate>
            <div class="form-group">
                <label>Выберите файл</label>
                <input type="file" class="form-control" file-upload
                       ng-model="request.file"
                       ng-required="true">
            </div>
        </form>
    </div>

    <div class="modal-footer">
        <button type="button" class="btn btn-primary"
                ng-click="submit(request)">
            <spring:message code="button.submit"/>
        </button>
        <button type="button" class="btn btn-default"
                ng-click="close()">
            <spring:message code="button.close"/>
        </button>
    </div>
</div>