package co.nz.assurity.test.TMSandboxTesting2.jsonparser;

import org.json.simple.*;
import org.json.simple.parser.*;

public class CategoryReader {
	JSONObject jo;
	
	/**
	 * read in Category JSON object
	 */
	public CategoryReader(String s) throws Exception {
		Object obj = new JSONParser().parse(s);
		
		jo = (JSONObject) obj;
	}
	
	/*
	 * get value for Name field in Category JSON
	 */
	public String getName() throws Exception{
		String name = null;
		
		name = (String) jo.get("Name");
		
		return name;
	}
	
	/*
	 * get value for CanRelist field in Category JSON
	 */
	public Boolean getCanRelist() throws Exception {
		Boolean canRelist = null;
		
		canRelist = (Boolean) jo.get("CanRelist");
		
		return canRelist;
	}
	
	/*
	 * get value for Desc field for promotion 'Gallery' in Category JSON
	 */
	public String getPromoGalleryDesc() throws Exception {
		String promoGalleryDesc = null;
		
		JSONArray ja = (JSONArray) jo.get("Promotions");
		
		for (int i = 0; i<ja.size(); i++) {
			JSONObject obj2 = (JSONObject) ja.get(i);
			String name = (String)obj2.get("Name");
			if (name.equals("Gallery"))
				promoGalleryDesc = (String)obj2.get("Description");
		}
		
		
		return promoGalleryDesc;
	}
	
	
}
