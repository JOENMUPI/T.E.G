package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

import LB.LB;
import LB.ConnectionsMap;
import ORG.ProfileMap;
import TEGApp.CS;
import TEGApp.SP;
import TEGApp.SPOA;
import TEGApp.XC;
import TEGApp.XL;
import TEGApp.baseS;
import Utilities.Props;

class SImpl extends SPOA {
	private ORB orb;
	private String host;
	private final String objName = "Security";
	
	public SImpl() {
		super();
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	public void confirmation(baseS data, String objectName) { LB.confirmation(objectName, data.ipResponse, data.portResponse, data.id); }
	public XC senData(CS data) {        
		try {
			if(/*ProfileMap.getPerm(data.profile, data.ObjectName, data.methodName)*/true) {
				try {	
					baseS bs = new baseS(data.response.id, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port"));
					
					ClientOrb.getCImpl(ORB.init(ArgsParser.serverInfo(data.response.ipResponse, data.response.portResponse), null)).confirmation(bs, this.objName);
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Info", "Send msg success"));
					LB.createticket(data.response.id);
					return ClientOrb.getPImpl(LB.getOrb("Process")).senData(new SP(bs, data.ObjectName, data.methodName, data.params, data.typeParams));
				} catch(Exception e) {
					e.printStackTrace();
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Error", "Internal Error on if"));
					return new XC(objName + "-Catch-String","Internal Error on if".getBytes());
				}
			} else {
				ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Denegated", "profile not valid for the operation"));
				return new XC(objName + "-Denegated-String", "profile not valid for the operation".getBytes());
			} 
		} catch(Exception e) { 
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Error", "Internal Error"));
			return new XC(objName + "-Catch-String","Internal Error".getBytes());
		}
	}
	
	public void updateConnections(String objName, String host, String port) {
		try { ConnectionsMap.setConnection(objName, host, port);
		} catch(Exception e) { e.printStackTrace(System.out); }
	}
}