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
		int totalpage=dao.coporateTotalPage();
		List<CoporateReviewVO> list=dao.coporateListData(curpage);

		//List<Integer> scoreList=dao.reviewTotalScore();
		
		//request.setAttribute("scoreList", scoreList);
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
			request.setCharacterEncoding("UTF-8");
		}catch(Exception e) {}
		
		String coporate_nm=request.getParameter("coporate_nm");
		String co_evaluation=request.getParameter("co_evaluation");
		String advantages=request.getParameter("advantages");
		String disadvantages=request.getParameter("disadvantages");
		String career=request.getParameter("career");
		String welfare_salary_score=request.getParameter("welfare_salary_score");
		String work_life_score=request.getParameter("work_life_score");
		String inhouse_culture_score=request.getParameter("inhouse_culture_score");
		String opportunities_score=request.getParameter("opportunities_score");
		String ceo_score=request.getParameter("ceo_score");
		
		CoporateReviewVO vo=new CoporateReviewVO();
		vo.setCoporate_nm(coporate_nm);
		vo.setCo_evaluation(co_evaluation);
		vo.setAdvantages(advantages);
		vo.setDisadvantages(disadvantages);
		vo.setCareer(career);
		vo.setWelfare_salary_score(Integer.parseInt(welfare_salary_score));
		vo.setWork_life_score(Integer.parseInt(work_life_score));
		vo.setInhouse_culture_score(Integer.parseInt(inhouse_culture_score));
		vo.setOpportunities_score(Integer.parseInt(opportunities_score));
		vo.setCeo_score(Integer.parseInt(ceo_score));
		
		CoporateReviewDAO dao=CoporateReviewDAO.getInstance();
		dao.reviewInsert(vo);
		return "redirect:/review/coList.do";
	}
	
	 @RequestMapping("review/detail.do")
	  public String reviewDetail(HttpServletRequest request,HttpServletResponse response)
	  {
		  // 요청한 데이터 받기 (no)
		  String idk=request.getParameter("idk");
		  // FreeBoardDAO연결 => 데이터 얻기를 한다 
		  CoporateReviewDAO dao=CoporateReviewDAO.getInstance();
		  // 메소드 호출 
		  CoporateReviewVO vo=dao.reviewDetailData(Integer.parseInt(idk));
		 
		  request.setAttribute("vo", vo);
		  request.setAttribute("uri", "/views/review/coView.jsp");

		  return "/views/index.jsp";
	  }
	
}