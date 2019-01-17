package ORB;

import TEGApp.*; 
import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class ClientOrb {
	public static P getPImpl(ORB o) {
		try { return PHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("P"));
		} catch (Exception e) { e.printStackTrace(System.out); return null; }
	}
	
	public static D getDImpl(ORB o) {
		try { return DHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("D"));
		} catch (Exception e) { e.printStackTrace(System.out); return null; }
	}
	
	public static T getTImpl(ORB o) {
		try { return THelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("T"));
		} catch (Exception e) { e.printStackTrace(System.out); return null; }
	}
}