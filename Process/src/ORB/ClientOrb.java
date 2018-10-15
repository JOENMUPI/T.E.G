package ORB;

import TEGApp.*; 
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class ClientOrb {
	public static XtoC getXtoCImpl(ORB o) {
		try {
			return XtoCHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("XtoC"));
		} 
		catch (Exception e) { System.out.println("Error: " + e); e.printStackTrace(System.out); return null; }
	}
}