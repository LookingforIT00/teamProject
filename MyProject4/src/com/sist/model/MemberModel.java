package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MemberModel {

	@RequestMapping("member/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/member/login.jsp");

		return "/views/index.jsp";
	}

	@RequestMapping("member/signup.do")
	public String signup(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/member/signup.jsp");

		return "/views/index.jsp";
	}
}