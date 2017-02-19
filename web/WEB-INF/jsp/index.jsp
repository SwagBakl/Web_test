<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>


<HTML>
<HEAD>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">

  <TITLE>Users</TITLE>
  <STYLE>
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
<TABLE>
  <TR>
    <TH>FIO</TH>
    <TH>Login</TH>
    <TH>Password</TH>
    <TH>Role</TH>
  </TR>
  <c:forEach var="user" items="${users}">
    <TR>

        <c:url var="editUrl" value="/edit.html">
       <c:param name="id" value="${user.id}"/>
      </c:url>


      <c:set var="name" value="${fn:substring(user.name, 0, 1)}"/>
      <c:set var="middle_name" value="${fn:substring(user.middle_name, 0, 1)}"/>
      <TD>
        <A href="${editUrl}" >${user.second_name}&nbsp;${name}.&nbsp${middle_name}.</A>
      </TD>
      <c:set var="login" value="${user.login}"
              />
      <TD>
        ${user.login}
      </TD>
      <c:set var="password" value="${user.password}"
              />
      <TD>
        ${user.password}
      </TD>
      <TD>
        <ftm:formatNumber value="${user.role}"/>
      </TD>
    </TR>
  </c:forEach>
</TABLE>
<c:url var="addUrl" value="/add_user.html"/>
<A href="${addUrl}">Add user</A>
</BODY>
</HTML>