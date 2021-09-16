package com.sist.model;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.json.JsonManager;
import com.sist.jsoup.JsoupManager;
import com.sist.parsingObject.AreaObject;
import com.sist.parsingObject.DutiesObject;

@Controller
public class MainModel {

	@RequestMapping("main.do")
	public String view(HttpServletRequest request, HttpServletResponse response) {
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		
		Calendar cal = Calendar.getInstance();
		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		int nowDay = cal.get(Calendar.DAY_OF_MONTH);
		request.setAttribute("nowYear", nowYear);
		request.setAttribute("nowMonth", nowMonth);
		request.setAttribute("nowDay", nowDay);

		int year_;
		int month_;
		if (year != null || month != null) {
			year_ = Integer.parseInt(year);
			month_ = Integer.parseInt(month);
		} else {
			year_ = nowYear;
			month_ = nowMonth;
		}
		request.setAttribute("year", year_);
		request.setAttribute("month", month_);
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

	@RequestMapping("main/json.do")
	public String getCommentList(HttpServletRequest request, HttpServletResponse response) {
		JsonManager json = JsonManager.getInstance();

		String type0 = request.getParameter("type0");
		String type1 = request.getParameter("type1");
		String type2 = request.getParameter("type2");
		
		String jsonStr = "[";
		if(type0.equals("지역")) {
			List<AreaObject> areaList = json.areaJson(Paths.get(request.getRealPath("json"), "area.json").toString());
			for(AreaObject vo : areaList) {
				if(type1.isEmpty()) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("name", vo.getArea());
					jsonStr += jsonObj.toJSONString();
					jsonStr += ",";
				}else {
					if(vo.getArea().equals(type1)) {
						for(String st : vo.getBorough()) {
							JSONObject jsonObj = new JSONObject();
							jsonObj.put("name", st);
							jsonStr += jsonObj.toJSONString();
							jsonStr += ",";
						}
						break;
					}
				}
			}
		}else if(type0.equals("직종")) {
			List<DutiesObject> dutiesList = json.dutiesJson(Paths.get(request.getRealPath("json"), "duties.json").toString());
			for(DutiesObject vo : dutiesList) {
				if(type1.isEmpty()) {
					JSONObject jsonObj = new JSONObject();
					jsonObj.put("name", vo.getDuties());
					jsonStr += jsonObj.toJSONString();
					jsonStr += ",";
				}else {
					if(vo.getDuties().equals(type1)) {
						for(String st : vo.getDetailed()) {
							if(type2.isEmpty()) {
								JSONObject jsonObj = new JSONObject();
								jsonObj.put("name", st);
								jsonStr += jsonObj.toJSONString();
								jsonStr += ",";
							}else if(st.equals(type2)) {
								for(String str : vo.getDetailed2().get(st)) {
									JSONObject jsonObj = new JSONObject();
									jsonObj.put("name", str);
									jsonStr += jsonObj.toJSONString();
									jsonStr += ",";
								}
								break;
							}
						}
						break;
					}
				}
			}
		}
		if(jsonStr.contains(",")) {
			jsonStr = jsonStr.substring(0, jsonStr.length()-1);
		}
		jsonStr += "]";
		request.setAttribute("send", jsonStr);

		return "/views/board/send.jsp";
	}
}