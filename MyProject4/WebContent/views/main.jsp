<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
	<head>
		<%@ include file="base/start.html" %>
		<%@ include file="base/end.html" %>
	</head>
	<body>
		<%@ include file="base/navbar.html" %>
		<!-- strat wrapper -->
		<div class="h-screen flex flex-row flex-wrap">
			<%@ include file="base/sidebar.jsp" %>
			<!-- strat content -->
			<jsp:include page="${ uri }"/>
			<!-- end content -->
		</div>
		<!-- end wrapper -->
		<script>
			let message = "${message}";
			if (message != "") {
				alert(message);
			}
		</script>
	</body>
</html>