<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 게시판</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js">

</script>
</head>
<body>
  <div class="container-fluid px-4">
	<h1 class="text-center">회사 후기</h1>
	<div class="card mb-4">
		<div class="card-body">
			<a href="<%=request.getContextPath()%>/review/coInsert.do" class="btn btn-sm btn-danger">작성</a>
		</div>
	</div>
	<div id="main">
	 <table class="table">
	 <tr>
      <td>
      	<c:forEach var="vo" items="${reviewList }">
		<table class="table">
				<tr>
					<th colspan="2"><a href="#">${vo.coporate_nm }</a></th>
				</tr>
				<tr>
					<th width=10%>평점</th>
					<!--<td><span style="color:orange">${vo.score }</span></td>-->
					<td>
						<c:choose>
					 	<c:when test="${vo.score eq 1}">★☆☆☆☆</c:when>
					 	<c:when test="${vo.score eq 2 }">★★☆☆☆</c:when>
					 	<c:when test="${vo.score eq 3 }">★★★☆☆</c:when>
					 	<c:when test="${vo.score eq 4 }">★★★★☆</c:when>
					 	<c:when test="${vo.score eq 5 }">★★★★★</c:when>
					 	<c:otherwise>☆☆☆☆☆</c:otherwise>
					 </c:choose>
					</td>
				</tr>	
				<tr>
					<th width=10%>한줄평</th>
					<td>${vo.co_evaluation }</td>
				</tr>
				<tr>
					<th width=10%>장점</th>
					<td>${vo.advantages }</td>
				</tr>	
				<tr>
					<th width=10%>단점</th>
					<td>${vo.disadvantages }</td>
				</tr>		
		</table>
		</c:forEach>
		</td>
		</tr>
	 </table>
	 <table class="table">
     <tr>
       <td class="text-center">
         <a href="<%=request.getContextPath()%>/review/coList.do?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-primary">이전</a>
          ${curpage } page / ${totalpage } pages
         <a href="<%=request.getContextPath()%>/review/coList.do?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-primary">다음</a>
       </td>
     </tr>
   </table>
	</div>
</div>
</body>
</html>