<%-- 
    Document   : runningTasks
    Created on : 9 d�c. 2010, 13:18:26
    Author     : ruppena
--%>

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
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>LPL Web Solver - Tasks</title>
        <script type="text/javascript" src="jslpl.js"></script>
        <s:head />
    </head>
    <body>
        <div class="post">
            <h2 class="title"><a href="#">LPL Web Solver</a></h2>
            <p class="meta"><span class="date"><%= new java.util.Date()%></span><span class="posted">List of Tasks on the system</span></p>
            <div class="entry">
    I've found the following tasks on the system:
        <table border="10em" cellspacing="0" cellpadding="1" id="taskstable">
            <tr>
                <th>ID</th>
                <th>Start Time</th>
                <th>Finished</th>
                <th>Aborted</th>
            </tr>

            <p/>
           
            <s:iterator id="i" value="tasks.tasks" status="rowstatus">
                <tr>
                    <s:if test="#rowstatus.odd == true">
                        <td style="background: grey"><a href="task.action?id=<s:property value="id"/>"><s:property value="id"/></a></td>
                        <td style="background: grey"><s:property value="startdate"/></td>
                        <td style="background: grey"><s:property value="finished"/></td>
                        <td style="background: grey"><s:property value="aborted" /></td>
                    </s:if>
                    <s:else>
                        <td><a href="task.action?id=<s:property value="id"/>"><s:property value="id"/></a></td>
                        <td><s:property value="startdate"/></td>
                        <td><s:property value="finished"/></td>
                        <td><s:property value="aborted" /></td>
                    </s:else>
                </tr>
            </s:iterator>
        </table>
 
        <s:url id="url" action="tasks.action" >
            <s:param name="currentPage" value="5" />
        </s:url>
            <s:iterator value="tasks.links" status="rowstatus">
                

                <s:if test="relationship == 'next'">

                    <a href="?start=<s:property value="start+size" />&size=<s:property value="size" />">&gt;&gt;</a>
                </s:if>
                <s:else>

                    <a href="?start=<s:property value="start-size" />&size=<s:property value="size" />">&lt;&lt;</a>
                </s:else>
            
        </s:iterator>
            <%
                        Object start = request.getAttribute("start");
                        Object size = request.getAttribute("size");
            %>
            <script type="text/javascript">
                function refreshTaskList()
                {
                    getTasks(<%=start%>, <%=size%>);
                    setTimeout('refreshTaskList();', 10000);
                }
                refreshTaskList();
            </script>
            </div>
        </div>
    </body>
</html>
