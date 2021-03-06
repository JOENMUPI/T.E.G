package ORG;

import java.util.HashMap;

public class ProfileMap {
	private static HashMap<Integer, HashMap<String, HashMap<String, Boolean>>> profiles = new HashMap<Integer, HashMap<String, HashMap<String, Boolean>>>();;
	
	public static HashMap<String, HashMap<String, Boolean>> getProfile(Integer profile) { return profiles.get(profile); }
	public static void setProfile(Integer profile) {
		if(!checkProfile(profile)) { profiles.put(profile, new HashMap<String, HashMap<String, Boolean>>()); }
	}
	
	public static void setObject(Integer profile, String object) {
		profiles.get(profile).put(object, new HashMap<String, Boolean>());
	}
	
	public static void setMethod(Integer profile, String object, String method, Boolean bool) {
		profiles.get(profile).get(object).put(method, bool);
	}
	
	public static HashMap<String, Boolean> getObject(Integer profile, String object) {
		return profiles.get(profile).get(object);
	}
	
	public static Boolean getPerm(Integer profile, String object, String method) {
		return profiles.get(profile).get(object).get(method);
	}
	
	private static Boolean checkProfile(int i) {
		try{ profiles.get(i); return true; 
		} catch(Exception e) { e.printStackTrace(); return false; }
	}
}