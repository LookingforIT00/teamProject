package com.sist.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.CoporateDAO;
import com.sist.dao.JobDAO;
import com.sist.vo.JobVO;
import com.sist.vo.CoporateVO;

public class JsoupManager {

	private static JsoupManager instance;

	public static JsoupManager getInstance() {
		if (instance == null) {
			instance = new JsoupManager();
		}

		return instance;
	}

	public boolean downloadJob() {
		try {
			JobDAO dao = JobDAO.getInstance();
			CoporateDAO coDAO = CoporateDAO.getInstance();

			int page = 1;
			String url = "https://www.work.go.kr/empInfo/empInfoSrch/list/dtlEmpSrchList.do?pageIndex=";
			while (true) {
				Document doc = Jsoup.connect(url + page).get();
				Elements jobName = doc.select("div.cp-info div");
				if (jobName.size() == 0) {
					break;
				} else {
					try {
						Elements edu = doc.select("td:nth-child(3)>div.cp-info>p>em:nth-child(2)");
						Elements career = doc.select("td:nth-child(3)>div.cp-info>p>em:nth-child(1)");
						Elements addr = doc.select("td:nth-child(3)>div.cp-info>p>em:nth-child(3)");
						Elements sal = doc.select("td:nth-child(4)>div.cp-info>p:nth-child(1)");
						Elements workTime = doc.select("td:nth-child(4)>div.cp-info>p:nth-child(4)");
						Elements startline = doc.select("div.cp-info p.dday+p");
						Elements info = doc.select("div.cp-info-in > a ");

						for (int i = 0; i < jobName.size(); i++) {
							String infoUrl = url.substring(0, url.indexOf("/empInfo"));
							infoUrl += info.get(i).attr("href");
							Document infoDoc = Jsoup.connect(infoUrl).get();
							Elements type = infoDoc.select("div.info:nth-child(3)>div:first-child strong");
							String empType = "-";
							String workType = "-";
							for (int j = 0; j < type.size(); j++) {
								if (type.get(j).text().equals("고용형태")) {
									empType = infoDoc.select("div.info:nth-child(3)>div:first-child span").get(j)
											.text();
								} else if (type.get(j).text().equals("근무형태")) {
									workType = infoDoc.select("div.info:nth-child(3)>div:first-child span").get(j)
											.text();
								}
							}
							StringBuffer sb = new StringBuffer();
							Elements welfare_ = infoDoc.select(".careers-table.v1:nth-child(5) td");
							for (int j = 0; j < welfare_.size(); j++) {
								sb.append(welfare_.get(j).text());
								sb.append("^");
							}
							welfare_ = infoDoc.select(".careers-table.v1:nth-child(6) td");
							for (int j = 0; j < welfare_.size(); j++) {
								sb.append(welfare_.get(j).text());
								sb.append("^");
							}
							String welfare = sb.toString();
							sb.setLength(0);
							if (!welfare.isEmpty()) {
								welfare = welfare.substring(0, welfare.lastIndexOf("^"));
							}
							Element content = infoDoc.selectFirst(".careers-table:nth-child(4) tbody tr");
							Element reception = infoDoc
									.selectFirst(".careers-table:nth-child(11) tbody td:nth-child(3)");

							Element coName = infoDoc.selectFirst("div.info li:nth-child(1)>div");
							Element coType = infoDoc.selectFirst("div.info li:nth-child(2)>div");
							Element coScale = infoDoc.selectFirst("div.info li:nth-child(3)>div");
							Element coYear = infoDoc.selectFirst("div.info li:nth-child(4)>div");
							Element coSales = infoDoc.selectFirst("div.info li:nth-child(5)>div");
							Element coLink = infoDoc.selectFirst("div.info li:nth-child(6)>div");
							Element coWorkers = infoDoc.selectFirst("div.info li:nth-child(7)>div");

							CoporateVO coVO = new CoporateVO();
							coVO.setCoName(coName.text());
							coVO.setCoType(coType.text());
							coVO.setCoScale(coScale.text());
							coVO.setCoYear(coYear.text());
							coVO.setCoSales(coSales.text());
							coVO.setCoLink(coLink.text());
							String workers = coWorkers.text();
							if(workers != null) {
								if(workers.contains("명")) {
									workers = workers.substring(0, workers.indexOf("명"));
								}
								workers = workers.trim();
								coVO.setCoWorkers(Integer.parseInt(workers));
							}
							coDAO.insertCoporate(coVO);
							System.out.println(coVO.getCoName());
							System.out.println(coVO.getCoType());
							System.out.println(coVO.getCoScale());
							System.out.println(coVO.getCoYear());
							System.out.println(coVO.getCoSales());
							System.out.println(coVO.getCoLink());
							System.out.println(coVO.getCoWorkers());
							System.out.println("------------------------------------------------");

							JobVO vo = new JobVO();
							vo.setJobName(jobName.get(i).text());
							vo.setEdu(edu.get(i).text());
							vo.setCareer(career.get(i).text());
							vo.setAddr(addr.get(i).text());
							vo.setSal(sal.get(i).text());
							vo.setEmpType(empType);
							vo.setWorkType(workType);
							if(workTime.get(i).text().isEmpty()) {
								vo.setWorkTime("-");
							} else {
								vo.setWorkTime(workTime.get(i).text());
							}
							vo.setWelfare(welfare);
							vo.setContent(content.text());
							String date = startline.get(i).text();
							vo.setStartline(date.substring(0, date.indexOf("등록") + 2));
							if (date.contains("마감")) {
								vo.setDeadline(date.substring(date.indexOf("등록") + 2).trim());
							} else {
								vo.setDeadline("-");
							}
							vo.setReception(reception.text());
							vo.setCoName(coName.text());
							dao.insertJob(vo);
							System.out.println(vo.getJobName());
							System.out.println(vo.getEdu());
							System.out.println(vo.getCareer());
							System.out.println(vo.getAddr());
							System.out.println(vo.getSal());
							System.out.println(vo.getEmpType());
							System.out.println(vo.getWorkType());
							System.out.println(vo.getWorkTime());
							System.out.println(vo.getWelfare());
							System.out.println(vo.getContent());
							System.out.println(vo.getStartline());
							System.out.println(vo.getDeadline());
							System.out.println(vo.getReception());
							System.out.println(vo.getCoName());
							System.out.println("================================================");
						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
				page += 1;
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}
}