package ORB;
import TEGApp.*;
import LB.ConnectionsMap;
import Utilities.Props;
import Utilities.Serial;

import org.omg.CosNaming.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class ServerOrb {
	public static void main(String args[]) {
		try {
			Runtime.getRuntime().exec("cmd /C start /wait orbd -ORBInitialPort " + Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			start();
			ORB orb = ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Server")), null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			BImpl BImpl = new BImpl(); 
			BImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("B");
			ncRef.rebind(path, BHelper.narrow(rootpoa.servant_to_reference(BImpl)));
			System.out.println("ServidorOrb Basic listo y en espera");
			orb.run();
		} catch (Exception e) { e.printStackTrace(System.out); }
		System.out.println("Adios, cerrando servidor Basic");
	}
	
	@SuppressWarnings("unchecked")
	private static void start() {
		try {
			ConnectionsMap.loadConnections(((HashMap<String, HashMap<String, String>>)Serial.deserializeElement(ClientOrb.getTImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Traker").getProperty("host"), Props.getPropertiesFile("Connections", "Traker").getProperty("port")), null)).getConnection("Basic", InetAddress.getLocalHost().getHostAddress(), Props.getPropertiesFile("Connections", "Server").getProperty("port")).obj)));
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
	}
}