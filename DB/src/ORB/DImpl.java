package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Calendar;

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
			if(data.params == null) { return new byt(Serial.serializeElement(new DB().query(data.queryId, data.schema))); 
			} else { 
				return new byt(Serial.serializeElement(new DB().query(data.queryId, data.schema, (Object[])Serial.deserializeElement(data.params)))); 
			}
		} catch(Exception e) {
			e.printStackTrace();
			this.sendLog(new XL(this.host, this.objName, data.idMsg, "Error", "Internal Error, request"));
			return null;
		} 
	}
	
	public void sendLog(XL data) {
		try { 
			
			new DB().doInsert("insertInfo", "log", new Timestamp(Calendar.getInstance().getTimeInMillis()), data.host, data.idMsg, data.typeMsg, data.infoMsg, data.objectName);
		} catch(Exception e) { e.printStackTrace(); }
	}

	public boolean dataInsert(XD data) {
		try {
			if(data.params == null) {  
				new DB().query(data.queryId, data.schema);
				return true;
			} else { 
				new DB().doInsert(data.queryId, data.schema, (Object[])Serial.deserializeElement(data.params));
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			this.sendLog(new XL(this.host, this.objName, data.idMsg, "Error", "Internal Error, insert"));
			return false;
		} 
	}

	public boolean dataUpdate(XD data) {
		try {
			if(data.params == null) { 
				new DB().update(data.queryId, data.schema);
				return true;
			} else { 
				new DB().update(data.queryId, data.schema, (Object[])Serial.deserializeElement(data.params)); 
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			this.sendLog(new XL(this.host, this.objName, data.idMsg, "Error", "Internal Error, update"));
			return false;
		} 
	}
}