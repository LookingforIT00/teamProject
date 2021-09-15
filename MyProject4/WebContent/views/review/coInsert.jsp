<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container-fluid px-4">
		<h1 class="text-center">기업 리뷰 작성</h1>
		<div class="card-content">
			<form action="<%=request.getContextPath()%>/review/insert_ok.do" method="post">
				<table class="table">
					<tr>
						<th width="20%">기업명</th>
						<td width="80%"><input type="text" name="coporate_nm" size=100 class="input-sm"></td>
					</tr>
					<tr>
						<th width="20%">직종</th>
						<td width="80%"><!-- 카테고리 조사후 option으로 바꿀 계획 --></td>
					</tr>
					<tr>
						<th width="20%">고용형태</th>
						<td width="80%"><!-- 카테고리 조사후 option으로 바꿀 계획 --></td>
					</tr>
					<tr>
						<th width="20%">경력</th>
						<td width="80%"><input type="text" name="career" size=100 class="input-sm"></td>
					</tr>
					<tr>
						<th>재직상태</th>
						<td><!-- 카테고리 조사후 option으로 바꿀 계획 --></td>
					</tr>
					<tr>
						<th>근무지역</th>
						<td><!-- 카테고리 조사후 option으로 바꿀 계획 --></td>
					</tr>
					<tr>
						<th>한줄평</th>
						<td><input type="text" name="co_evaluation" size=100 class="input-sm"></td>
					</tr>
					<tr>
						<th>기업의 장점</th>
						<td><input type="text" name="advantages" size=100 class="input-sm"></td>
					</tr>
					<tr>
						<th>기업의 단점</th>
						<td><input type="text" name="disadvantages" size=100 class="input-sm"></td>
					</tr>
				</table>t
			</form>
		</div>
	</div>
</body>
</html>