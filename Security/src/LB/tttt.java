package LB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class tttt {
	
	public static void main(String[] args) {
		TimerTask tt = new TimerTask() {
			public void run() {
				InetAddress address;
				try { 
					address = InetAddress.getLocalHost();
					System.out.println("d "+InetAddress.getLocalHost().getHostAddress());
					System.out.println("c "+address.getHostAddress());
				} catch (UnknownHostException e) { e.printStackTrace(); }
			}
		};
		
		Timer t = new Timer();
		t.scheduleAtFixedRate(tt, 1000, 2000);
	}
}