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
  <TITLE>Customers</TITLE>
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
<TABLE>
  <TR>
    <TH>Name</TH>
    <TH>Adress</TH>
    <TH>Number_of_projects</TH>
    <TH>Finished_projects</TH>
  </TR>
  <c:forEach var="customer" items="${customers}">
    <TR>
      <c:url var="editUrl" value="/customer_edit.html">
              <c:param name="id" value="${customer.id}"/>
      </c:url>
      <c:set var="Name" value="${customer.name}"/>
      <TD>
        <A href="${editUrl}">${customer.name}</A>
      </TD>
      <c:set var="adress" value="${customer.adress}"
              />
      <TD>
          ${customer.adress}
      </TD>
      <TD>
        <fmt:formatNumber value="${customer.number_of_projects}"/>
      </TD>
      <TD>
        <ftm:formatNumber value="${customer.finished_projects}"/>
      </TD>
    </TR>
  </c:forEach>
</TABLE>
<c:url var="manager_pageUrl" value="/manager_page.html"/>
<A href="${manager_pageUrl}">back</A>
</BODY>
</HTML>