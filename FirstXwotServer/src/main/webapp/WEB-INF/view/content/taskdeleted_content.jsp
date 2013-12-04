<%-- 
    Document   : taskdeleted_content
    Created on : 8 d�c. 2010, 14:24:54
    Author     : ruppena
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" import="java.util.*" %>
<%@page contentType="text/html" import="org.homelinux.digsim.lpl.domain.*" %>

<div class="post">
    <h2 class="title"><a href="#">LPL Web Solver  </a></h2>
    <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">Task</span></p>
    <div class="entry">
        <c:choose>
            <c:when test="${it == null}">
                    <h1>I had a problem deleting the task. Please try again.</h1>
                </c:when>
                <c:otherwise>
                    <h1>I have successfully canceled the following Task:</h1>
            <ul>
                <li>The item id was: ${it.id}</li>
                <li>Computation was: ${it.computation}</li>
                <li>Result is: ${it.result}</li>
                <li>Computation is finished: ${it.finished}</li>
            </ul>
                </c:otherwise>
            </c:choose>
    
</div>
    <p class="links"><a href="#">Read More</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" title="b0x w">Comments</a></p>
</div>
