package ORB;
import TEGApp.*;
import Utilities.Props;

import org.omg.CosNaming.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import ORG.PoolManager;
import ORG.PropertiesMap;


public class ServerOrb {
	public static void main(String args[]) {
		try {
			Runtime.getRuntime().exec("cmd /C start /wait orbd -ORBInitialPort " + Props.getPropertiesFile("Connections", "Server").getProperty("port"));
			start();
			ORB orb = ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Server")), null); 
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			DImpl DImpl = new DImpl(); 
			DImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("D");
			ncRef.rebind(path, DHelper.narrow(rootpoa.servant_to_reference(DImpl)));
			System.out.println("ServidorOrb DataBase listo y en espera");
			orb.run();
		} catch (Exception e) { e.printStackTrace(); }
		System.out.println("Adios, cerrando servidor DataBase");
	}
	
	private static void start() throws UnknownHostException {
		ClientOrb.getTImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("Connections", "Traker").getProperty("host"), Props.getPropertiesFile("Connections", "Traker").getProperty("port")), null)).getConnection("DataBase", InetAddress.getLocalHost().getHostAddress(), Props.getPropertiesFile("Connections", "Server").getProperty("port"));                                          
		PoolManager.InitializePool(Props.getPropertiesFile("config", "db"));                                                                                                                                                      
		new PropertiesMap().loadProperties("config", "security", "user", "log", "basic");//que lea un solo propierties tipo relations de traker
	}
}