<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 02.02.2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
    <title>Manager</title>
  <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
<c:url var="logoutUrl" value="/logout.html"/>
<P style="text-align: right">Вы вошли, как пользователь ${user.login}<BR><A href="${logoutUrl}">выйти</A></P>
<c:url var="projectUrl" value="/Projects/projectTable.html"/>
<c:url var="customerUrl" value="/customers/customerTable.html"/>
<FORM method="get" action="${customerUrl}">
  <BUTTON type="submit">Customers</BUTTON>
</FORM><BR>
<FORM method="get" action="${projectUrl}">
  <BUTTON type="submit">Projects</BUTTON>
  </FORM>
</body>
</html>
