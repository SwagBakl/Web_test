<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty projects}">
  <jsp:useBean id="project" class="domain.Project"/>
</c:if>

<HTML>
<HEAD>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
  <TITLE>
      Редактирование данных о пользователях
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
  <c:url var="saveUrl" value="/Projects/project_save.html"/>
  <FORM action="${saveUrl}" method="post">
    <c:if test="${not empty project.id}">
      <INPUT type="hidden" name="id" value="${project.id}">
    </c:if>
    Имя:<BR>
    <INPUT type="text" name="name" value="${project.name}"><BR>
    Date Begin:<BR>
    <INPUT type="text" name="second_name" value="${project.date_begin}"><BR>
    Planned Finish Date:<BR>
    <INPUT type="text" name="middle_name" value="${project.date_planned_finish}"><BR>
    Date Finish:<BR>
    <INPUT type="text" name="login" value="${project.date_finish}"><BR>
    Customer id:<BR>
    <INPUT type="text" name="password" value="${project.customer_id}"><BR>
    <BUTTON type="submit">Сохранить</BUTTON><BR>
    <BUTTON type="reset">Сбросить</BUTTON><BR>
  </FORM>
  <c:url var="indexUrl" value="/Projects/projectTable.html"/>
  <A href="${indexUrl}">Вернуться назад</A>

</DIV>
</BODY>
</HTML>
