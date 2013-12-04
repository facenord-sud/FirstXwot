<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="Content-Language" content="en-us" />
        <meta name="description" content="eHalth Executor is a powerful modeling system..." />
        <meta name="keywords" content="eHealth" />
        <META NAME="author" content="Andreas ruppen" />
        <meta name="robots" content="all" />
        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE" />
        <title>Next Generation Health Care</title>
        <title>eHealth Executor Web Interface</title>
        <c:set var="req" value="${pageContext.request}" />
        <c:set var="uri" value="${req.requestURI}" />
        <c:set var="url">${req.requestURL}</c:set>
        <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" /> 
        
        <link href="css/lpl.css" rel="stylesheet" type="text/css" media="screen" />

    </head>
    <body>
        <div id="wrapper">
            <div id="header-wrapper">
                <div id="header">
                    <tiles:insertAttribute name="header" />
                </div>
            </div>
            <!-- end #header -->
            <div id="page">
                <div id="page-bgtop">
                    <div id="page-bgbtm">
                        <div id="content">
                            <tiles:insertAttribute name="content" />

                            <div style="clear: both;">&nbsp;</div>
                        </div>
                        <!-- end #content -->
                        <tiles:insertAttribute name="sidebar" />
                        <!-- end #sidebar -->
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                </div>
            </div>
            <!-- end #page -->
        </div>
        <tiles:insertAttribute name="footer" />
        <!-- end #footer -->
    </body>
</html>