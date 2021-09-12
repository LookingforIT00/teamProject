package com.sist.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.BoardDAO;
import com.sist.dao.CommentDAO;
import com.sist.vo.BoardVO;
import com.sist.vo.CommentVO;

@Controller
public class BoardModel {

	@RequestMapping("board/list.do")
	public String boardList(HttpServletRequest request, HttpServletResponse response) {		
		BoardDAO dao = BoardDAO.getInstance();

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
		String page = request.getParameter("page");
		int page_ = 1;
		if(page != null) {
			page_ = Integer.parseInt(page);
		}
		int min = 1+(recordsPerPage*(page_-1));
		int max = recordsPerPage*page_;

		List<BoardVO> boardList = dao.selectBoardList(search, min, max);
		request.setAttribute("boardList", boardList);

		int boardCount = dao.selectBoardCount(search);
		int maxPage = ((boardCount-1) / recordsPerPage) + 1;
		
		int pageSize_ = pageSize / 2;
		int minPage_ = page_ - pageSize_;
		if(minPage_ < 1) {
			minPage_ = 1;
		}
		int maxPage_ = page_ + pageSize_;
		if(maxPage_ > maxPage) {
			maxPage_ = maxPage;
		}
		request.setAttribute("page", page_);
		request.setAttribute("search", search);
		request.setAttribute("minPage", minPage_);
		request.setAttribute("maxPage", maxPage_);

		request.setAttribute("uri", "/views/board/list.jsp");

		return "/views/index.jsp";
	}

	@RequestMapping("board/write.do")
	public String writeBoard(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();

		String idx = request.getParameter("idx");
		BoardVO vo;
		if (idx == null) {
			vo = new BoardVO();
			vo.setWriter("nickname");
		} else {
			vo = dao.selectBoard(Integer.parseInt(idx));
		}
		request.setAttribute("vo", vo);

		request.setAttribute("uri", "/views/board/write.jsp");

		return "/views/index.jsp";
	}

	@RequestMapping("board/viewCount.do")
	public String viewCount(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();

		String idx = request.getParameter("idx");
		if (idx == null) {
			return "redirect:"+request.getContextPath()+"/board/list.do";
		} else {
			dao.viewCount(Integer.parseInt(idx));
		}

		return "redirect:"+request.getContextPath()+"/board/view.do?idx="+idx;
	}

	@RequestMapping("board/view.do")
	public String viewMain(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();

		String idx = request.getParameter("idx");
		BoardVO vo;
		if (idx == null) {
			return "redirect:"+request.getContextPath()+"/board/list.do";
		} else {
			vo = dao.selectBoard(Integer.parseInt(idx));
		}
		request.setAttribute("vo", vo);

		request.setAttribute("uri", "/views/board/view.jsp");

		return "/views/index.jsp";
	}

	@RequestMapping("board/register.do")
	public String registerBoard(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		String idx = request.getParameter("idx");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		if (idx == null) {
			dao.insertBoard(vo);
		} else {
			vo.setIdx(Integer.parseInt(idx));
			dao.updateBoard(vo);
		}
		
		return "redirect:"+request.getContextPath()+"/board/list.do";
	}

	@RequestMapping("board/delete.do")
	public String deleteBoard(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		String idx = request.getParameter("idx");
		if (idx == null) {
			return "redirect:"+request.getContextPath()+"/board/list.do";
		} else {
			dao.deleteBoard(Integer.parseInt(idx));
		}

		return "redirect:"+request.getContextPath()+"/board/list.do";
	}

	@RequestMapping("comment/insert.do")
	public String registerComment(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = CommentDAO.getInstance();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		String targetIdx = request.getParameter("targetIdx");
		String targetType = request.getParameter("targetType");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		CommentVO vo = new CommentVO();
		vo.setTargetIdx(Integer.parseInt(targetIdx));
		vo.setTargetType(targetType);
		vo.setContent(content);
		vo.setWriter(writer);
		dao.insertComment(vo);

		request.setAttribute("send", true);

		return "/views/board/send.jsp";
	}

	@RequestMapping("comment/list.do")
	public String getCommentList(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = CommentDAO.getInstance();

		String targetIdx = request.getParameter("targetIdx");
		String targetType = request.getParameter("targetType");

		List<CommentVO> commentList = dao.selectCommentList(Integer.parseInt(targetIdx), targetType);

		String json = "[";
		for(CommentVO vo : commentList) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("idx", vo.getIdx());
			jsonObj.put("targetIdx", vo.getTargetIdx());
			jsonObj.put("targetType", vo.getTargetType());
			jsonObj.put("content", vo.getContent());
			jsonObj.put("writer", vo.getWriter());
			jsonObj.put("deleteCheck", vo.getDelete_check());
			jsonObj.put("insertTime", vo.getInsertTime().toString());
			if(vo.getUpdateTime() != null) {
				jsonObj.put("updateTime", vo.getUpdateTime().toString());
			}else {
				jsonObj.put("updateTime", null);
			}
			if(vo.getDelete_time() != null) {
				jsonObj.put("deleteTime", vo.getDelete_time().toString());
			}else {
				jsonObj.put("deleteTime", null);
			}
			json += jsonObj.toJSONString();
			json += ",";
		}
		if(json.contains(",")) {
			json = json.substring(0, json.length()-1);
		}
		json += "]";
		request.setAttribute("send", json);

		return "/views/board/send.jsp";
	}

	@RequestMapping("comment/update.do")
	public String updateComment(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = CommentDAO.getInstance();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
		}
		String idx = request.getParameter("idx");
		String content = request.getParameter("content");

		dao.updateComment(Integer.parseInt(idx), content);

		request.setAttribute("send", true);

		return "/views/board/send.jsp";
	}

	@RequestMapping("comment/delete.do")
	public String deleteComment(HttpServletRequest request, HttpServletResponse response) {
		CommentDAO dao = CommentDAO.getInstance();
		
		String idx = request.getParameter("idx");
		if (idx == null) {
			request.setAttribute("send", false);
		} else {
			dao.deleteComment(Integer.parseInt(idx));
			request.setAttribute("send", true);
		}

		return "/views/board/send.jsp";
	}
}