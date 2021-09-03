<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- strat content -->
<div class="bg-gray-100 flex-1 p-6 md:mt-16">
	<!-- congrats & summary -->
	<div class="grid grid-cols-3 lg:grid-cols-1 gap-5">
		<%@ include file="index/congrats.html"%>
		<%@ include file="index/summary.html"%>
	</div>
	<!-- end congrats & summary -->

	<!-- status -->
	<%@ include file="index/status.html"%>
	<!-- end status -->

	<!-- best seller & traffic -->
	<div class="grid grid-cols-2 lg:grid-cols-1 gap-5 mt-5">
		<%@ include file="index/bestSeller.html"%>
		<%@ include file="index/recent_order.html"%>
	</div>
	<!-- end best seller & traffic -->
</div>
<!-- end content -->