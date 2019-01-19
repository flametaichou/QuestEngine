<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="icon" href="favicon.ico" />

    <title>Quest Engine</title>

    <link href="<c:url value='/static/css/bootstrap/bootstrap.min.css' />" rel="stylesheet" media="screen"/>
    <link href="<c:url value='/static/css/font-awesome/font-awesome-4.7.0/css/font-awesome.css' />" rel="stylesheet"/>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
    <script src="<c:url value='/static/js/angular-1.5.11/angular.js' />"></script>

    <style>
        body {
            background-repeat: no-repeat;
            background: #f5f5f5 url(<c:url value='/static/images/background.png' />) center fixed;
            background-size: cover;
        }
    </style>
</head>

<div ng-controller="AppController">
    <div style="height: 30%">
    </div>
    <div class="inner cover text-white text-center glass-window" style="width: 40%;">
        <h1 class="greeting-text">Ошибка 403</h1>
        <span class="greeting-tooltip" style="font-size: 1.3em;">У вас нет прав для доступа к запрашиваемому ресурсу</span>
    </div>
</div>