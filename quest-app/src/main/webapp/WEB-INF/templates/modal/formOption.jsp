<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div class="modal-content">
    <div class="modal-header">
        <h3 ng-if="!option.id">
            <spring:message code="option.form.new"/>
        </h3>
        <h3 ng-if="option.id">
            <spring:message code="option.form.edit"/>
        </h3>
    </div>
    <div class="modal-body">
        <form role="form">
            <div class="row form-group">
                <label class="col-lg-4">
                    <spring:message code="option.name"/>
                </label>
                <div class="col-lg-8">
                    <input type="text" class="form-control"
                           ng-model="option.name"
                           ng-required="true">
                </div>
            </div>

            <div class="row form-group">
                <label class="col-lg-4">
                    <spring:message code="option.targetScene"/>
                </label>
                <div class="col-lg-8">
                    <select ng-model="option.targetSceneId" class="form-control form-control-sm">
                        <option value=""><spring:message code="common.select"/></option>
                        <option value="{{scene.id}}" ng-repeat="scene in scenes">{{scene.name}}</option>
                    </select>
                </div>
            </div>
        </form>
        <label class="text-danger">{{error}}</label>
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" ng-click="submit()">
            <spring:message code="button.submit"/>
        </button>
        <button class="btn btn-secondary" ng-click="close()">
            <spring:message code="button.close"/>
        </button>
    </div>
</div>