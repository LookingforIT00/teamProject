package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.jsoup.JsoupManager;

@Controller
public class BoardModel {

	@RequestMapping("board/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		

		return "/views/boar/list.jsp";
	}
	
	@RequestMapping("board/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		

		return "/views/boar/view.jsp";
	}
	
	@RequestMapping("board/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		

		return "/views/boar/register.jsp";
	}
	
	@RequestMapping("board/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		

		return "/views/boar/delete.jsp";
	}
	
	@RequestMapping("board/comment.do")
	public String comment(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		

		return "/views/boar/comment.jsp";
	}
}