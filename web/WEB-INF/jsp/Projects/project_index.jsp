<%--
  Created by IntelliJ IDEA.
  User: Денис
  Date: 02.02.2017
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>


<HTML>
<HEAD>
  <META http-equiv="Content-Type"
        content="text/html; charset=UTF-8">
  <TITLE>Projects</TITLE>
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
<P style="text-align: right">You are signed in as ${user.login}<BR><A href="${logoutUrl}">Logout</A></P>
<TABLE>
  <TR>
    <TH>ID</TH>
    <TH>Name</TH>
    <TH>Date_begin</TH>
    <TH>Date_planned_finish</TH>
    <TH>Date_finish</TH>
    <TH>Project_seccess</TH>
    <TH>Customer_id</TH>
  </TR>
  <c:forEach var="project" items="${projects}">
    <TR>
      <TD>
        <fmt:formatNumber value="${project.id}"/>
      </TD>

      <TD>
          ${project.name}
      </TD>
      <TD>
        <fmt:formatDate value="${project.date_begin}"/>
      </TD>
      <TD>
        <ftm:formatDate value="${project.date_planned_finish}"/>
      </TD>
      <TD>
        <ftm:formatDate value="${project.date_finish}"/>
      </TD>
      <TD>
        <c:choose>
          <c:when test="${project.project_seccess}">Yes</c:when>
          <c:otherwise>No</c:otherwise>
        </c:choose>
      </TD>
      <TD>
        <fmt:formatNumber value="${project.customer_id}"/>
      </TD>
    </TR>
  </c:forEach>
</TABLE>
<c:url var="manager_pageUrl" value="/manager_page.html"/>
<A href="${manager_pageUrl}">back</A>
</BODY>
</HTML>
