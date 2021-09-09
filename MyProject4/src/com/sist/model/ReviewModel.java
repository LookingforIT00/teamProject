package com.sist.model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.controller.*;

@Controller
public class ReviewModel {
	@RequestMapping("review/coReview.do")
	public String coReview(HttpServletRequest request, HttpServletResponse response) {
		
		return "/views/review/coReview_main.jsp";
	}
}
