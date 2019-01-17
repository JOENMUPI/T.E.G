package ORB;

import TEGApp.*;

import Utilities.Props;

import org.omg.CosNaming.*;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import ORG.MapManager;

public class ServerOrb {
	public static void main(String args[]) {
		try {
			Runtime.getRuntime().exec("cmd /C start /wait orbd -ORBInitialPort " + Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			start();
			ORB orb = ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Server")), null); 
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			TImpl TImpl = new TImpl(); 
			TImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("T");
			ncRef.rebind(path, THelper.narrow(rootpoa.servant_to_reference(TImpl)));
			System.out.println("ServidorOrb Traker listo y en espera");
			orb.run();
		} catch (Exception e) { e.printStackTrace(); }
		System.out.println("Adios, cerrando servidor Traker");
	}
	
	private static void start() {
		MapManager.loadRelations(Props.getPropertiesFile("Connections", "Relations"));
	}
}