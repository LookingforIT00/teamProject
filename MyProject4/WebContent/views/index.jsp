<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- strat content -->
<div class="bg-gray-100 flex-1 p-6 md:mt-16">
	<!-- General Report -->
	<%@ include file="index/generalReport.html"%>
	<!-- End General Report -->

	<!-- strat Analytics -->
	<%@ include file="index/analytics-1.html"%>
	<!-- end Analytics -->

	<!-- Sales Overview -->
	<%@ include file="index/salesOverview.html"%>
	@include('./index/salesOverview.html')
	<!-- end Sales Overview -->

	<!-- start numbers -->
	<%@ include file="index/numbers.html"%>
	<!-- end nmbers -->

	<!-- start quick Info -->
	<%@ include file="index/quickInfo.html"%>
	<!-- end quick Info -->
</div>
<!-- end content -->