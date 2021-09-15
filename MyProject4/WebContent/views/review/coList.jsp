<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 게시판</title>
</head>
<body>
	<h1 class="mt-4">회사 후기</h1>
	<div id="main">
	 <table class="table">
	 <tr>
      <td>
      	<c:forEach var="vo" items="${reviewList }">
		<table class="table">
				<tr>
					<th><a href="#">${vo.coporate_nm }</a></th>
				</tr>
				<tr>
					<th>평점</th>
					<td><span style="color:orange">${vo.score }</span></td>
				</tr>	
				<tr>
					<th>한줄평</th>
					<td>${vo.co_evaluation }</td>
				</tr>
				<tr>
					<th>장점</th>
					<td>${vo.advantages }</td>
				</tr>	
				<tr>
					<th>단점</th>
					<td>${vo.disadvantages }</td>
				</tr>		
		</table>
		</c:forEach>
		</td>
		</tr>
	 </table>
	</div>
</body>
</html>