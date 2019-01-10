package ORB;
import TEGApp.*;
import Utilities.Props;

import org.omg.CosNaming.*;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import ORG.PoolManager;
import ORG.PropertiesMap;


public class ServerOrb {
	public static void main(String args[]) {
		try {
			Runtime.getRuntime().exec("cmd /C start /wait orbd -ORBInitialPort " + Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			start();
			ORB orb = ORB.init(args, null); 
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			DImpl DImpl = new DImpl(); 
			DImpl.setORB(orb);  
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("D");
			ncRef.rebind(path, DHelper.narrow(rootpoa.servant_to_reference(DImpl)));
			System.out.println("ServidorOrb DataBase listo y en espera");
			orb.run();
		} catch (Exception e) { e.printStackTrace(System.out); }
		System.out.println("Adios, cerrando servidor DataBase");
	}
	
	private static void start() {
		PoolManager.InitializePool(Props.getPropertiesFile("config", "db"));
		new PropertiesMap().loadProperties("config", "security");
	}
}