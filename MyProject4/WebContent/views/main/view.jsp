<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/plugin/bootstrap/css/bootstrap.min.css" />">
		<script src="<c:url value="/plugin/bootstrap/js/bootstrap.min.js" />"></script>
		<title>메인</title>
		<style type="text/css">
			.monthMove span:first-child {
				float: left;
				width: 33.3%;
				margin: 0px auto;
			}
			.monthMove span:nth-child(2) {
				float: left;
				width: 33.3%;
				margin: 0px auto;
				font-size: 40px;
				text-align: center;
			}
			.monthMove span:last-child {
				float: right;
				width: 33.3%;
				margin: 0px auto;
			}
			.table {
				border-collapse: separate;
				border-spacing: 0;
			}
			.table > tbody > tr > td{width: 14.2%}
			.table > tbody > tr > td.now{
				background-color: #aaffa8;
				font-weight: bold;
			}
			.table > thead > tr > th.sun,
			.table > tbody > tr > td.sun{
				color: red;
			}
			.table > thead > tr > th.sat,
			.table > tbody > tr > td.sat{
				color: blue;
			}
			.table > tbody > tr > td.nan{
				color: gray;
			}
			.table-hover-large > tbody > tr > td {
			    border: solid 1px #000;
			    border-style: none solid solid none;
			    padding: 10px;
			    border-radius: 10px;
				font-size:30px;
			}
			.table-hover-large > tbody > tr > td > p {
				font-size:20px;
			}
			.table-hover-large > tbody > tr > td:hover {
  				background-color: #f5f5f5;
				cursor: pointer;
			}
			.table-hover-large > tbody > tr > td.now:hover {
				background-color: #99dda7;
			}
			.table-hover > tbody > tr > td {
				padding: 3px;
			}
			.table-hover > tbody > tr:hover {
				cursor: pointer;
			}
			.table-hover > tbody > tr:hover > td {
				padding: 0px;
				border: orange 3px;
			    border-style: solid none solid none;
			}
			.table-hover > tbody > tr:hover > td:first-child {
			    border-style: solid none solid solid;
  				border-bottom-left-radius: 100%;
  				border-top-left-radius: 100%;
			}
			.table-hover > tbody > tr:hover > td:last-child {
			    border-style: solid solid solid none;
  				border-bottom-right-radius: 50%;
  				border-top-right-radius: 50%;
			}
			.table-hover {
				float:left;
			}
			#calendar {
				float:bottom;
			}
			#calendar > div {
				width: calc(100% - 390px);
				height: 300px;
				min-width:450px;
			}
			#category > div{
				border: solid 0.5px;
				overflow: auto
			}
			#category > div ul {
				list-style: none;
				padding-left: 0px;
				height: 300px;
			}
			#category > div li {
				border-bottom: solid;
				border-width: 0.5;
				cursor: pointer;
				font-size: 30px;
			}
			#category > div li.active {
				font-weight: bold;
				color: red;
			}
			@media (max-width: 1025px) {
				#calendar > div {
	    			position: Relative;
	    			top: -30px;
				}
			}
		</style>
	</head>
	<body>
		<div id="main">
			<div class="row">
				<div class="col-xs-12">
					<div class="box-content">
						<div class="clearfix">
							<form action="<%=request.getContextPath()%>/job/list.do" method="get" class="form-horizontal" style="margin:0px">
								<div class="input-group">
									<input type="text" id="search" class="form-control" style="height:45px" name="search" placeholder="키워드를 입력해 주세요." />
									<input type="hidden" name="page" value="1" />
									<input type="hidden" id="duties" name="duties" />
									<input type="hidden" id="area" name="area" />
									<div class="input-group-btn">
										<button type="submit" style="border:0px; padding:8px 25px; color:white" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span></button>
									</div>
								</div>
							</form>
							<div id="category" style="margin-bottom:30px; height:300px">
								<div class="col-xs-3">
									<ul id="type0">
										<li onclick="typeClick(this)">직종</li>
										<li onclick="typeClick(this)">지역</li>
									</ul>
								</div>
								<div class="col-xs-3">
									<ul id="type1">

									</ul>
								</div>
								<div class="col-xs-3">
									<ul id="type2">

									</ul>
								</div>
								<div class="col-xs-3">
									<ul id="type3">

									</ul>
								</div>
								<ul class="pager">
									<li id="dutiesPager"></li>
									<li id="areaPager"></li>
								</ul>
							</div>
							<div style="padding-top:40px">
								<span class="monthMove">
									<span>
										<Input type="button" onClick="monthMove(-1)" style="float:right; width:100px" value="◀" />
									</span>
									<span>${month}월</span>
									<span>
										<Input type="button" onClick="monthMove(+1)" style="float:left; width:100px" value="▶"/>
									</span>
								</span>
								<span id="bigCalendar"></span>
							</div>
							<div>
								<span id="smallCalendar"></span>
								<div id="calendar" class="dropdown">
									<div class="dropdown-menu dropdown-menu-right" onmouseover=mouseIn(null,null) onmouseout=mouseOut()>
								      	<ul style="float:left; width:50%; padding:33px">
								          	<li>내용1</li>
								          	<li>내용2</li>
								          	<li>내용3</li>
								          	<li>내용4</li>
								          	<li>내용5</li>
							 	          	<li>내용6</li>
								      	</ul>
								      	<ul style="float:left; width:50%; padding:33px">
								          	<li>내용7</li>
								          	<li>내용8</li>
								          	<li>내용9</li>
								          	<li>내용10</li>
								          	<li>내용11</li>
								          	<li>내용12</li>
								      	</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				let nowYear = ${nowYear};
				let nowMonth = ${nowMonth};
				let nowDay = ${nowDay};
				
				let year = ${year};
				let month = ${month};
				
				let yOptions = "<select id='year' name='year' onchange='formCalendar(this.form)'>";
				for (let year_ = (nowYear - 10); year_ <= (nowYear + 10); year_++) {
					if (year_ === year) {
						yOptions += '<option value='+year_+' selected="selected">'+year_+'</option>';
					} else {
						yOptions += '<option value='+year_+'>'+year_+'</option>';
					}
				}
				yOptions += "</select>년";
				$("#yOptions").html(yOptions);
	
				let mOptions = "<select id='month' name='month' onchange='formCalendar(this.form)'>";
				for (let month_ = 1; month_ <= 12; month_++) {
					if (month_ === month) {
						mOptions += '<option value='+month_+' selected="selected">'+month_+'</option>';
					} else {
						mOptions += '<option value='+month_+'>'+month_+'</option>';
					}
				}
				mOptions += "</select>월";
				$("#mOptions").html(mOptions);
				
				let months = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
				if (Math.floor(year % 4) === 0 && Math.floor(year % 100) != 0 || Math.floor(year % 400) === 0) {
					months[1] = 29;
				}

				let nalsu = (year - 1) * 365 + Math.floor((year - 1) / 4) - Math.floor((year - 1) / 100) + Math.floor((year - 1) / 400);
				for (let i = 0; i < (month - 1); i++) {
					nalsu += months[i];
				}
				nalsu += 1;
	
				let dayOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
				let week = Math.floor(nalsu % dayOfWeek.length);
				let lastDay = months[month - 1];
				let previousMonthLastDay;
				if (month === 1) {
					previousMonthLastDay = months[11];
				} else {
					previousMonthLastDay = months[month - 2];
				}
	
				let bigCalendar = "<table class='table table-hover-large' style='height:700px'><thead><tr>";
				for (let i = 0; i < dayOfWeek.length; i++) {
					if (i === 0) {
						bigCalendar += "<th class='sun'>";
					} else if (i === 6) {
						bigCalendar += "<th class='sat'>";
					} else {
						bigCalendar += "<th>";
					}
					bigCalendar += dayOfWeek[i] + "</th>";
				}
				bigCalendar += "</tr></thead><tbody><tr>";
				for (let i = 1; i <= week; i++) {
					bigCalendar += "<td class='nan'>" + (previousMonthLastDay - week + i)
							+ "<p style='color:black'>내용입니다.</p></td>";
				}
				let weekCount = week;
				for (let i = 1; i <= lastDay; i++, weekCount++) {
					if (Math.floor(weekCount % dayOfWeek.length) === 6) {
						bigCalendar += "<td class='sat ";
					} else if (Math.floor(weekCount % dayOfWeek.length) === 0) {
						weekCount = 0;
						bigCalendar += "<td class='sun ";
					} else {
						bigCalendar += "<td class='";
					}
					if (year === nowYear && month === nowMonth && i === nowDay) {
						bigCalendar += "now'>";
					} else {
						bigCalendar += "'>";
					}
					bigCalendar += i + "<p style='color:black'>내용입니다.</p></td>";
					if (Math.floor(weekCount % dayOfWeek.length) === 6) {
						bigCalendar += "</tr><tr>";
					}
				}
				for (let i = 1; i <= dayOfWeek.length - weekCount; i++) {
					bigCalendar += "<td class='nan'>" + i + "<p style='color:black'>내용입니다.</p></td>";
				}
				bigCalendar += "</tr>";
				$("#bigCalendar").html(bigCalendar);
	
				let date;
				let month_;
				if (month === 1) {
					date = String(year - 1);
					month_ = "12";
				} else {
					date = String(year);
					month_ = String(month - 1);
				}
				if (month_.length <= 1) {
					date += "0";
				}
				date += month_;
	
				let firstDate = date;
				let day = String(previousMonthLastDay - week + 1);
				if (day.length <= 1) {
					firstDate += "0";
				}
				firstDate += day;
	
				date = String(year);
				month_ = String(month);
				if (month_.length <= 1) {
					date += "0";
				}
				date += month_;
	
				let lastDate = date;
				day = String(dayOfWeek.length - week);
				if (day.length <= 1) {
					lastDate += "0";
				}
				lastDate += day;
	
				let smallCalendar = "<table class='table table-hover' style='width:400px;height:300p'><thead><tr>";
				for (let i = 0; i < dayOfWeek.length; i++) {
					if (i === 0) {
						smallCalendar += "<th class='sun'>";
					} else if (i === 6) {
						smallCalendar += "<th class='sat'>";
					} else {
						smallCalendar += "<th>";
					}
					smallCalendar += dayOfWeek[i] + "</th>";
				}
				smallCalendar += "</tr></thead><tbody><tr onmouseover=mouseIn(" + firstDate + "," + lastDate
						+ ") onmouseout=mouseOut()>";
				for (let i = 1; i <= week; i++) {
					smallCalendar += "<td class='nan'>" + (previousMonthLastDay - week + i) + "</td>";
				}
				weekCount = week;
				for (let i = 1; i <= lastDay; i++, weekCount++) {
					if (Math.floor(weekCount % dayOfWeek.length) === 6) {
						smallCalendar += "<td class='sat ";
					} else if (Math.floor(weekCount % dayOfWeek.length) === 0) {
						weekCount = 0;
						smallCalendar += "<td class='sun ";
					} else {
						smallCalendar += "<td class='";
					}
					if (year === nowYear && month === nowMonth && i === nowDay) {
						smallCalendar += "now'>";
					} else {
						smallCalendar += "'>";
					}
					smallCalendar += i + "</td>";
					if (Math.floor(weekCount % dayOfWeek.length) === 6) {
						firstDate = date;
						day = String(i + 1);
						if (day.length <= 1) {
							firstDate += "0";
						}
						firstDate += day;
	
						if (i + dayOfWeek.length > lastDay) {
							if (month === 12) {
								date = String(year + 1);
								month_ = "1";
							} else {
								date = String(year);
								month_ = String(month + 1);
							}
							if (month_.length <= 1) {
								date += "0";
							}
							date += month_;
	
							lastDate = date;
							day = String((i + dayOfWeek.length) - lastDay);
						} else {
							lastDate = date;
							day = String(i + dayOfWeek.length);
						}
						if (day.length <= 1) {
							lastDate += "0";
						}
						lastDate += day;
						smallCalendar += "</tr><tr onmouseover=mouseIn(" + firstDate + "," + lastDate + ") onmouseout=mouseOut()>";
					}
				}
				for (let i = 1; i <= dayOfWeek.length - weekCount; i++) {
					smallCalendar += "<td class='nan'>" + i + "</td>";
				}
				smallCalendar += "</tr></tbody></table>";
				$("#smallCalendar").html(smallCalendar);
			});
		
		   	const formCalendar=(obj)=>{
		      	obj.submit();
		   	}
		   	
			const mouseIn=(firstDate, lastDate)=>{
				$("#calendar").addClass("open");
				if(firstDate != null && lastDate != null){
					let list = $("#calendar").find("li");
					list.each(function() {
						    $(this).text(firstDate + "~" + lastDate);
						});
				}
			}
			
			const mouseOut=()=>{
				$("#calendar").removeClass("open");
			}
			
			const monthMove=(num)=>{
				let year = ${year};
				let month = ${month};
				month += num;
				if(month === 0){
					month = 12;
					year -= 1;
				}else if(month === 13){
					month = 1;
					year += 1;
				}
	
				let uri = "${requestScope['javax.servlet.forward.request_uri']}";
				let form = $("<form></form>");
				form.attr("action", uri);
				form.attr("method", "get");
				form.appendTo("body");
				let year_ = $("<input type='hidden' name='year' value="+year+" />");
				let month_ = $("<input type='hidden' name='month' value="+month+" />");
				form.append(year_);
				form.append(month_);
				form.submit();
			}
			
			let type0, type1, type2;
			let duties = "", area = "";
			const typeClick=(obj)=>{
				$(obj).siblings().removeClass("active");
				$(obj).addClass("active");
				
				let targetId = $(obj).closest("ul").attr("id");
				let next = true;
				if(targetId === "type3"){
					duties = $(obj).text();
					next = false;
				}else {
					if(targetId === "type2"){
						if(type0 === "직종"){
							nextTarget = "type3"
						}else{
							area = $(obj).text();
							next = false;
						}
						type2 = $(obj).text();
					}else{
						if(targetId === "type1"){
							nextTarget = "type2"
							type1 = $(obj).text();
						}else{
							nextTarget = "type1"
							type0 = $(obj).text();
							type1 = "";
							$("#type2").html("");
						}
						type2 = "";
						$("#type3").html("");
						if(type0 === "지역"){
							area = "";
						}
					}
					if(type0 === "직종"){
						duties = "";
					}
				}
				$("#duties").val(duties);
				$("#area").val(area);
				if(duties != ""){
					$("#dutiesPager").html("<a href='#'>"+duties+"</a>");
				}else{
					$("#dutiesPager").html(duties);
				}
				if(area != ""){
					$("#areaPager").html("<a href='#'>"+area+"</a>");
				}else{
					$("#areaPager").html(area);
				}
				
				if(next){
					let uri = "<%=request.getContextPath()%>/main/json.do";
					let type = 
						"type0=" + type0+
						"&type1=" + type1+
						"&type2=" + type2;
					
					$.ajax({
						url : uri,
						type : "GET",
						data : type,
						success : function(response) {
							if(response != ""){
								response = JSON.parse(response);
								let html = "";
								$(response).each(function(index, category) {
									html += `<li onclick="typeClick(this)">`+category.name+`</li>`;
								});
								$("#"+nextTarget).html(html);
							}
						},
						error : function(request, status, error) {
							console.log("code:" + request.status + "\n" + "message:"
									+ request.responseText + "\n" + "error:" + error);
							alert("에러가 발생하였습니다.");
						}
					});
				}
			}
		</script>
	</body>
</html>