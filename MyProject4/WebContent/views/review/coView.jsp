<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// 더보기 
let u=0;
$(function(){
	$('.ubtn').click(function(){
		let idk=$(this).attr("data-no");
		$('.updateForm').hide();
		if(u==0)
		{
			$(this).text("취소");
			$('#u'+idk).show();
			u=1;
		}
		else
		{
			$(this).text("수정");
			$('#u'+idk).hide();
			u=0;
		}
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  
  <main class="container clear">
  <h2 class="sectiontitle">리뷰 보기</h2>
    <table class="table">
      <tr >
        <th class="text-center" width=20%>번호</th>
        <td class="text-center" width=80%>${vo.idk }</td>
      </tr>
      <tr>
       <th class="text-center" width=20%>작성일</th>
        <td class="text-center" width=80%>${vo.regdate }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>기업명</th>
        <td class="text-center" width=80%>${vo.coporate_nm }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>한줄평</th>
        <td class="text-center" width=80%>${vo.co_evaluation }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>장점</th>
        <td class="text-center" width=80%>${vo.advantages }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>단점</th>
        <td class="text-center" width=80%>${vo.disadvantages }</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>복지 및 연봉</th>
        <td class="text-center" width=80%>
        <c:choose>
			<c:when test="${vo.welfare_salary_score eq 1}">★☆☆☆☆</c:when>
			<c:when test="${vo.welfare_salary_score eq 2 }">★★☆☆☆</c:when>
			<c:when test="${vo.welfare_salary_score eq 3 }">★★★☆☆</c:when>
			<c:when test="${vo.welfare_salary_score eq 4 }">★★★★☆</c:when>
			<c:when test="${vo.welfare_salary_score eq 5 }">★★★★★</c:when>
			<c:otherwise>☆☆☆☆☆</c:otherwise>
		</c:choose>
		</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>워라벨</th>
        <td class="text-center" width=80%>
        <c:choose>
			<c:when test="${vo.work_life_score eq 1}">★☆☆☆☆</c:when>
			<c:when test="${vo.work_life_score eq 2 }">★★☆☆☆</c:when>
			<c:when test="${vo.work_life_score eq 3 }">★★★☆☆</c:when>
			<c:when test="${vo.work_life_score eq 4 }">★★★★☆</c:when>
			<c:when test="${vo.work_life_score eq 5 }">★★★★★</c:when>
			<c:otherwise>☆☆☆☆☆</c:otherwise>
		</c:choose>
		</td>
      </tr>
       <tr>
        <th class="text-center" width=20%>사내문화</th>
        <td class="text-center" width=80%>
        <c:choose>
			<c:when test="${vo.inhouse_culture_score eq 1}">★☆☆☆☆</c:when>
			<c:when test="${vo.inhouse_culture_score eq 2 }">★★☆☆☆</c:when>
			<c:when test="${vo.inhouse_culture_score eq 3 }">★★★☆☆</c:when>
			<c:when test="${vo.inhouse_culture_score eq 4 }">★★★★☆</c:when>
			<c:when test="${vo.inhouse_culture_score eq 5 }">★★★★★</c:when>
			<c:otherwise>☆☆☆☆☆</c:otherwise>
		</c:choose>
		</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>승진 기회 및 가능성</th>
        <td class="text-center" width=80%>
        <c:choose>
			<c:when test="${vo.opportunities_score eq 1}">★☆☆☆☆</c:when>
			<c:when test="${vo.opportunities_score eq 2 }">★★☆☆☆</c:when>
			<c:when test="${vo.opportunities_score eq 3 }">★★★☆☆</c:when>
			<c:when test="${vo.opportunities_score eq 4 }">★★★★☆</c:when>
			<c:when test="${vo.opportunities_score eq 5 }">★★★★★</c:when>
			<c:otherwise>☆☆☆☆☆</c:otherwise>
		</c:choose>
		</td>
      </tr>
      <tr>
        <th class="text-center" width=20%>경영진 평가</th>
        <td class="text-center" width=80%>
        <c:choose>
			<c:when test="${vo.ceo_score eq 1}">★☆☆☆☆</c:when>
			<c:when test="${vo.ceo_score eq 2 }">★★☆☆☆</c:when>
			<c:when test="${vo.ceo_score eq 3 }">★★★☆☆</c:when>
			<c:when test="${vo.ceo_score eq 4 }">★★★★☆</c:when>
			<c:when test="${vo.ceo_score eq 5 }">★★★★★</c:when>
			<c:otherwise>☆☆☆☆☆</c:otherwise>
		</c:choose>
		</td>
      </tr>
      <tr>
        <td colspan="4" class="text-right">
          <a href="#" class="btn btn-xs btn-success">수정</a>
          <a href="#" class="btn btn-xs btn-info">삭제</a>
          <a href="coList.do" class="btn btn-xs btn-warning">목록</a>
          
        </td>
      </tr>
    </table>
  </main>
</div>
</body>
</html>