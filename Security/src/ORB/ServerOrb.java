package ORB;
import TEGApp.*;
import Utilities.ArgsParser;
import Utilities.DataSet;
import Utilities.Props;
import Utilities.Row;
import Utilities.Serial;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import ORG.ProfileMap;

class CtoSImpl extends CtoSPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public boolean test() { return true; }
	public void shutdown() { orb.shutdown(false); }
	public boolean senData(CS data) {
		try { return ProfileMap.getPerm(data.profile, data.ObjectName, data.methodName);  }
		finally {
			if(ProfileMap.getPerm(data.profile, data.ObjectName, data.methodName)) {
				SP sp = new SP();
				sp.infoR = data.infoR;
				sp.methodName = data.methodName;
				sp.ObjectName = data.ObjectName;
				sp.params = data.params;
				sp.typeParams = data.typeParams;
				ClientOrb.getStoPImpl(null).senData(sp);
			}
		}
	}
	
	public void confirmation(String code) {	}
}

public class ServerOrb {
	public static void main(String args[]) {
		try {
			start();
			ORB orb = ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Server")), null); 
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			CtoSImpl CtoSImpl = new CtoSImpl(); 
			CtoSImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("CtoS");
			ncRef.rebind(path, CtoSHelper.narrow(rootpoa.servant_to_reference(CtoSImpl)));
			System.out.println("ServidorOrb C.S listo y en espera");
			orb.run();
		}
		
		catch (Exception e) { System.err.println("Error: " + e); e.printStackTrace(System.out); }
		System.out.println("Adios, cerrando servidor C.S");
	}
	
	private static void start() {
		XD xd = new XD();
		xd.params = null;
		xd.schema = "security";
		xd.queryId = "loadProfiles";
		DataSet ds = Serial.deserializeDS(ClientOrb.getXtoDImpl(null).dataRequest(xd));//recuerda aqui va lo de lb
		loadProfiles(ds);
		ds.first();
		loadObjects(ds);
		ds.first();
		loadMethods(ds);
	}
	
	private static void loadProfiles(DataSet ds) {
		while(ds.hasNext()) {
			Row r = ds.next();
			ProfileMap.setProfile(r.getField("profile_ide").asInteger());
		}
	}
	private static void loadObjects(DataSet ds) {
		while(ds.hasNext()) {
			Row r = ds.next();
			ProfileMap.setObject(r.getField("profile_ide").asInteger(), r.getField("object_nam").asString());
		}
	}
	
	private static void loadMethods(DataSet ds) {
		while(ds.hasNext()) {
			Row r = ds.next();
			ProfileMap.setMethod(r.getField("profile_ide").asInteger(), r.getField("object_nam").asString(), r.getField("object_nam").asString(), r.getField("perm").asBoolean());
		}
	}
	
}