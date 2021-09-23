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
			<form action="<%=request.getContextPath()%>/review/coInsert_ok.do" method="post">
				<table class="table">
					<tr>
						<th width="20%">기업명</th>
						<td width="80%"><input type="text" name="coporate_nm" size=100 class="input-sm"></td>
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
					<tr>
						<th width="20%">경력</th>
						<td width="80%"><input type="text" name="career" size=20 class="input-sm"></td>
					</tr>
					<!-- 별점 부분 -->
					
					<tr>
						<th>복지 및 급여</th>
						<td><input type="text" name="welfare_salary_score" size=10 class="input-sm"></td>
					</tr>
					<tr>
						<th>업무와 삶의 균형</th>
						<td><input type="text" name="work_life_score" size=10 class="input-sm"></td>
					</tr>
					<tr>
						<th>사내문화</th>
						<td><input type="text" name="inhouse_culture_score" size=10 class="input-sm"></td>
					</tr>
					<tr>
						<th>승진 기회 및 가능성</th>
						<td><input type="text" name="opportunities_score" size=10 class="input-sm"></td>
					</tr>
					<tr>
						<th>경영진 평가</th>
						<td><input type="text" name="ceo_score" size=10 class="input-sm"></td>
					</tr>
					<tr>
      				  <td class="text-center" colspan="2">
       					<input type=submit value="글쓰기" class="btn btn-sm btn-danger">
        				<input type=button value="취소" class="btn btn-sm btn-success" onclick="javascript:history.back()">
       				  </td>
      				</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>