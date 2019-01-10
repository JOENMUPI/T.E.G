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
import TEGApp.byt;
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
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	public XC senData(SP data) {
		try {
			//ejemplo
			baseS bs = new baseS(data.response.id, host, Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			
			ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(data.response.ipResponse, data.response.portResponse), null)).confirmation(bs, this.objName);
			LB.createticket(data.response.id);
			new PB(data.response, data.methodName, Serial.serializeParams(ProcessParams.parseParams(data.params, data.typeParams)));
			return new XC();//aqui la conexion requerida del objeto de negocio con el elemento de arriba 
		} catch(Exception e) { 
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, this.objName, data.response.id, "Error", "Internal Error"));
			return new XC("Process-Catch-String","Internal Error".getBytes()); 
		} finally { 
			ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(data.response.ipResponse, data.response.portResponse), null)).confirmation(new baseS(data.response.id, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), this.objName);
		}
	}
	
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