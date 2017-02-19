<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 16.02.2017
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty customer}">
  <jsp:useBean id="customer" class="domain.Customer"/>
</c:if>

<HTML>
<HEAD>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
  <TITLE>
    <c:choose>
      <c:when test="${not empty customer.id}">Редактирование данных о пользователях</c:when>
    </c:choose>
  </TITLE>
  <STYLE type="text/css">
    BODY {
      padding: 0;
    }
    TABLE {
      border-collapse: collapse;
    }
    TH, TD {
      border: 1px solid black;
      padding: 5px 30px 5px 10px;
    }
  </STYLE>
</HEAD>
<BODY>
<c:url var="logoutUrl" value="/logout.html"/>
<P style="text-align: right">Вы вошли, как пользователь ${user.login}<BR><A href="${logoutUrl}">выйти</A></P>
<DIV class="user">
  <c:url var="saveUrl" value="/customer_save.html"/>
  <FORM action="${saveUrl}" method="post">
    <c:if test="${not empty customer.id}">
      <INPUT type="text" name="id" value="${customer.id}">
    </c:if>
    Имя:<BR>
    <INPUT type="text" name="name" value="${customer.name}"><BR>
    Фамилия:<BR>
    <INPUT type="text" name="adress" value="${customer.adress}"><BR>
    Отчество:<BR>
    <INPUT type="text" name="number_of_projects" value="${customer.number_of_projects}"><BR>
    Логин:<BR>
    <INPUT type="text" name="finished_projects" value="${customer.finished_projects}"><BR>
    <BUTTON type="submit">Сохранить</BUTTON><BR>
    <BUTTON type="reset">Сбросить</BUTTON><BR>
  </FORM>
  <c:if test="${not empty user.id}">
    <c:url var="deleteUrl" value="/delete.html"/>
    <FORM action="${deleteUrl}" method="post">
      <INPUT type="hidden" name="id" value="${user.id}">
      <BUTTON type="submit">Удалить</BUTTON>
    </FORM>
  </c:if>
  <c:url var="indexUrl" value="/index.html"/>
  <A href="${indexUrl}">Вернуться назад</A>

</DIV>
</BODY>
</HTML>
