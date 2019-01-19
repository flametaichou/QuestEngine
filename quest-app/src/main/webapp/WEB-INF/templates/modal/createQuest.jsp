<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>

<div class="modal-content">
    <div class="modal-header">
        <h3>
            <spring:message code="quest.form.new"/>
        </h3>
    </div>
    <div class="modal-body">
        <form role="form">
            <div class="row">
                <label class="col-lg-4">
                    <spring:message code="quest.name"/>
                </label>
                <div class="col-lg-8">
                    <input type="text" class="form-control"
                           ng-model="quest.name"
                           ng-required="true">
                </div>
            </div>
            <div class="indent-xs"/>
            <div class="row">
                <label class="col-lg-4">
                    <spring:message code="quest.uniqueCode"/>
                </label>
                <div class="col-lg-8">
                    <input type="text" class="form-control"
                           ng-model="quest.uniqueCode">
                </div>
            </div>
            <div class="indent-xs"/>
            <div class="row">
                <label class="col-lg-4">
                    <spring:message code="quest.description"/>
                </label>
                <div class="col-lg-8">
                    <textarea class="form-control" ng-model="quest.description"/>
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