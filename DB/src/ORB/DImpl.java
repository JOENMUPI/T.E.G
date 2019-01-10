package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.ORB;

import ORG.DB;
import TEGApp.DPOA;
import TEGApp.XD;
import TEGApp.XL;
import TEGApp.byt;
import Utilities.Serial;

class DImpl extends DPOA {
	private ORB orb;
	private String host;
	private final String objName = "DataBase";
	
	public DImpl() {
		super();
		try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { this.orb.shutdown(false); }
	public byt dataRequest(XD data) {
		try {
			if(data.params == null) { return new byt(Serial.serializeDS(new DB().query(data.queryId, data.schema))); 
			} else { 
				return new byt(Serial.serializeDS(new DB().query(data.queryId, data.schema, Serial.deserializeParams(data.params)))); 
			}
		} catch(Exception e) {
			this.sendLog(new XL(this.host, this.objName, data.idMsg, "Error", "Internal Error"));
			return null;
		} finally {//ejemplo de retorno de confirmacion a b.o  
			//ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(data.response.ipResponse, data.response.portResponse), null)).confirmation(new baseS(data.response.id, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), this.objName);
		} 
	}
	
	public void sendLog(XL data) {
		try { new DB().query("inserInfo", "log", data.host, data.idMsg, data.infoMsg, data.typeMsg, data.objectName);
		} catch(Exception e) { e.printStackTrace(); }
	}
}