package ORG;

import java.util.HashMap;
import java.util.Properties;

public class MapManager {
	private static HashMap<String, String> objRelations = new HashMap<String, String>(); 
	
	public static void loadRelations(Properties pro) {
		for(Object obj : pro.keySet()) { objRelations.put(obj.toString(), pro.getProperty(obj.toString())); }
	}
	
	public static HashMap<String, HashMap<String, String>> getConnections(String objName, String host, String port) {
		ConnectionasMap.setConnection(objName, host, port);
		if(objName.equals("DataBase")) { return null; } 
		return ConnectionasMap.getConObjs(objRelations.get(objName).split(","));	
	}
	
	public static String[] relate(String objName) {
		String s = "";
		
		for(Object key : objRelations.keySet()) { if(objRelations.get(key.toString()).contains(objName)) { s += key.toString() + ","; } }
		return s.substring(0, s.length() - 1).split(",");
	}
}