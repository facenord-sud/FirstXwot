<%-- 
    Document   : header
    Created on : 9 d�c. 2010, 11:32:58
    Author     : ruppena
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="logo">
    <h1><a href="#">LPL</a></h1>
    <p>Web Interface</p>
</div>
<div id="menu">
    <ul>
        <%
                    String url = (request.getRequestURL()).toString();
                   
                    boolean ishome = url.contains("index");
                    boolean istasks = url.contains("task");
                    String homeclass = "";
                    String tasksClass = "";
                    if (istasks) {
                        tasksClass = "class=\"current_page_item\"";
                    } else if(ishome){
                        homeclass = "class=\"current_page_item\"";
                    }
        %>


        <li <%=homeclass%>><a href="index.jsp">Home</a></li>
        <li><a href="http://www.unifr.ch/informatics/">Our Departement of informatics</a></li>
        <li ><a href="http://www.virtual-optima.com">Go to Virtual Optima</a></li>
        <li ><a  href="&#109;&#97;&#105;&#108;&#116;&#111;&#58&#116;&#111;&#110;&#121;&#46;&#104;&#117;&#101;&#114;&#108;&#105;&#109;&#97;&#110;&#110;&#64;&#117;&#110;&#105;&#102;&#114;&#46;&#99;&#104;">Contact</a></li>
        <li <%=tasksClass%>><a  href="tasks.action">Tasks</a></li>
    </ul>
</div>