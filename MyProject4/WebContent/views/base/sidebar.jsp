<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="sideBar"
	class="relative flex flex-col flex-wrap bg-white border-r border-gray-300 p-6 flex-none w-64 md:-ml-64 md:fixed md:top-0 md:z-30 md:h-screen md:shadow-xl animated faster">
	<!-- sidebar content -->
	<div class="flex flex-col">
		<!-- sidebar toggle -->
		<div class="text-right hidden md:block mb-4">
			<button id="sideBarHideBtn">
				<i class="fad fa-times-circle"></i>
			</button>
		</div>
		<!-- end sidebar toggle -->

		<p class="uppercase text-xs text-gray-600 mb-4 tracking-wider">계정관리</p>

		<!-- link -->
		<a href="/main.do?type=0"
			class="mb-3 capitalize font-medium text-sm hover:text-teal-600 transition ease-in-out duration-500">
			<i class="fad fa-chart-pie text-xs mr-2"></i> 로그인
		</a>
		<!-- end link -->

		<!-- link -->
		<a href="./main.do?type=1"
			class="mb-3 capitalize font-medium text-sm hover:text-teal-600 transition ease-in-out duration-500">
			<i class="fad fa-shopping-cart text-xs mr-2"></i> 회원가입
		</a>
		<!-- end link -->

		<p class="uppercase text-xs text-gray-600 mb-4 mt-4 tracking-wider">리뷰</p>

		<!-- link -->
		<a href="./main.do?type=2"
			class="mb-3 capitalize font-medium text-sm hover:text-teal-600 transition ease-in-out duration-500">
			<i class="fad fa-envelope-open-text text-xs mr-2"></i> 면접리뷰
		</a>
		<!-- end link -->

		<!-- link -->
		<a href="./main.do?type=3"
			class="mb-3 capitalize font-medium text-sm hover:text-teal-600 transition ease-in-out duration-500">
			<i class="fad fa-comments text-xs mr-2"></i> 기업리뷰
		</a>
		<!-- end link -->

		<p class="uppercase text-xs text-gray-600 mb-4 mt-4 tracking-wider">게시판</p>

		<!-- link -->
		<a href="./main.do?type=8"
			class="mb-3 capitalize font-medium text-sm hover:text-teal-600 transition ease-in-out duration-500">
			<i class="fad fa-text text-xs mr-2"></i> 게시판
		</a>
		<!-- end link -->
	</div>
	<!-- end sidebar content -->
</div>
<!-- end sidbar -->