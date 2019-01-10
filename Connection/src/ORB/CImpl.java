package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

import LB.ConnectionsMap;
import LB.LB;
import TEGApp.CPOA;
import TEGApp.XL;
import TEGApp.baseS;
import TEGApp.byt;
import Utilities.Serial;

class CImpl extends CPOA {
	private ORB orb;
	private String host;
	private final String objName = "Security";
	
	public CImpl() {
		super();
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public boolean test() { return true; }
	public void shutdown() { orb.shutdown(false); }
	public void confirmation(baseS data, String objectName) { LB.confirmation(objectName, data.ipResponse, data.portResponse, data.id); }
	public void updateConnections(byt files) {
		try {
			ConnectionsMap.loadConnections(Serial.deserializeConnections(files.obj));
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, this.objName, "", "Info", "ConnMapp updated"));
		} catch(Exception e) { 
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, this.objName, "", "Error", "ConnMap erro on update"));
			e.printStackTrace();
		}
	}
}