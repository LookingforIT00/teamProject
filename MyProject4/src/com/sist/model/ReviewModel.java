package com.sist.model;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;


@Controller
public class ReviewModel {

	@RequestMapping("review/inList.do")
	public String inList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/review/inList.jsp");

		return "/views/index.jsp";
	}

	
	@RequestMapping("review/coList.do")
	public String coList(HttpServletRequest request, HttpServletResponse response) {
				
		String page = request.getParameter("page");
		if (page == null)
		{
			page = "1";
		}
		
		CoporateReviewDAO dao=CoporateReviewDAO.getInstance();
		
		int curpage=Integer.parseInt(page);
		List<CoporateReviewVO> list=dao.coporateListData(curpage);
		int totalpage=dao.coporateTotalPage();
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("reviewList", list);
		
		
		request.setAttribute("uri", "/views/review/coList.jsp");
		return "/views/index.jsp";
	}
	
	@RequestMapping("review/coInsert.do")
	public String coInsert(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("uri", "/views/review/coInsert.jsp");
		return "/views/index.jsp";
	}
	
	@RequestMapping("review/coInsert_ok.do")
	public String coInsert_ok(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			request.getParameter("UTF-8");
		}catch(Exception e) {}
		
		return "redirect:../views/reviews/coList.jsp";
	}
		
}