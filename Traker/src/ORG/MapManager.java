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
		return ConnectionasMap.getConObjs(objRelations.get(objName).split(","));	
	}
}