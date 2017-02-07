<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 05.02.2017
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty customer}">
  <jsp:useBean id="customer" class="domain.Customer"/>
</c:if>

<c:if test="${empty user}">
  <jsp:useBean id="user" class="domain.User"/>
</c:if>


<html>
<head>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
    <title>
    <c:choose>
      <c:when test="${not empty customer.id}">Edit customer data</c:when>
    </c:choose>
    </title>
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
</head>
<body>
<c:url var="logoutUrl" value="/logout.html"/>
<P style="text-align: right">Вы вошли, как пользователь ${user.login}<BR><A href="${logoutUrl}">выйти</A></P>
<DIV class="customer">
  <c:url var="saveUrl" value="/customer_save.html"/>
  <FORM action="${saveUrl}" method="post">
    <c:if test="${not empty customer.id}">
      <INPUT type="hidden" name="id" value="${customer.id}">
      </c:if>
    Name:<BR>
    <INPUT type="text" name="name" value="${customer.name}"><BR>
    Adress:<BR>
    <INPUT type="text" name="adress" value="${customer.adress}"><BR>
    Number of projects:<BR>
    <INPUT type="text" name="number_of_projects" value="${customer.number_of_projects}"><BR>
    Finished projects:<BR>
    <INPUT type="text" name="finished_projects" value="${customer.finished_projects}"><BR>
    <Button type="submit">Save</Button><BR>
    <Button type="reset">Reset</Button><BR>
    </FORM>
  <c:if test="${not empty customer.id}">
  <c:url var="deleteUrl" value="/customer_delete.html"/>
  <FORM action="${deleteUrl}" method="post">
    <INPUT type="hidden" name="id" value="${customer.id}">
    <BUTTON type="submit">Delete</BUTTON>
  </FORM>
    </c:if>
  <c:url var="customerTableUrl" value="/customers/customerTable.html"/>
  <A href="${customerTableUrl}">back</A>
  </DIV>
</body>
</html>
