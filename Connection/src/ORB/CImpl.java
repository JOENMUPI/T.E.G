package ORB;

import org.omg.CORBA.ORB;

import LB.ConnectionsMap;
import LB.LB;
import TEGApp.CPOA;
import TEGApp.baseS;

class CImpl extends CPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public boolean test() { return true; }
	public void shutdown() { orb.shutdown(false); }
	public void confirmation(baseS data, String objectName) { LB.confirmation(objectName, data.ipResponse, data.portResponse, data.id); }                                 
	public void updateConnections(String objName, String host, String port) {
		try { ConnectionsMap.setConnection(objName, host, port);
		} catch(Exception e) { e.printStackTrace(System.out); }
	}
}