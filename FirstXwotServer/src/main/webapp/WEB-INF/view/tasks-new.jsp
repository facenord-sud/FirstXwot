<%-- 
    Document   : tasks-new
    Created on : 5 sept. 2011, 11:21:55
    Author     : ruppena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" import="java.util.*" %>
<%@page import="org.homelinux.digsim.lpl.domain.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <script type="text/javascript" src="../../jslpl.js"></script>
<div class="post">
    <h2 class="title"><a href="#">LPL Web Solver  </a></h2>
    <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">Tasks</span></p>
    <div class="entry">
        <h1>Following is a list of currently Running Tasks</h1>
        <div id="ajaxLoader" align="center" ></div>
         <ul id="tasklist">
            <c:forEach var="p" items="${it.tasks}">
                <li><a href="${p.uri}">${p.id} (${p.status})</a>
                    <c:choose>
                        <c:when test="${p.status == 'runing'}">
                            <form name="deleteform"><input type="button" name="Delete" value="Abort Task" onclick="javascript:abortTask('${p.uri}')"></form>
                        </c:when>
                        <c:otherwise>
                            <form name="deleteform"><input type="button" name="Delete" value="Delete Task" onclick="javascript:abortTask('${p.uri}')"></form>
                        </c:otherwise>
                    </c:choose>
                    <%--<form action="${p.uri}abort" method="POST"><input type="submit" name="Delete" value="Delete"></form>--%>
                    
                </li>
            </c:forEach>
        </ul>
        <c:forEach var="l" items="${it.links}">
            <c:choose>
                <c:when test="${l.relationship == 'next'}">

                    <a href="${l.href}">&gt;&gt;</a>
                </c:when>
                <c:otherwise>

                    <a href="${l.href}">&lt;&lt;</a>
                </c:otherwise>
            </c:choose>

        </c:forEach>
</div>
    <p class="links">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
</div>
    </body>
</html>
