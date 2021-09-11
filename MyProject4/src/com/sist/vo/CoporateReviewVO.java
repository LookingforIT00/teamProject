package com.sist.vo;
/*
 * IDK               NOT NULL NUMBER       
COPORATE_NM       NOT NULL VARCHAR2(60) 
SCORE             NOT NULL NUMBER(2,1)  
CO_EVALUATION     NOT NULL VARCHAR2(90) 
ADVANTAGES        NOT NULL VARCHAR2(90) 
DISADVANTAGES     NOT NULL VARCHAR2(90) 
JOB               NOT NULL VARCHAR2(90) 
REGDATE           NOT NULL DATE         
EMPLOYMENT        NOT NULL VARCHAR2(60) 
EMPLOYMENT_STATUS NOT NULL VARCHAR2(60) 
AREA              NOT NULL VARCHAR2(60)
 */
import java.util.Date;
public class CoporateReviewVO {
	private int idk;
	private double score;
	private Date regdate;
	private String coporate_nm, co_evaluation, advantages, disadvantages, job, employment, employemnt_status, area;
	public int getIdk() {
		return idk;
	}
	public void setIdk(int idk) {
		this.idk = idk;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
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
	public String getEmployment() {
		return employment;
	}
	public void setEmployment(String employment) {
		this.employment = employment;
	}
	public String getEmployemnt_status() {
		return employemnt_status;
	}
	public void setEmployemnt_status(String employemnt_status) {
		this.employemnt_status = employemnt_status;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
}
