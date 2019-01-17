package ORB;

import TEGApp.*; 
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
public class ClientOrb {
	public static S getSImpl(ORB o) {
		try { return SHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("S"));
		} catch (Exception e) { e.printStackTrace(); return null; }
	}
	
	public static D getDImpl(ORB o) {
		try { return DHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("D"));
		} catch (Exception e) { e.printStackTrace(); return null; }
	}
	
	public static T getTImpl(ORB o) {
		try { return THelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("T"));
		} catch (Exception e) { e.printStackTrace(); return null; }
	}
	
	public static B getBImpl(ORB o) {
		try { return BHelper.narrow(NamingContextExtHelper.narrow(o.resolve_initial_references("NameService")).resolve_str("B"));
		} catch (Exception e) { e.printStackTrace(); return null; }	
	}//conexiones a los b.o
}