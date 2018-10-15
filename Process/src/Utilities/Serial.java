package Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import TEGApp.byt;

public class Serial {
	public static byte[] serializeParams(Object[] obj) {
		ByteArrayOutputStream bs= new ByteArrayOutputStream();
		ObjectOutputStream os;
		try {
			os = new ObjectOutputStream (bs);
			os.writeObject(obj); 
			os.close();
		}
		
		catch (IOException e) { System.err.println("Error: " + e); e.printStackTrace(); }
		return bs.toByteArray(); 
	}
	
	public static Object[] deserializeParams(byt[] b) {
		Object[] response = (Object[]) new Object();
		try {
			for(int i = 0; i < b.length; i++) {
				ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(b[i].obj));	
				response[i] = (Object[])is.readObject();
				is.close();
			}
			return response;
		} 
		
		catch (IOException | ClassNotFoundException e) {
			System.err.println("Error: " + e); 
			e.printStackTrace(); 
			return null; 
		}
	}
}