package com.sist.model;

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
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception e) {}
		
		CoporateReviewDAO dao=CoporateReviewDAO.getInstance();
		List<CoporateReviewVO> list=dao.coporateListData();
		request.setAttribute("list", list);
		request.setAttribute("uri", "/views/review/coList.jsp");

		return "/views/index.jsp";
	}
		
}