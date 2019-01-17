package LB;// buscar raid5 para las bd

import java.util.HashMap;

public class ConnectionsMap {
	private static HashMap<String,HashMap<String, HashMap<String, TailTime>>> conMap = new HashMap<String,HashMap<String, HashMap<String, TailTime>>>();
	
	public static HashMap<String, HashMap<String, TailTime>> getConnetions(String objectName) { return conMap.get(objectName); }
	public static TailTime getTailTime(String objectName, String host, String port) { return conMap.get(objectName).get(host).get(port); }
	public static void setConnection(String objName, String host, String port) {
		HashMap<String, HashMap<String, TailTime>> values = new HashMap<String, HashMap<String, TailTime>>();
		HashMap<String, TailTime> val =  new HashMap<String, TailTime>();
		
		val.put(port, new TailTime());
		values.put(host, val);
		conMap.put(objName, values);
	}
	
	public static void loadConnections(HashMap<String, HashMap<String, String>> files) {	
		for(String objName : files.keySet()) { loadConnection(objName, files.get(objName)); }
	}
	
	public static void loadConnection(String objName, HashMap<String,String> file) {
		HashMap<String, HashMap<String, TailTime>> values = new HashMap<String, HashMap<String, TailTime>>();
		
		for(String host : file.keySet()) {
			HashMap<String, TailTime> val =  new HashMap<String, TailTime>();
			val.put(file.get(host), new TailTime());
			values.put(host, val);
		}

		conMap.put(objName, values);
	}
	
	public static String[] bestConnetion(String objectName) {
		String[] s = new String[2];
		double d = 999999999;
		
		for(String xx : conMap.get(objectName).keySet()) {
			for(String yy : conMap.get(objectName).get(xx).keySet()) {
				if(d > conMap.get(objectName).get(xx).get(yy).averageTime()) {
					s[0] = xx;
					s[1] = yy;
					d = conMap.get(objectName).get(xx).get(yy).averageTime();
				}
			}
		}
		
		return s;
	}
}