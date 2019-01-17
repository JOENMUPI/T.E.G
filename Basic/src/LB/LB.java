package LB;

import java.util.LinkedList;

import org.omg.CORBA.ORB;

import ORB.ArgsParser;

public class LB {
	public static LinkedList<TicketTime> tail = new LinkedList<TicketTime>();
	
	public static void createticket(String code) { tail.add(new TicketTime(code)); }
	private static long getTime(String code) {
		for(int i = 0; i < tail.size(); i++) {
			if(tail.get(i).getCode() == code ) { 
				tail.get(i).finalCaptureTime();
				return tail.remove(i).getResult(); 
			}
		}
		
		return 999999999;
	}
	
	public static void confirmation(String objectName, String host, String port, String code) {
		ConnectionsMap.getTailTime(objectName, host, port).setTime(getTime(code));
	}
		
	public static ORB getOrb(String name) {
		String[] s = ConnectionsMap.bestConnetion(name);
		return ORB.init(ArgsParser.serverInfo(s[0], s[1]), null);
	}
}