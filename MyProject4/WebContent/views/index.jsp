<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
    <head>
		<%@include file="layout/basic.jsp"%>
    </head>
    <body class="sb-nav-fixed">
		<%@include file="layout/bodyTop.jsp"%>
        <div id="layoutSidenav">
		<%@include file="layout/bodySide.jsp"%>
            <div id="layoutSidenav_content">
				<jsp:include page="${ uri }"/>
                
				<%@include file="layout/bodyBottom.jsp"%>
            </div>
        </div>
    </body>
</html>