<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>

<head>
    <jsp:include page="import.jsp"/>
</head>

<div>
    <div style="height: 30%">
    </div>
    <div class="inner cover text-white text-center message-window" style="width: 40%;">
        <h1 class="message-text">Ошибка 404</h1>
        <span class="message-tooltip" style="font-size: 1.3em;">Запрашиваемой страницы не существует</span>
    </div>
</div>