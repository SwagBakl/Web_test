<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 07.02.2017
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${empty project}">
  <jsp:useBean id="project" class="domain.Project"/>
  </c:if>


<html>
<head>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
    <title>Edit project</title>
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
<DIV class="project">
  <c:url var="saveUrl" value="/Projects/project_save.html"/>
  <FORM action="${saveUrl}" method="post">
    <c:if test="${not empty project.id}">
      <INPUT type="text" name="id" value="${project.id}"/>
      </c:if>
    Name:<BR>
    <INPUT type="text" name="name" value="${project.name}"><BR>
    Date_begin:<BR>
    <INPUT type="text" name="adress" value="${project.date_begin}"><BR>
    Date_planned_finish:<BR>
    <INPUT type="text" name="number_of_projects" value="${project.date_planned_finish}"><BR>
    Date_finish:<BR>
    <INPUT type="text" name="finished_projects" value="${project.date_finish}"><BR>
    Project_seccess:<BR>
    <INPUT type="text" name="finished_projects" value="${project.project_seccess}"><BR>
    Customer_id:<BR>
    <INPUT type="text" name="finished_projects" value="${project.customer_id}"><BR>
    <Button type="submit">Save</Button><BR>
    <Button type="reset">Reset</Button><BR>
  </FORM>
  <c:if test="${not empty project.id}">
    <c:url var="deleteUrl" value="/Projects/project_delete.html"/>
    <FORM action="${deleteUrl}" method="post">
      <INPUT type="hidden" name="id" value="${project.id}">
      <BUTTON type="submit">Delete</BUTTON>
    </FORM>
  </c:if>
  <c:url var="projectTableUrl" value="/Projects/projectTable.html"/>
  <A href="${projectTableUrl}">back</A>
  </FORM>
</DIV>
</body>
</html>
