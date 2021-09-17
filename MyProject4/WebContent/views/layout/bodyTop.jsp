<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	// 로그인 처리
	$('#logBtn').click(function(){
		let id=$('#log_id').val(); // 사용자가 입력한 값을 읽어 온다 
		if(id.trim()=="")
		{
			$('#log_id').focus();
			return;
		}
		let pwd=$('#log_pwd').val();
		if(pwd.trim()=="")
		{
			$('#log_pwd').focus();
			return;
		}
		// 입력된 경우 데이터를 전송 
		$.ajax({
			type:'post',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			// 결과값 => NOID , NOPWD , OK
			success:function(res)
			{
				let result=res.trim();
				if(result=='NOID') //id가 없는 상태
				{
					alert("아이디가 존재하지 않습니다\n다시 입력하세요");
					$('#log_id').val("");
					$('#log_pwd').val("");
					$('#log_id').focus();
				}
				else if(result=='NOPWD') // 비빌번호가 틀리다
				{
					alert("비밀번호가 틀립니다\n다시 입력하세요");
					$('#log_pwd').val("");
					$('#log_pwd').focus();
				}
				else // 로그인 
				{
					location.href="../main/index.do";
				}
			}
		});
	})
	// 로그아웃 처리
	$('#logoutBtn').click(function(){
		location.href="../member/logout.do";
	})
});
</script>

</head>
<body>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="<%=request.getContextPath()%>/main.do">Looking for IT</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <!-- <li><a class="dropdown-item" href="#!">Settings</a></li>
                <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" href="#!">Logout</a></li> -->
                <c:if test="${sessionScope.id==null }"><%--로그인 안된 상태 --%>
	      <table class="table" style="border:none">
	        <tr class="inline">
	          <td style="border:none">
	            ID:<input type=text name=id id=log_id size=15 class="input-sm">
	          </td>
	          <td style="border:none">
	            Password:<input type=password name=pwd id=log_pwd size=15 class="input-sm">
	          </td>
	          <td style="border:none">
	            <input type=button id="logBtn" value="로그인" class="btn btn-sm btn-danger">
	          </td>
	        </tr>
	      </table>
      </c:if>
      <c:if test="${sessionScope.id!=null }"><%--로그인 된 상태 --%>
	      <table class="table" style="border:none">
	        <tr class="inline">
	          <td style="border:none">
	            ${sessionScope.name }(${sessionScope.admin=='y'?"관리자":"일반유저" })
	          </td>
	          <td style="border:none">
	                     로그인 중입니다!!
	          </td>
	          <td style="border:none">
	            <input type=button id="logoutBtn" value="로그아웃" class="btn btn-sm btn-danger">
	          </td>
	        </tr>
	      </table>
      </c:if>
            </ul>
        </li>
    </ul>
</nav>
</div>

<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
  
    <!-- ################################################################################################ -->
    <ul class="clear">
      <li class="active"><a href="../main/main.do">홈</a></li>
      <li><a class="drop" href="#">회원</a>
        <c:if test="${sessionScope.id==null }"><%--로그인이 안된 상태 --%>
	        <ul>
	          <li><a href="../member/join.do">회원가입</a></li>
	          <li><a href="../member/idfind.do">아이디찾기</a></li>
	          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
	        </ul>
        </c:if>
        <c:if test="${sessionScope.id!=null }"><%--로그인이 된 상태 --%>
	        <ul>
	          <li><a href="../member/join_update.do">회원수정</a></li>
	          <li><a href="../member/join_delete.do">회원탈퇴</a></li>
	        </ul>
        </c:if>
      </li>
</ul>
</nav>
</div>
</body>
</html>