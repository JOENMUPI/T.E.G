package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {	
	public static Properties getPropertiesFile(String path, String propertiesName) {
		try {
			Properties prop = new Properties(System.getProperties());
			prop.load(new FileInputStream(path + "/" + propertiesName + ".properties"));			
			return prop;
		} catch(IOException e) { e.printStackTrace(); }
		return null;
	}
}