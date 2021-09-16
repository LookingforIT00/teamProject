package com.sist.parsingObject;

import java.util.ArrayList;
import java.util.Map;

public class DutiesObject {
	
	private String duties;
	private ArrayList<String> detailed;
	private Map<String, ArrayList<String>> detailed2;
	
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public ArrayList<String> getDetailed() {
		return detailed;
	}
	public void setDetailed(ArrayList<String> detailed) {
		this.detailed = detailed;
	}
	public Map<String, ArrayList<String>> getDetailed2() {
		return detailed2;
	}
	public void setDetailed2(Map<String, ArrayList<String>> detailed2) {
		this.detailed2 = detailed2;
	}
}