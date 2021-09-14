package com.sist.vo;
/*
 * IDK                   NOT NULL NUMBER       
WELFARE_SALARY_SCORE  NOT NULL NUMBER(2,1)  
WORK_LIFE_SCORE       NOT NULL NUMBER(2,1)  
INHOUSE_CULTURE_SCORE NOT NULL NUMBER(2,1)  
OPPORTUNITIES_SCORE   NOT NULL NUMBER(2,1)  
CEO_SCORE             NOT NULL NUMBER(2,1)  
CAREER                NOT NULL VARCHAR2(60) 

 */
public class CoporateDetailVO {
	private int idk;
	private double welfare_salary_score, work_like_score, inhouse_culture_score, opportunities_score, ceo_score;
	private String career;
	public int getIdk() {
		return idk;
	}
	public void setIdk(int idk) {
		this.idk = idk;
	}
	public double getWelfare_salary_score() {
		return welfare_salary_score;
	}
	public void setWelfare_salary_score(double welfare_salary_score) {
		this.welfare_salary_score = welfare_salary_score;
	}
	public double getWork_like_score() {
		return work_like_score;
	}
	public void setWork_like_score(double work_like_score) {
		this.work_like_score = work_like_score;
	}
	public double getInhouse_culture_score() {
		return inhouse_culture_score;
	}
	public void setInhouse_culture_score(double inhouse_culture_score) {
		this.inhouse_culture_score = inhouse_culture_score;
	}
	public double getOpportunities_score() {
		return opportunities_score;
	}
	public void setOpportunities_score(double opportunities_score) {
		this.opportunities_score = opportunities_score;
	}
	public double getCeo_score() {
		return ceo_score;
	}
	public void setCeo_score(double ceo_score) {
		this.ceo_score = ceo_score;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	
	
}
