package Utilities;

import java.util.Properties;

public class ArgsParser {
	public static String[] serverInfo(Properties p) {
		String[] s = new String[3];
		s[0] ="-ORBInitialPort";
		s[1] = p.getProperty("port");
		s[2] = " -ORBInitialHost";
		s[3] = p.getProperty("ip");;
		return s;
	}
}