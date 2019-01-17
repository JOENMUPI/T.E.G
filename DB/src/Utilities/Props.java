package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {	
	public static Properties getPropertiesFile(String path, String propertiesName) {
		try {
			Properties prop = new Properties(System.getProperties());//recuerda que user.dir vas afuera de esta clase para generalizarla mas aun
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/" + path + "/" + propertiesName + ".properties"));			
			return prop;
		} catch(IOException e) { e.printStackTrace(); return null; }
	}
}