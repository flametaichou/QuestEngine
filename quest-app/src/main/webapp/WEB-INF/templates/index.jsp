<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<html>
<head>
    <jsp:include page="import.jsp"/>
</head>

<body>
    <div class="left-part">
        <div class="vertically-center-outer text-white" style="padding-right: 20px;">
            <div class="vertically-center-middle text-right">
                <div style="margin: 7px;">
                    <h1 class="mt-5 greeting-text">Quest Engine</h1>
                    <p class="lead greeting-text">Выберите один из пунктов меню</p>
                </div>
                <div class="indent-xs"></div>
                <a class="btn btn-outline-light mx-2" href="engine/">Редактор квестов</a>
                <a class="btn btn-outline-light mx-2" href="viewer/">Готовые квесты</a>
            </div>
        </div>
    </div>
    <div class="right-part">
        Тут будет блог
    </div>
</body>
</html>