package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.CoporateDAO;
import com.sist.dao.JobDAO;
import com.sist.vo.CoporateVO;
import com.sist.vo.JobVO;

@Controller
public class JobModel {

	@RequestMapping("job/list.do")
	public String jobList(HttpServletRequest request, HttpServletResponse response) {
		JobDAO dao = JobDAO.getInstance();

		int recordsPerPage = 10;
		int pageSize = 5;
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		String search = request.getParameter("search");
		if(search == null) {
			search = "";
		}
		String duties = request.getParameter("duties");
		if(duties == null) {
			duties = "";
		}
		String area = request.getParameter("area");
		if(area == null) {
			area = "";
		}
		String page = request.getParameter("page");
		int page_ = 1;
		if(page != null) {
			page_ = Integer.parseInt(page);
		}

		int jobCount = dao.selectJobCount(search,duties,area);
		if(jobCount > 0) {
			int maxPage = ((jobCount-1) / recordsPerPage) + 1;
			if(page_ < 1) {
				page_ = 1;
			}else if(page_ > maxPage) {
				page_ = maxPage;
			}
			
			int min = 1+(recordsPerPage*(page_-1));
			int max = recordsPerPage*page_;

			List<JobVO> jobList = dao.selectJobList(search,duties,area, min, max);
			request.setAttribute("jobList", jobList);

			int pageSize_ = pageSize / 2;
			int minPage_ = page_ - pageSize_;
			int maxPage_ = page_ + pageSize_;
			if(minPage_ < 1) {
				maxPage_ += 1-minPage_;
				minPage_ = 1;
			}
			if(maxPage_ > maxPage) {
				minPage_ += maxPage-maxPage_;
				maxPage_ = maxPage;
				if(minPage_ < 1) {
					minPage_ = 1;
				}
			}
			request.setAttribute("minPage", minPage_);
			request.setAttribute("maxPage", maxPage_);
		}
		request.setAttribute("page", page_);
		request.setAttribute("search", search);

		request.setAttribute("uri", "/views/job/list.jsp");

		return "/views/index.jsp";
	}

	@RequestMapping("job/hitCount.do")
	public String jobHitCount(HttpServletRequest request, HttpServletResponse response) {
		JobDAO dao = JobDAO.getInstance();

		String idx = request.getParameter("idx");
		if (idx == null) {
			return "redirect:"+request.getContextPath()+"/job/list.do";
		} else {
			dao.hitCount(Integer.parseInt(idx));
		}

		return "redirect:"+request.getContextPath()+"/job/view.do?idx="+idx;
	}

	@RequestMapping("job/view.do")
	public String jobView(HttpServletRequest request, HttpServletResponse response) {
		JobDAO dao = JobDAO.getInstance();

		String idx = request.getParameter("idx");
		JobVO jobVO;
		if (idx == null) {
			return "redirect:"+request.getContextPath()+"/job/list.do";
		} else {
			jobVO = dao.selectJob(Integer.parseInt(idx));
			System.out.println(jobVO.getJobName());
		}
		request.setAttribute("jobVO", jobVO);

		CoporateDAO coporateDAO = CoporateDAO.getInstance();
		CoporateVO coporateVO = coporateDAO.selectCoporate(jobVO.getCoName());
		request.setAttribute("coporateVO", coporateVO);

		request.setAttribute("uri", "/views/job/view.jsp");

		return "/views/index.jsp";
	}
}