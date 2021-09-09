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
			
			// navbar
			var navbarToggle = document.getElementById('navbarToggle'),
			navbar = document.getElementById('navbar');

			navbarToggle.addEventListener('click', function() {
			if (navbar.classList.contains('md:hidden')) {
				navbar.classList.remove('md:hidden');
				navbar.classList.add('fadeIn');
			} else if (navbar.classList.contains('fadeIn')) {
				navbar.classList.remove('fadeIn');
				navbar.classList.add('fadeOut');

				setTimeout(() => {
					navbar.classList.remove('fadeOut');
					navbar.classList.add('md:hidden');
				}, 450);
			};
			});

			//work with sidebar
			var btn = document.getElementById('sliderBtn'),
			sideBar = document.getElementById('sideBar'),
			sideBarHideBtn = document.getElementById('sideBarHideBtn');

			//show sidebar 
			btn.addEventListener('click', function() {
			if (sideBar.classList.contains('md:-ml-64')) {
				sideBar.classList.replace('md:-ml-64', 'md:ml-0');
				sideBar.classList.remove('md:slideOutLeft');
				sideBar.classList.add('md:slideInLeft');
			};
			});

			//hide sideBar    
			sideBarHideBtn.addEventListener('click', function() {
			if (sideBar.classList.contains('md:ml-0', 'slideInLeft')) {
				let animate = function() {
					sideBar.classList.remove('md:slideInLeft');
					sideBar.classList.add('md:slideOutLeft');

					setTimeout(() => {
						sideBar.classList.replace('md:ml-0', 'md:-ml-64');
					}, 300);
				};
				animate();
			};
			});
			//end with sidebar
		</script>
	</body>
</html>