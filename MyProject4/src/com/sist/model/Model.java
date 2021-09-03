package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class Model {

	@RequestMapping("main.do")
	public String main(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		int idx = 0;
		if(type != null) {
			idx = Integer.parseInt(type);
		}
		switch (idx) {
		case 1:
			request.setAttribute("uri", "index-1.jsp");
			break;
		case 2:
			request.setAttribute("uri", "email.jsp");
			break;
		case 8:
			request.setAttribute("uri", "typography.jsp");
			break;
		case 9:
			request.setAttribute("uri", "alert.jsp");
			break;
		case 10:
			request.setAttribute("uri", "buttons.jsp");
			break;
		case 11:
			request.setAttribute("uri", "content.jsp");
			break;
		default:
			request.setAttribute("uri", "index.jsp");
			break;
		}
		
		return "/WEB-INF/views/main.jsp";
	}
/*
	@RequestMapping("index.do")
	public String index(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/index.jsp";
	}

	@RequestMapping("index-1.do")
	public String index_1(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/index-1.jsp";
	}

	@RequestMapping("email.do")
	public String email(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/email.jsp";
	}

	@RequestMapping("typography.do")
	public String typography(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/typography.jsp";
	}

	@RequestMapping("alert.do")
	public String alert(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/alert.jsp";
	}

	@RequestMapping("buttons.do")
	public String buttons(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/buttons.jsp";
	}

	@RequestMapping("content.do")
	public String blank(HttpServletRequest request, HttpServletResponse response) {

		return "/WEB-INF/views/content.jsp";
	}
*/
}