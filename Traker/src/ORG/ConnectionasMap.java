package ORG;

import java.util.HashMap;

public class ConnectionasMap {
	private static HashMap <String, HashMap<String, String>> conMap = new HashMap<String, HashMap<String, String>>();
	
	public static void setConnection(String objName, String host, String port) {
		if(conMap.get(objName) == null) {
			HashMap<String, String> aux = new HashMap<String, String>();
			
			aux.put(host, port);
			conMap.put(objName, aux);
		} else { conMap.get(objName).put(host, port); }
	}
	
	public static HashMap <String, HashMap<String, String>> getConObjs(String[] objs) {
		HashMap <String, HashMap<String, String>> aux  = new HashMap <String, HashMap<String, String>>();
		
		for(String obj : objs) { aux.put(obj, conMap.get(obj)); }
		return aux;
	}
	
	public static HashMap<String, String> getObj(String objName) { return conMap.get(objName); }
}