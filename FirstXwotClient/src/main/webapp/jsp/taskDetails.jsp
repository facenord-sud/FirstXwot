<%-- 
    Document   : taskDetails
    Created on : 9 d�c. 2010, 13:18:41
    Author     : ruppena
--%>

<%@page import="org.homelinux.digsim.lpl.domain.Task"%>
<%@page import="org.homelinux.digsim.lpl.domain.Tasks" %>
<%@page import="org.homelinux.digsim.lpl.client.*" %>
<%@page import="org.netbeans.saas.*" %>
<%@page import="java.util.List"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html PUBLIC
    "-//W3C//DTD XHTML 1.1 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>
<%RestResponse result = null;%>
<%
            Object id = request.getAttribute("id");
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>LPL Web Solver - Task Details</title>
        <script type="text/javascript" src="jslpl.js"></script>
        <script type="text/javascript">
            stillRunning = true;
        </script>
        <s:head />
    </head>
    <body>
        <div class="post">
            <h2 class="title"><a href="#">LPL Web Solver</a></h2>
            <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">Task Details</span></p>
            <div class="entry">
                <%
                            Task task = (Task) request.getAttribute("task");
                            Long startdate = task.getStartdate();
                %>
                <jsp:useBean id="dateValue" class="java.util.Date" />
                <jsp:setProperty name="dateValue" property="time" value="<%=startdate%>" />
                <fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy HH:mm" />
                <h1>Following are the details of task: <s:property value="task.id" /></h1>

                <div id="taskdiv">
                    <dl>
                        <dt>Id:</dt><dd><s:property value="task.id" /></dd>
                        <dt>Name: </dt><dd><s:property value="task.problemName" /></dd>
                        <dt>User:</dt><dd><s:property value="task.username" /></dd>
                        <dt>Start date:</dt><dd>${dateValue}</dd>
                        <dt>Callback Strategy:</dt><dd><s:property value="task.callbackStrategy" /></dd>
                        <dt>Callback Address:</dt><dd><s:property value="task.callbackAddress" /></dd>
                        <dt>Finished:</dt><dd><s:property value="task.finished" /></dd>
                        <dt>Aborted:</dt><dd><s:property value="task.aborted" /></dd>
                        <dt>Used Solver Time:</dt><dd><s:property value="task.usedSolverTime" /></dd>
                        <dt>Computation:</dt><dd><a href='<s:property value="task.computation" />'>Get Input Description</a></dd>
                        <dt>Solution:</dt><dd><a href='<s:property value="task.result.fileResult"/>'>Get Solution</a></dd>
                        <s:if test="task.result.imageResult != ''">
                            <dt>Image</dt><dd><a href='<s:property value="task.result.imageResult" />'>Get Image</a></dd>
                        </s:if>
                        <dt>Log:</dt><dd><s:property value="task.result.logResult" /></dd>
                    </dl>
                </div>
                <s:if test="!(task.finished || task.aborted)">
                    <form action="#" id="deleteform"  name="deleteform"><input type="button" name="Delete" value="Abort" onclick="javascript:stillRunning=false;abortTask('http://localhost:8080/SlowLPLServer/work/tasks/'+<%=id%>)"></form>
                    </s:if>

                <script type="text/javascript">
                    function refreshTask()
                    {
                        if(stillRunning)
                        {
                            getTask(<%=id%>);
                
                            setTimeout('refreshTask();', 10000);
                        }
                    }
                    refreshTask();
                </script>
            </div>
        </div>

    </body>
</html>
