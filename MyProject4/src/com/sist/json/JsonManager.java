package com.sist.json;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sist.parsingObject.AreaObject;
import com.sist.parsingObject.DutiesObject;

public class JsonManager {

	private static JsonManager instance;

	public static JsonManager getInstance() {
		if (instance == null) {
			instance = new JsonManager();
		}

		return instance;
	}

	public ArrayList<AreaObject> areaJson(String path) {
		ArrayList<AreaObject> list = new ArrayList<AreaObject>();
		ArrayList<String> saveName = new ArrayList<String>();
		try {
			JSONArray arr = (JSONArray) new JSONParser().parse(new FileReader(path));
			for (int i = 0; i < arr.size(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				String areaName = (String) tmp.get("area_nm");
				String boroughName = (String) tmp.get("borough_nm");
				if (!saveName.contains(areaName)) {
					int count = list.size();
					list.add(new AreaObject());
					list.get(count).setArea(areaName);
					list.get(count).setBorough(new ArrayList<String>());
					saveName.add(areaName);
				}
				list.get(saveName.indexOf(areaName)).getBorough().add(boroughName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<DutiesObject> dutiesJson(String path) {
		ArrayList<DutiesObject> list = new ArrayList<DutiesObject>();
		ArrayList<String> saveName = new ArrayList<String>();
		ArrayList<String> saveName2 = new ArrayList<String>();
		try {
			JSONArray arr = (JSONArray) new JSONParser().parse(new FileReader(path));
			for (int i = 0; i < arr.size(); i++) {
				JSONObject tmp = (JSONObject) arr.get(i);
				String dutiesName = (String) tmp.get("duties_nm");
				if (!saveName.contains(dutiesName)) {
					int count = list.size();
					list.add(new DutiesObject());
					list.get(count).setDuties(dutiesName);
					list.get(count).setDetailed(new ArrayList<String>());
					list.get(count).setDetailed2(new HashMap<String, ArrayList<String>>());
					saveName.add(dutiesName);
				}
				String detailedName = (String) tmp.get("detailed_nm");
				if (!saveName2.contains(detailedName)) {
					list.get(saveName.indexOf(dutiesName)).getDetailed().add(detailedName);
					list.get(saveName.indexOf(dutiesName)).getDetailed2().put(detailedName, new ArrayList<String>());
					saveName2.add(detailedName);
				}
				if (tmp.containsKey("detailed_nm2")) {
					list.get(saveName.indexOf(dutiesName)).getDetailed2().get(detailedName)
							.add((String) tmp.get("detailed_nm2"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}