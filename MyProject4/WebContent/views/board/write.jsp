<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>게시판 입력</title>
		<style>
			.btn.btn-bordered.btn-xs {
				padding: 4px 15px 4px;
			}
		</style>
	</head>
	<body>
		<div id="main">
			<div class="row">
				<div class="col-xs-12">
					<div class="box-content">
						<div class="card-content">
							<form id="form" class="form-horizontal" action="<%=request.getContextPath()%>/board/register.do"
								method="post" onsubmit="return registerBoard(this)">
								<c:choose>
							  		<c:when test="${vo.idx > 0}">
									<input type="hidden" name="idx" value="${vo.idx}" />
									</c:when>
								  	<c:otherwise>
									</c:otherwise>
								</c:choose>
					
								<div class="form-group">
									<label for="title" class="col-sm-2 control-label">제목</label>
									<div class="col-sm-10">
										<input type="text" id="title" name="title" value="${vo.title}" maxlength="30"
											class="form-control" placeholder="제목을 입력해 주세요." /> <span
											id="valid_title"></span>
									</div>
								</div>
					
								<div class="form-group">
									<label for="content" class="col-sm-2 control-label">내용</label>
									<div class="col-sm-10">
										<textarea id="content" name="content" maxlength="300"
											class="form-control" placeholder="내용을 입력해 주세요.">${vo.content}</textarea>
										<span id="valid_content"></span>
									</div>
								</div>
					
								<div class="form-group">
									<label for="writer" class="col-sm-2 control-label">작성자</label>
									<div class="col-sm-10">
										<input type="text" id="writer" name="writer" value="${vo.writer}" maxlength="10"
											class="form-control" style="background-color:white" readonly />
									</div>
								</div>
					
								<div id="btnDiv" class="text-center" style="margin-top:20px">
									<button type="button"
										class="btn btn-default"
										onclick="backwardBoard()">뒤로가기</button>
									<button type="submit"
										class="btn btn-primary">저장하기</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			const backwardBoard=()=>{
				let idx = "${vo.idx}";
				let form = $("<form></form>");
				if (idx != "") {
					let uri = "<%=request.getContextPath()%>/board/view.do";
					form.attr("action", uri);
					form.attr("method", "post");
					form.appendTo("body");
					let idx_ = $("<input type='hidden' name='idx' value="+idx+" />");
					form.append(idx_);
					form.submit();
				}else{
					let uri = "<%=request.getContextPath()%>/board/list.do";
					location.href = uri;
				}
			}
			
			const registerBoard=(form)=>{
				$("#valid_title").text("");
				$("#valid_content").text("");
				
				let check = true;
				if($("#title").val() === ""){
					$("#valid_title").text("제목을 입력해주세요!");
					check = false;
				}
				if($("#content").val() === ""){
					$("#valid_content").text("내용을 입력해주세요!");
					check = false;
				}

				return check;
			}
		</script>
	</body>
</html>