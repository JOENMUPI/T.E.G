package ORB;

import TEGApp.*;

import Utilities.DataSet;
import Utilities.Props;
import Utilities.Row;
import Utilities.Serial;

import org.omg.CosNaming.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.HashMap;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import LB.ConnectionsMap;
import LB.LB;
import ORG.ProfileMap;

public class ServerOrb {
	public static void main(String args[]) {
		try {
			Runtime.getRuntime().exec("cmd /C start /wait orbd -ORBInitialPort " + Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			start();
			ORB orb = ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Server")), null); 
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			SImpl SImpl = new SImpl(); 
			SImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("S");
			ncRef.rebind(path, SHelper.narrow(rootpoa.servant_to_reference(SImpl)));
			System.out.println("ServidorOrb Security listo y en espera");
			orb.run();
		} catch (Exception e) { e.printStackTrace(System.out); }
		System.out.println("Adios, cerrando servidor Security");
	}
	
	@SuppressWarnings("unchecked")
	private static void start() {
		try {
			ConnectionsMap.loadConnections((HashMap<String, HashMap<String, String>>)Serial.deserializeElement(ClientOrb.getTImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Traker").getProperty("host"), Props.getPropertiesFile("Connections", "Traker").getProperty("port")), null)).getConnection("Security", InetAddress.getLocalHost().getHostAddress(), Props.getPropertiesFile("Connections", "Server").getProperty("port")).obj));
		} catch (UnknownHostException e) { e.printStackTrace(); }
		
//		DataSet ds = Serial.deserializeDS(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataRequest(new XD("" + new SecureRandom().nextLong(), "security", "loadProfiles", null)).obj);
//		a dormir pequeño xd
//		loadProfiles(ds);
//		ds.first();
//		loadObjects(ds);
//		ds.first();
//		loadMethods(ds);
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
			ProfileMap.setMethod(r.getField("profile_ide").asInteger(), r.getField("object_nam").asString(), r.getField("method_nam").asString(), r.getField("perm").asBoolean());
		}
	}	
}