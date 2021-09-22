  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row {
   margin: 0px auto;
   width:500px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
   $('#postBtn').click(function(){
		let dong=$('#dong').val();
		if(dong.trim()=="") 
		{
			$('#dong').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../views/member/postfind.do', 
			data:{"dong":dong},
			success:function(res) 
			{
				$('#print').html(res);
			}
			
		})
   })
	
})
</script>
</head>
<body>
  <div class="container">
    <div class="row">
      <table class="table">
       <tr>
         <td>
         입력:<input type=text name=dong id=dong size=15 class="input-sm">
         <input type=button value="검색" class="btn btn-sm btn-primary" id="postBtn">
         </td>
       </tr>
       <tr>
        <td class="text-right">
          <sup style="color:red">※동/읍/면을 입력하세요</sup>
        </td>
       </tr>
      </table>
      <div id="print"></div>
    </div>
  </div>
</body>
</html>
