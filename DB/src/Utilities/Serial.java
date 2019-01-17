package Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serial {
	public static byte[] serializeElement(Object obj) {
		try {
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream (bs);
			
			os.writeObject(obj);
			try { return bs.toByteArray();
			} finally {	
				os.close();
				bs.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object deserializeElement(byte[] b) {
		try {
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(b));
			Object res = is.readObject(); 
			
			is.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace(); 
			return null; 
		} 
	}
}