package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class BoardModel {

	@RequestMapping("board/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response) {

		return "/views/board/list.jsp";
	}
	
	@RequestMapping("board/view.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {

		return "/views/board/view.jsp";
	}
	
	@RequestMapping("board/register.do")
	public String register(HttpServletRequest request, HttpServletResponse response) {

		return "/views/board/register.jsp";
	}
	
	@RequestMapping("board/delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) {

		return "/views/board/delete.jsp";
	}
	
	@RequestMapping("board/comment.do")
	public String comment(HttpServletRequest request, HttpServletResponse response) {

		return "/views/board/comment.jsp";
	}
}