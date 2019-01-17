package ORB;

import TEGApp.*; 
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class ClientOrb {
	public static T getTImpl(ORB o) {
		try { return THelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("T"));
		} catch (Exception e) { e.printStackTrace(System.out); return null;}
	}
}