package ORB;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ArgsParser {
	 public static String[] serverInfo(Properties p) {
		try {
			String[] s = new String[4];
			
			s[0] ="-ORBInitialPort";
			s[1] = p.getProperty("port");
			s[2] = " -ORBInitialHost";
			s[3] = InetAddress.getLocalHost().getHostAddress();
			return s;
		} catch (UnknownHostException e) { e.printStackTrace(); return null; }
	}

	 public static String[] serverInfo(String host, String port) {
			String[] s = new String[4];
			
			s[0] ="-ORBInitialPort";
			s[1] = port;
			s[2] = " -ORBInitialHost";
			s[3] = host;
			return s;
		}
}