<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function ok(zip,addr)
{
	parent.joinFrm.post1.value=zip.substring(0,3);
	parent.joinFrm.post2.value=zip.substring(4,7);
	parent.joinFrm.addr1.value=addr;
	parent.Shadowbox.close();
}
</script>
</head>
<body>
  <c:if test="${count==0 }">
    <table class="table">
      <tr>
        <td class="text-center">
          <span style="color:red">검색된 결과가 없습니다</span>
        </td>
      </tr>
    </table>
  </c:if>
  <c:if test="${count!=0 }">
     <table class="table">
       <tr>
         <th class="text-center">우편번호</th>
         <th class="text-center">주소</th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr>
           <td class="text-center">${vo.zipcode }</td>
           <td><a href="javascript:ok('${vo.zipcode }','${vo.address }')">${vo.address }</a></td>
         </tr>
       </c:forEach>
     </table>
  </c:if>
</body>
</html>
