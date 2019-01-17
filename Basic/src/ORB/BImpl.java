package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

import LB.ConnectionsMap;
import LB.LB;
import ORG.BO;
import TEGApp.BPOA;
import TEGApp.PB;
import TEGApp.XC;
import TEGApp.XL;
import TEGApp.baseS;
import Utilities.Props;
import Utilities.Serial;

public class BImpl extends BPOA {
	private ORB orb;
	private String host;
	private final String objName = "Basic";
	
	public BImpl() {
		super();
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public void confirmation(baseS data, String objectName) { LB.confirmation(objectName, data.ipResponse, data.portResponse, data.id); }
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	
	public XC senData(PB data) {   
		try {
			ClientOrb.getPImpl(LB.getOrb("Process")).confirmation(new baseS(data.response.id, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), objName);;
			Object[] o =  (Object[])Serial.deserializeElement(data.params), aux = new Object[o.length - 2];
			
			for(int i = 2; i < o.length; i++) { aux[i - 2] = o[i]; }
			switch(data.methodName) {
				case "add":    
					return new BO().insert(data.response.id, o[0].toString(), o[1].toString(), Serial.serializeElement(aux));
				case "get":
			    	return new BO().get(data.response.id, o[0].toString(), o[1].toString(), Serial.serializeElement(aux));
			    case "delete":
			    	return new BO().get(data.response.id, o[0].toString(), o[1].toString(), Serial.serializeElement(aux));
			    case "update":
			    	return new BO().get(data.response.id, o[0].toString(), o[1].toString(), Serial.serializeElement(aux));
				default:
					return new XC("Basic-default-boolean", Serial.serializeElement(false));
			} 
		} catch(Exception e) {
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Error", "Internal Error on sendData"));
			return new XC("Basic-Catch-boolean", Serial.serializeElement(false));                                                                           
		}
	}
	
	public void updateConnections(String objName, String host, String port) {
		try { ConnectionsMap.setConnection(objName, host, port);
		} catch(Exception e) { e.printStackTrace(System.out); }
	}
}