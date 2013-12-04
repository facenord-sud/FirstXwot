<!DOCTYPE html PUBLIC 
	"-//W3C//DTD XHTML 1.1 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<decorator:usePage id="myPage" />
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
        <meta http-equiv="Content-Language" content="en-us" />
        <meta name="description" content="LPL is a powerful modeling system..." />
        <meta name="keywords" content="lpl,modeling,optimization" />
        <META NAME="author" content="Tony Huerlimann" />
        <meta name="robots" content="all" />
        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE" />
       <title><decorator:title default="LPL Web Solver"/></title>
        <link href="/SlowLPLClient/css/lpl.css" rel="stylesheet" type="text/css" media="screen" />
        <decorator:head/>
    </head>
    <body>
        <div id="wrapper">
            <div id="header-wrapper">
                <div id="header">
                    <%--<page:applyDecorator name="empty" page="/decorators/header"/>--%>
                    <%@ include file="header.jsp" %>
                </div>
            </div>
            <!-- end #header -->
            
            <div id="page">
                <div id="page-bgtop">
                    <div id="page-bgbtm">
                        <div id="content">
                            <decorator:body/>

                            <div style="clear: both;">&nbsp;</div>
                        </div>
                        <!-- end #content -->
                        <%@ include file="sidebar.jsp" %>
                        <!-- end #sidebar -->
                        <div style="clear: both;">&nbsp;</div>
                    </div>
                </div>
            </div>
            <!-- end #page -->
            
            <%--<div id="page"><decorator:body /></div>--%>
        </div>
        <%--<page:applyDecorator name="empty" page="/decorators/footer"/>--%>
        <%@ include file="footer.jsp" %>
        <!-- end #footer -->
    </body>
</html>


