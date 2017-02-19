<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty user}">
  <jsp:useBean id="user" class="domain.User"/>
</c:if>

<HTML>
<HEAD>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
  <TITLE>
    <c:choose>
      <c:when test="${not empty user.id}">Редактирование данных о пользователях</c:when>
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
  <c:url var="saveUrl" value="/save.html"/>
  <FORM action="${saveUrl}" method="post">
    <c:if test="${not empty user.id}">
      <INPUT type="text" name="id" value="${user.id}">
    </c:if>
    Имя:<BR>
    <INPUT type="text" name="name" value="${user.name}"><BR>
    Фамилия:<BR>
    <INPUT type="text" name="second_name" value="${user.second_name}"><BR>
    Отчество:<BR>
    <INPUT type="text" name="middle_name" value="${user.middle_name}"><BR>
    Логин:<BR>
    <INPUT type="text" name="login" value="${user.login}"><BR>
    Пароль:<BR>
    <INPUT type="text" name="password" value="${user.password}"><BR>
    Роль:<BR>
    <fmt:formatNumber var="role" value="${user.role}"/>
    <INPUT type="text" name="role" value="${user.role}"><BR>
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
