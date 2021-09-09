package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class SearchModel {

	@RequestMapping("search/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/search/view.jsp");

		return "/views/index.jsp";
	}
}