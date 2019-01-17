package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

import LB.ConnectionsMap;
import LB.LB;
import ORG.ProcessParams;
import TEGApp.PB;
import TEGApp.PPOA;
import TEGApp.SP;
import TEGApp.XC;
import TEGApp.XL;
import TEGApp.baseS;
import Utilities.Props;
import Utilities.Serial;

class PImpl extends PPOA {
	private ORB orb;
	private String host;
	private final String objName = "Process";
	
	public PImpl() {
		super();
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public void confirmation(baseS data, String objectName) { LB.confirmation(objectName, data.ipResponse, data.portResponse, data.id); }
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	public XC senData(SP data) { 
		try {
			baseS bs = new baseS(data.response.id, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			
			ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(data.response.ipResponse, data.response.portResponse), null)).confirmation(bs, this.objName);
			LB.createticket(data.response.id);
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(this.host, this.objName, data.response.id, "Info", "sendData success"));
			return ClientOrb.getBImpl(LB.getOrb(data.ObjectName)).senData(new PB(bs, data.methodName, Serial.serializeElement(ProcessParams.parseParams(data.params, data.typeParams)))); 
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