package ORB;

import java.util.HashMap;

import org.omg.CORBA.ORB;

import ORG.ConnectionasMap;
import ORG.MapManager;
import TEGApp.TPOA;
import TEGApp.byt;
import Utilities.Serial;

public class TImpl extends TPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	public byt getConnection(String objName, String host, String port) {
		try { return new byt(Serial.serializeElement(MapManager.getConnections(objName, host, port))); 
		} finally {
			if(!objName.equals("DataBase")) {
				String[] s = MapManager.relate(objName);
				
				for(String obj : s) {
					HashMap<String, String> aux = ConnectionasMap.getObj(obj);
					
					if(!(aux == null)) {
						switch(obj) {
							case "Connection":
								for(Object key : aux.keySet()) {
									ClientOrb.getCImpl(ORB.init(ArgsParser.serverInfo(key.toString(), aux.get(key.toString())), null)).updateConnections(objName, host, port);                
								}
								
								break;
							case "Security":
								for(Object key : aux.keySet()) {
									ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(key.toString(), aux.get(key.toString())), null)).updateConnections(objName, host, port);                         
								}
								
								break;
							case "Process":	
								for(Object key : aux.keySet()) {
									ClientOrb.getPImpl(ORB.init(ArgsParser.serverInfo(key.toString(), aux.get(key.toString())), null)).updateConnections(objName, host, port);
								}
								
								break;
							case "Basic":
								for(Object key : aux.keySet()) {
									ClientOrb.getBImpl(ORB.init(ArgsParser.serverInfo(key.toString(), aux.get(key.toString())), null)).updateConnections(objName, host, port);                           
				  				}
								
								break;
							default:
								break;
						}
					}
				}
			}
		}
	}
}