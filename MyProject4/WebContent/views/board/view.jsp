<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
	<head>
		<title>게시판</title>
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
			.table > tbody > tr.listInner{
				cursor: pointer;
			}
		</style>
	</head>
	<body>
		<div id="commentModal" class="modal fade" tabindex = "-1" role = "dialog" aria-labelledby = "myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header" style="padding:0px; margin-right:15px">
						<button type="button" class="close" data-dismiss="modal"
							aria-label = "Close">
							<span style="font-size:50px">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">
								<label class="col-form-label">작성자</label>
								<input id="modalWriter" class="form-control" style="background-color:white" readonly />
							</div>
							<div class="form-group">
								<label for = "modalContent" class="col-form-label">내용</label>
								<textarea id="modalContent" class="form-control"
									placeholder = "내용을 입력해 주세요."></textarea>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnCommentUpdate"
							class="btn btn-primary">수정하기</button>
						<button type="button" id="btnCommentDelete"
							class="btn btn-danger">삭제하기</button>
					</div>
				</div>
			</div>
		</div>
		
		<div id="main">
			<div class="row">
				<div class="col-xs-12">
					<div class="box-content">
						<div class="card-content">
							<form class="form-horizontal form-view">
								<div class="form-group">
									<label for = "inp-type-1" class="col-sm-2 control-label">제목</label>
									<div class="col-sm-10">
										<input class="form-control" value = "${vo.title}" style="background-color:white" readonly />
									</div>
								</div>
					
								<div class="form-group">
									<label for = "inp-type-5" class="col-sm-2 control-label">내용</label>
									<div class="col-sm-10">
										<textarea class="form-control" style="background-color:white" readonly>${vo.content}</textarea>
									</div>
								</div>
					
								<div class="form-group">
									<label for = "inp-type-2" class="col-sm-2 control-label">작성자</label>
									<div class="col-sm-10">
										<input class="form-control" value = "${vo.writer}" style="background-color:white" readonly />
									</div>
								</div>
					
								<div class="form-group">
									<label for = "inp-type-5" class="col-sm-2 control-label">등록일</label>
									<div class="col-sm-10">
										<fmt:parseDate value="${vo.insertTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
										<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd" var="dateTime" />
										<input class="form-control"
											value = "${dateTime}" style="background-color:white" readonly />
									</div>
								</div>
					
								<div class="form-group">
									<label for = "inp-type-5" class="col-sm-2 control-label">조회 수</label>
									<div class="col-sm-10">
										<input class="form-control" value = "${vo.viewCount}" style="background-color:white" readonly />
									</div>
								</div>
							</form>
					
							<div class="text-center">
								<a class="btn btn-default" href="<%=request.getContextPath()%>/board/list.do">뒤로가기</a>
								<span>
									<button type="button"
										class="btn btn-primary"
										onclick = "writeBoard(${vo.idx})">수정하기</button>
									<button type="button"
										class="btn btn-danger"
										onclick = "deleteBoard(${vo.idx})">삭제하기</button>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="box-content">
				<div class="card-content">
					<div class="clearfix">
						<h3 class="pull-left">댓글</h3>
					</div>
					<form class="form-horizontal form-view">
						<div class="input-group" style="margin-bottom:20px">
							<input type="text" id="content" class="form-control" maxlength = "50" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick = "insertComment(${vo.idx}, 'board')">
									<i class="glyphicon glyphicon-send"></i>
								</button>
							</div>
						</div>
						<ul class="notice-list"></ul>
					</form>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				printCommentList();
			});
			
			const viewScroll=(selector)=>{
				let location = $("#"+selector).offset().top;
				$("html").animate({scrollTop : location}, 400);
			}
			
			const writeBoard=(idx)=>{
				let uri = "<%=request.getContextPath()%>/board/write.do";
				let form = $("<form></form>");
				form.attr("action", uri);
				form.attr("method", "post");
				form.appendTo("body");
				let idx_ = $("<input type='hidden' name='idx' value = "+idx+" />");
				form.append(idx_);
				form.submit();
			}
	
			const deleteBoard=(idx)=>{
				if (confirm(idx + "번 게시글을 삭제할까요?")) {
					let uri = "<%=request.getContextPath()%>/board/delete.do";
					let form = $("<form></form>");
					form.attr("action", uri);
					form.attr("method", "post");
					form.appendTo("body");
					let idx_ = $("<input type='hidden' name='idx' value = "+idx+" />");
					form.append(idx_);
					form.submit();
				}
			}
	
			$("#modalContent").on("keyup", function() {
				if($(this).val().length > 50) {
					$(this).val($(this).val().substring(0, 50));
				}
			});
	
			const openModal=(idx, writer, content)=>{
				$("#commentModal").modal("toggle");
	
				$("#modalWriter").val(writer);
				$("#modalContent").val(content);
	
				$("#btnCommentUpdate").attr("onclick", "updateComment("+ idx +")");
				$("#btnCommentDelete").attr("onclick", "deleteComment("+ idx +")");
			}
	
			let openInputIdx = -1;
			const openInput=(idx)=>{
				if(openInputIdx >= 0){
					$("span#comment"+openInputIdx).html("");
				}
				if(openInputIdx != idx){
					$("#comment"+idx).html(`
							<div class="input-group">
								<input type="text" id="commentInput" class="form-control" maxlength = "50" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" style="margin:0px auto; padding:0px" onclick = "insertComment(`+idx+`,'comment')">
										<i class="glyphicon glyphicon-send"></i>
									</button>
			
							</div>
							`);
					$("#commentInput").focus();
					openInputIdx = idx;
				}else{
					openInputIdx = -1;
				}
			}
	
			const insertComment=(idx,type)=>{
				let content;
				if(type === "comment"){
					content = $("#commentInput");
				}else{
					content = $("#content");
				}
				if (content.val() === "") {
					content.attr("placeholder", "댓글을 입력해 주세요.");
					content.focus();
					return false;
				}
				let uri = "<%=request.getContextPath()%>/comment/insert.do";
				let headers = {};
				headers["X-HTTP-Method-Override"] = "POST";
				let commentVO = 
					"targetIdx=" + idx+
					"&targetType=" + type+
					"&content=" + content.val()+
					"&writer=" + "nickname";
	
				$.ajax({
					url : uri,
					type : "POST",
					headers : headers,
					data : commentVO,
					success : function(response) {
						if (!response) {
							alert("댓글 등록에 실패하였습니다.");
							return false;
						}
	
						printCommentList();
						content.val("");
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:" + error);
						alert("에러가 발생하였습니다.");
					}
				});
			}
	
			const updateComment=(idx)=>{
				let content = $("#modalContent");
				if (content.val() === "") {
					content.focus();
					return false;
				}
	
				let uri = "<%=request.getContextPath()%>/comment/update.do";
				let headers = {};
				headers["X-HTTP-Method-Override"] = "POST";
				let commentVO = 
					"idx="+ idx+
					"&content="+ content.val();
	
				$.ajax({
					url : uri,
					type : "POST",
					headers : headers,
					data : commentVO,
					success : function(response) {
						if (!response) {
							alert("댓글 수정에 실패하였습니다.");
							
							return false;
						}
			
						printCommentList();
						$("#commentModal").modal("hide");
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:" + error);
						alert("에러가 발생하였습니다.");
					}
				});
			}
	
			const deleteComment=(idx)=>{
				if (!confirm("댓글을 삭제하시겠어요?"))
					return false;
	
				let uri = "<%=request.getContextPath()%>/comment/delete.do";
				uri += "?idx="+idx;
				let headers = {};
				headers["X-HTTP-Method-Override"] = "POST";
	
				$.ajax({
					url : uri,
					type : "POST",
					headers : headers,
					success : function(response) {
						if (!response) {
							alert("댓글 삭제에 실패하였습니다.");
							
							return false;
						}
	
						printCommentList();
						$("#commentModal").modal("hide");
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:" + error);
						alert("에러가 발생하였습니다.");
					}
				});
			}
	
			const printCommentList=()=>{
				let uri = "<%=request.getContextPath()%>/comment/list.do";
				uri += "?targetIdx=${vo.idx}";
				uri += "&targetType=board";
				$.get(uri, function(response) {
					if(response != ""){
						response = JSON.parse(response);
						let commentHtml = "";
							$(response).each(function(index, comment) {
								commentHtml += getComment(null, comment, 0);
							});
						$(".notice-list").html(commentHtml);
					}
				});
			}
	
			const getComment=(parent, comment, depth)=>{
				let auth = (comment.deleteCheck === "N");
				let commentHtml = "";
				if(depth >= 1){
					if(auth){
						commentHtml += `<li id="`+(comment.idx+comment.writer)+`" style="min-height:110px; padding-left:`+(depth<7?depth*15:7*15)+`px">`;
					}else{
						commentHtml += `<li id="`+(comment.idx+comment.writer)+`" style="padding-left:`+(depth<7?depth*15:7*15)+`px">`;
					}
					let color = ["blue", "green", "red", "black"];
					commentHtml += `<img src="<%=request.getContextPath()%>/images/re_icon.png" style="float:left; filter:opacity(0.5) drop-shadow(0 0 0 `+color[(depth-1) % color.length]+`);">`;
				}else{
					if(auth){
						commentHtml += `<li id="`+(comment.idx+comment.writer)+`" style="min-height:110px">`;
					}else{
						commentHtml += `<li id="`+(comment.idx+comment.writer)+`">`;
					}
				}
				if(depth >= 1){
					commentHtml += `<span class="name" style="padding-right:24px"><a href="javascript:void(0)" onclick="viewScroll('`+(parent.idx+parent.writer)+`')" style="color:gray">to:`+(parent.writer)+`</a>`+comment.writer+`</span>
					<span class="content" style="padding-left:24px">`+(comment.deleteCheck === "N" ? comment.content : "삭제한 내용입니다.")+`</span>`;
				}else{
					commentHtml += `<span class="name" style="padding-right:70px">`+(comment.writer)+`</span>
					<span class="content">`+(comment.deleteCheck === "N" ? comment.content : "삭제한 내용입니다.")+`</span>`;
				}
				commentHtml += `<span class="time">`+(comment.deleteCheck === "N" ? comment.updateTime!=null ? "수정:"+getTimeStamp(comment.updateTime) : getTimeStamp(comment.insertTime) : "삭제:"+getTimeStamp(comment.deleteTime))+`</span>`;
				if(auth){
					commentHtml += `<button type="button" onclick = "openModal('`+comment.idx+`', '`+comment.writer+`', '`+comment.content+`')" class="btn btn-primary" style="width:50px; height:25px; position:absolute; right:15px; top:70px; padding:0px">수정</button>`;
				}
				if(comment.deleteCheck === "N"){
					commentHtml += `<button type="button" onclick = "openInput(`+comment.idx+`)" class="btn btn-success" style="width:50px; height:25px; position:absolute; right:15px; top:38px; padding:0px">답글</button>`;
				}
				commentHtml += "</li>";
				commentHtml += `
				<span id="comment`+comment.idx+`">
				</span>`;
				
				let uri = "<%=request.getContextPath()%>/comment/list.do";
				uri += "?targetIdx="+comment.idx;
				uri += "&targetType=comment";
				
				$.ajax({
					url : uri,
					type : "GET",
					async : false,
					success : function(response) {
						if(response != ""){
							response = JSON.parse(response);
							parent = {
									idx: comment.idx,
									writer: comment.writer
							};
							$(response).each(function(index, comment) {
								commentHtml += getComment(parent, comment, depth+1);
							});
						}
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:" + error);
						alert("에러가 발생하였습니다.");
					}
				});
				
				return commentHtml; 
			}
	
			const getTimeStamp=function(date) {
				let data = new Date(date);
				let time =
					leadingZeros(data.getFullYear().toString().substring(2,4), 2) + "." +
					leadingZeros(data.getMonth() + 1, 2) + "." +
					leadingZeros(data.getDate(), 2) + " " +
					
					leadingZeros(data.getHours(), 2) + ":" +
					leadingZeros(data.getMinutes(), 2);
	
				return time;
			}
	
			const leadingZeros=function(n, digits) {
				let zero = "";
				n = n.toString();
	
				if (n.length < digits) {
					for (i = 0; i < digits - n.length; i++)
						zero += "0";
				}
				return zero + n;
			}
		</script>
	</body>
</html>