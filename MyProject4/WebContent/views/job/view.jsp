<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>취업 정보</title>
		<style>
			.table > thead > tr > th:first-child {
				width: 80px;
			}
			.table > thead > tr > th:last-child {
				width: 100px;
			}
			.table > thead > tr > th:nth-child(3) {
				width: 150px;
			}
			.table > thead > tr > th:nth-child(4) {
				width: 200px;
			}
		</style>
	</head>
	<body>
		<div id="main">
			<div class="row">
				<div class="col-xs-12">
					<div class="box-content">
						<div class="card-content">
							<table class="table">
								<tbody>
									<tr>
										<td colspan=2>
											<h2>
												${jobVO.jobName}
											</h2>
										</td>
									</tr>
									<tr>
										<th style="width: 20%" class=text-right>학력 조건</th>
										<td style="width: 80%">${jobVO.edu}</td>
									</tr>
									<tr>
										<th style="width: 20%" class=text-right>경력 조건</th>
										<td style="width: 80%">${jobVO.career}</td>
									</tr>
									<c:if test="${not empty jobVO.addr}">
										<tr>
											<th style="width: 20%" class=text-right>주소</th>
											<td style="width: 80%">${jobVO.addr}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.sal}">
										<tr>
											<th style="width: 20%" class=text-right>급여</th>
											<td style="width: 80%">${jobVO.sal}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.empType}">
										<tr>
											<th style="width: 20%" class=text-right>직종</th>
											<td style="width: 80%">${jobVO.empType}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.workType}">
										<tr>
											<th style="width: 20%" class=text-right>근무형태</th>
											<td style="width: 80%">${jobVO.workType}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.workTime}">
										<tr>
											<th style="width: 20%" class=text-right>근무시간</th>
											<td style="width: 80%">${jobVO.workTime}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.welfare}">
										<tr>
											<th style="width: 20%" class=text-right>복지</th>
											<td style="width: 80%">${jobVO.welfare}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.content}">
										<tr>
											<th style="width: 20%" class=text-right>직무내용</th>
											<td style="width: 80%">${jobVO.content}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.startline}">
										<tr>
											<th style="width: 20%" class=text-right>등록일</th>
											<td style="width: 80%">${jobVO.startline}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.deadline}">
										<tr>
											<th style="width: 20%" class=text-right>마감일</th>
											<td style="width: 80%">${jobVO.deadline}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.reception}">
										<tr>
											<th style="width: 20%" class=text-right>지원 방법</th>
											<td style="width: 80%">${jobVO.reception}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.jobType}">
										<tr>
											<th style="width: 20%" class=text-right>모집 직종</th>
											<td style="width: 80%">${jobVO.jobType}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.personnel}">
										<tr>
											<th style="width: 20%" class=text-right>모집 인원</th>
											<td style="width: 80%">${jobVO.personnel}</td>
										</tr>
									</c:if>
									<c:if test="${not empty jobVO.workPlace}">
										<tr>
											<th style="width: 20%" class=text-right>군무 예정지</th>
											<td style="width: 80%">${jobVO.workPlace}</td>
										</tr>
									</c:if>
									<tr>
										<th style="width: 20%" class=text-right>조회 수</th>
										<td style="width: 80%">${jobVO.hit}</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-xs-12">
					<div class="box-content">
						<div class="card-content">
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">회사명</span>
								<span class="col-sm-10">
									${coporateVO.coName}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">업종</span>
								<span class="col-sm-10">
									${coporateVO.coType}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">회사규모</span>
								<span class="col-sm-10">
									${coporateVO.coScale}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">설립년도</span>
								<span class="col-sm-10">
									${coporateVO.coYear}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">연매출액</span>
								<span class="col-sm-10">
									${coporateVO.coSales}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">근로자 수</span>
								<span class="col-sm-10">
									${coporateVO.coWorkers}
								</span>
							</div>
				
							<div class="form-group" style="text-align:center">
								<span class="col-sm-2" style="font-weight: bold">홈페이지</span>
								<span class="col-sm-10">
									<a href="${coporateVO.coLink}">${coporateVO.coLink}</a>
								</span>
							</div>
					
							<div class="text-center" style="margin-top:20px">
								<a class="btn btn-default" href="javascript:coporateVOid(0)" onclick="backwardBoard()">뒤로가기</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			const backwardBoard=()=>{
				window.history.back();
			}
		</script>
	</body>
</html>