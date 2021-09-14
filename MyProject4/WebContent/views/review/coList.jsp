<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 게시판</title>
<style type="text/css">
</style>
</head>
<body>
	<main>
		<div class="container-fluid px-4">
			<h1 class="mt-4">기업 리뷰 리스트</h1>
			<ol class="breadcrumb mb-4">
				<li class="breadcrumb-item">검색창 만들 예정</li>
			</ol>
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table me-1"></i> DataTable Example
				</div>
				<div class="card-body">
					<table id="datatablesSimple">
						<tr>
							<th><a href="#">${vo.coporate_nm }</a></th>
							<td>평점</td><td>${vo.score }</td>
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
				</div>
			</div>
		</div>
	</main>
</body>
</html>