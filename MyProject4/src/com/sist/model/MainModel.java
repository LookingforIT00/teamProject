package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.jsoup.JsoupManager;

@Controller
public class MainModel {

	@RequestMapping("main.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("uri", "/views/main/view.jsp");

		return "/views/index.jsp";
	}
	

	@RequestMapping("download.do")
	public String download(HttpServletRequest request, HttpServletResponse response) {
		JsoupManager jsoup = JsoupManager.getInstance();
		if (jsoup.downloadJob()) {
			request.setAttribute("message", "다운로드가 완료되었습니다.");
		} else {
			request.setAttribute("message", "다운로드를 실패하셨습니다.");
		}
		request.setAttribute("uri", "/views/main/view.jsp");

		return "/views/index.jsp";
	}
}