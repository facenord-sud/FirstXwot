<%--
    Document   : tasks_content
    Created on : 7 d�c. 2010, 12:01:32
    Author     : ruppena
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.homelinux.digsim.lpl.domain.Task"%>
<div class="post">
    <h2 class="title"><a href="#">LPL Web Solver  </a></h2>
    <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">Task</span></p>
    <div class="entry">
        <jsp:useBean id="dateValue" class="java.util.Date" />
        
        <jsp:setProperty name="dateValue" property="time" value="${it.startdate}" />
        <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" />
        <h1>Following are the details of task: ${it.id}</h1>
        <dl>
            <dt>Id:</dt><dd>${it.id}</dd>
            <dt>Name: </dt><dd>${it.problemName}</dd>
            <dt>User:</dt><dd>${it.userid}</dd>
            <dt>Start date:</dt><dd>${dateValue}</dd>
            <dt>Callback Strategy:</dt><dd>${it.callbackStrategy}</dd>
            <dt>Callback Address:</dt><dd>${it.callbackAddress}</dd>
            <dt>Finished:</dt><dd>${it.finished}</dd>
            <dt>Aborted:</dt><dd>${it.aborted}</dd>
            <dt>Used Solver Time:</dt><dd>${it.usedSolverTime}</dd>
            <dt>Computation:</dt><dd>${it.input}</dd>
            <%
                Task ta = (Task) request.getAttribute("task");
                if(ta.getResult().getFileResult().length()!=0)
                {
            %>
            <dt>Solution:</dt><dd><a href="${it.result.fileResult}">Get Solution</a></dd>
            <%
                }
                if(ta.getResult().getImageResult().length()!=0)
                {
            %>
            <dt>Image</dt><dd><a href="${it.result.imageResult}">Get Image</a></dd>
             <%
                }
                if(ta.getResult().getLogResult().length()!=0)
                {
            %>
            <dt>Log:</dt><dd>${it.result.logResult}</dd>
            <%
                }
            %>
            <%
                if(ta.isAborted() || ta.isFinished())
                 {
            %>
                <form name="deleteform"><input type="button" name="Delete" value="Delete Task" onclick="javascript:abortTask('${it.uri}')"></form>
            <%
                }
                else
                {
            %>
                <form name="deleteform"><input type="button" name="Delete" value="Abort Task" onclick="javascript:abortTask('${it.uri}')"></form>
            <%
                }
            %>
        </dl>
            <%-- This is another way to convert the timestamp
        <%
                    Task ta = (Task) request.getAttribute("task");
                    java.util.Date date = new java.util.Date(ta.getStartdate());


        %>
        <%=date%>
            --%>
    </div>
    <p class="links"><a href="../">Back to task list</a></p>
</div>
