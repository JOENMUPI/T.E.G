package ORB;
import TEGApp.*;
import Utilities.ArgsParser;
import Utilities.ConnectionsMap;
import Utilities.Props;
import Utilities.Serial;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import ORG.ProcessParams;

class StoPImpl extends StoPPOA {
	private ORB orb;
	
	public void setORB(ORB orb_val) { orb = orb_val; }
	public boolean test() { return true; }
	public void shutdown() { orb.shutdown(false); }
	public void senData(SP data) {
		PB pl = new PB();
		pl.infoR = data.infoR;
		pl.methodName = data.methodName;
		pl.params = Serial.serializeParams(ProcessParams.parseParams(data.params, data.typeParams));
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
			StoPImpl StoPImpl = new StoPImpl(); 
			StoPImpl.setORB(orb); 
			NamingContextExt ncRef = NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
			NameComponent path[] = ncRef.to_name("StoP");
			ncRef.rebind(path, CtoSHelper.narrow(rootpoa.servant_to_reference(StoPImpl)));
			System.out.println("ServidorOrb S.P listo y en espera");
			orb.run();
		}
		
		catch (Exception e) { System.err.println("Error: " + e); e.printStackTrace(System.out); }
		System.out.println("Adios, cerrando servidor S.P");
	}
	
	private static void start() {
		new ConnectionsMap().loadProperties("connections", "b.o vbobnbnobn", "n objetos aqui");
	}
}