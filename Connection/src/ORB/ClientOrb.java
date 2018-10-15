package ORB;

import TEGApp.*; 
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class ClientOrb {
	static XtoD XtoDImpl;
	 ORB orb;
	public XtoD getXtoDImpl() { return XtoDImpl; }
	public void iniOrb() { orb = ORB.init(); }
	public void initXtoD() {
		try {
			XtoDImpl = XtoDHelper.narrow(NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService")).resolve_str("XtoD"));
		} 
		catch (Exception e) { System.out.println("Error: " + e); e.printStackTrace(System.out); }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		try {
			ORB orb = ORB.init(args, null);
			XtoDImpl = XtoDHelper.narrow(NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService")).resolve_str("XtoD"));
			boolean x = XtoDImpl.test();
			if(x) { System.out.println("funcax"); XtoDImpl.shutdown(); }
		} 
		catch (Exception e) { System.out.println("Error: " + e); e.printStackTrace(System.out); }
	}
}