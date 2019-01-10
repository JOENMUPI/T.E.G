package ORB;

import org.omg.CORBA.ORB;

import ORG.MapManager;
import TEGApp.TPOA;
import TEGApp.byt;
import Utilities.Serial;

public class TImpl extends TPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public void shutdown() { orb.shutdown(false); }
	public byt getConnection(String objName, String host, String port) {
		try { return new byt(Serial.serializeConnections(MapManager.getConnections(objName, host, port))); 
		} finally {// llamar a otods los componentes relacionados para update
			ClientOrb.
		}
	}
}