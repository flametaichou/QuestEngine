<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<div ng-controller="ControllerAccounts">

    <div class="inner cover"
         style="width: 90%; padding: 10px; margin-right: auto; margin-left: auto; border-radius: 5px; background-image: linear-gradient(to bottom right, whitesmoke, lightgray);">
        <div class="text-center"
             style="position: relative; font-size: 1.3em; font-weight: bolder; padding-bottom: 10px; border-bottom: 1px solid #b9b7b7;">
            Аккаунты
            <button class="btn btn-sm btn-secondary"
                    style="position: absolute; right: 0;"
                    ng-click="addNewUser()">
                Создать пользователя
            </button>
        </div>

        <div class="form-inline" style="position: relative; padding-top: 7px;" ng-if="creatingUser">
            <div class="form-group mb-2" style="margin-right: 10px;">
                <input type="text" ng-model="newUser.username" class="form-control form-control-sm" placeholder="Логин"
                       ng-disabled="edit"
                       ng-required="true">
            </div>
            <div class="form-group mb-2" style="margin-right: 10px;">
                <input type="password" ng-model="newUser.password" class="form-control form-control-sm"
                       placeholder="Пароль" ng-required="true">
            </div>
            <div class="form-group mb-2" style="margin-right: 10px;">
                <input type="password" ng-model="newUser.confirmPassword" class="form-control form-control-sm"
                       placeholder="Повторите пароль" ng-required="true">
            </div>
            <div class="form-group mb-2" style="margin-right: 10px;">
                <select ng-model="newUser.accountRole.role" class="form-control form-control-sm"
                        ng-disabled="edit">
                    <option value="">Без роли</option>
                    <option ng-repeat="role in roles">{{role}}</option>
                </select>
            </div>
            <div class="form-group mb-2" style="margin-right: 10px;">
                <label>{{error}}</label>
            </div>

            <div style="position: absolute; right: 0;">
                <button class="btn btn-sm btn-primary"
                        type="submit"
                        ng-click="submitUser()">
                    Сохранить пользователя
                </button>
                <button class="btn btn-sm btn-secondary"
                        ng-click="cancel()">
                    Отмена
                </button>
            </div>
        </div>

        <table class="table table-striped table-adaptive-font">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Имя пользователя</th>
                <th scope="col">Роль</th>
                <th scope="col">Действия</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="account in accounts" style="height: 48px;">
                <td>{{account.id}}</td>
                <td>{{account.username}}</td>
                <td>
                    <span class="badge badge-secondary" style="margin: 2px;" ng-repeat="role in account.accountRoles">
                        {{role.role + ' '}}
                    </span>
                </td>
                <td>
                    <security:authorize access="hasRole('ADMIN')">
                        <span class="fa fa-times"
                              style="font-size: 22px;"
                              ng-click="deleteUser(account.username)"
                              title="Удалить"></span>
                            <span class="fa fa-pencil"
                                  style="font-size: 22px;"
                                  ng-click="editUser(account)"
                                  title="Редактировать"></span>
                    </security:authorize>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
