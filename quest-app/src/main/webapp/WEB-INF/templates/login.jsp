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
    <div style="height: 25%">
    </div>
    <div class="inner cover text-white text-center glass-window" style="width: 550px;">
        <h1 class="mt-5 greeting-text-small">Quest Engine</h1>
        <span class="greeting-tooltip">Для продолжения введите логин и пароль</span>
        <form role="form" style="margin-top: 50px;" action="<c:url value="/j_spring_security_check"/>" method="post">
            <div class="form-group" style="margin-top: 15px;">
                <input type="text" id="j_username" name="j_username"
                       value="<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>"
                       placeholder="Логин"
                       ng-required="true"
                       class="form-control" autofocus>
            </div>

            <div class="form-group">
                <input type="password" id="j_password" name="j_password"
                       placeholder="Пароль"
                       ng-required="true"
                       class="form-control" autocomplete="off">
            </div>

            <div class="form-group" style="margin-top: 15px;">
                <span class="help-block">
                    <c:if test="${not empty param.login_error}">
                        Ошибка входа! Введены неправильное имя пользователя или пароль.
                    </c:if>
                    </span>
            </div>

            <button class="btn btn-outline-light btn-lg mx-2" type="submit">
                Войти
            </button>
        </form>

    </div>
</div>