package ORG;
import java.net.InetAddress;
import java.net.UnknownHostException;

import LB.LB;
import ORB.ClientOrb;
import TEGApp.XC;
import TEGApp.XD;
import TEGApp.XL;
import Utilities.Serial;

public class BO {
	private String host;
	private final String objName = "Basic";
	
	public BO() {
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public XC get(String id, String semaTarget, String queryTarget, byte[] params) {
		try {
    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, objName, id,"Info", "Get success"));
    		return new XC(objName + "-success-boolean", Serial.serializeElement(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataRequest(new XD(id, semaTarget, queryTarget, params))));                                      
    	} catch(Exception e) {
     		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, id, "Error", "Internal error on get"));
    		e.printStackTrace();
    		return new XC(objName + "-Catch-String","internal error on Inventory".getBytes());
    	}
	}
	
	public XC insert(String id, String semaTarget, String queryTarget, byte[] params) {
		try {
    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, objName, id,"Info", "Insert success"));
    		return new XC(objName + "-success-boolean", Serial.serializeElement(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataInsert(new XD(id, semaTarget, queryTarget, params))));                                      
    	} catch(Exception e) {
     		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, id, "Error", "Internal error on inset"));
    		e.printStackTrace();                                                                                                                         
    		return new XC(objName + "-Catch-String","internal error on Inventory".getBytes());
    	}
	}
	
	public XC update(String id, String semaTarget, String queryTarget, byte[] params) {
		try {
    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, objName, id,"Info", "Update success"));
    		return new XC(objName + "-success-boolean", Serial.serializeElement(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataUpdate(new XD(id, semaTarget, queryTarget, params))));                                      
    	} catch(Exception e) {                                                                                                                          
     		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, id, "Error", "Internal error on update"));                      
    		e.printStackTrace();
    		return new XC(objName + "-Catch-String","internal error on Inventory".getBytes());
    	}
	}
}