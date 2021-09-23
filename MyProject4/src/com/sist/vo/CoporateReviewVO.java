package com.sist.vo;
/*
 * IDK                   NOT NULL NUMBER        
COPORATE_NM           NOT NULL VARCHAR2(100) 
CO_EVALUATION         NOT NULL VARCHAR2(100) 
ADVANTAGES            NOT NULL VARCHAR2(100) 
DISADVANTAGES         NOT NULL VARCHAR2(100) 
JOB                            VARCHAR2(100) 
REGDATE               NOT NULL DATE          
EMPLOYMENT                     VARCHAR2(100) 
EMPLOYMENT_STATUS              VARCHAR2(100) 
AREA                           VARCHAR2(100) 
WELFARE_SALARY_SCORE  NOT NULL NUMBER        
WORK_LIFE_SCORE       NOT NULL NUMBER        
INHOUSE_CULTURE_SCORE NOT NULL NUMBER        
OPPORTUNITIES_SCORE   NOT NULL NUMBER        
CEO_SCORE             NOT NULL NUMBER        
CAREER                NOT NULL VARCHAR2(50)  

 */
import java.util.Date;
public class CoporateReviewVO {                                                
	private int idk,welfare_salary_score, work_life_score, inhouse_culture_score, opportunities_score, ceo_score;
	private String coporate_nm,co_evaluation, advantages, disadvantages, job, empolyment, employment_status, area, career;
	private Date regdate;
	public int getIdk() {
		return idk;
	}
	public void setIdk(int idk) {
		this.idk = idk;
	}
	public int getWelfare_salary_score() {
		return welfare_salary_score;
	}
	public void setWelfare_salary_score(int welfare_salary_score) {
		this.welfare_salary_score = welfare_salary_score;
	}
	public int getWork_life_score() {
		return work_life_score;
	}
	public void setWork_life_score(int work_life_score) {
		this.work_life_score = work_life_score;
	}
	public int getInhouse_culture_score() {
		return inhouse_culture_score;
	}
	public void setInhouse_culture_score(int inhouse_culture_score) {
		this.inhouse_culture_score = inhouse_culture_score;
	}
	public int getOpportunities_score() {
		return opportunities_score;
	}
	public void setOpportunities_score(int opportunities_score) {
		this.opportunities_score = opportunities_score;
	}
	public int getCeo_score() {
		return ceo_score;
	}
	public void setCeo_score(int ceo_score) {
		this.ceo_score = ceo_score;
	}
	public String getCo_evaluation() {
		return co_evaluation;
	}
	public void setCo_evaluation(String co_evaluation) {
		this.co_evaluation = co_evaluation;
	}
	public String getAdvantages() {
		return advantages;
	}
	public void setAdvantages(String advantages) {
		this.advantages = advantages;
	}
	public String getDisadvantages() {
		return disadvantages;
	}
	public void setDisadvantages(String disadvantages) {
		this.disadvantages = disadvantages;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEmpolyment() {
		return empolyment;
	}
	public void setEmpolyment(String empolyment) {
		this.empolyment = empolyment;
	}
	public String getEmployment_status() {
		return employment_status;
	}
	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getCoporate_nm() {
		return coporate_nm;
	}
	public void setCoporate_nm(String coporate_nm) {
		this.coporate_nm = coporate_nm;
	}
	
	
}
