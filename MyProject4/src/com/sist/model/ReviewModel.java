package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class ReviewModel {

	@RequestMapping("review/coporateView.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/review/coporateView.jsp");

		return "/views/index.jsp";
	}
}