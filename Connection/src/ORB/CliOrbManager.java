package ORB;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExtHelper;

import TEGApp.CtoS;
import TEGApp.CtoSHelper;

public class CliOrbManager {
	static CtoS CtoSImpl;
	private ORB orb;
	
	public void initOrb(String args[]) { this.orb = ORB.init(args, null); }
	public void initOrb() { this.orb = ORB.init(); }	
	public void CtoSInit() {
		try {
			CtoSImpl = CtoSHelper.narrow(NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService")).resolve_str("CtoS"));
		} 
		catch (Exception e) { System.out.println("Error: " + e); e.printStackTrace(System.out); }
	}	
}