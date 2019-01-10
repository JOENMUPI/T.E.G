package Utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import Utilities.DataSet;
import TEGApp.byt;

public class Serial {
	public static byte[] serializeDS(DataSet ds) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os;
		
		try {
			os = new ObjectOutputStream (bs);
			os.writeObject(ds); 
			os.close();
			return bs.toByteArray();
		} catch (IOException e) { e.printStackTrace(); return null; } 
	}
	
	public static DataSet deserializeDS(byte[] b) {
		try {
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(b));
			DataSet response = (DataSet)is.readObject();
			
			is.close();
			return response;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace(); 
			return null; 
		}
	}
	
	public static byte[] serializeParams(Object[] obj) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os;
		
		try {
			os = new ObjectOutputStream (bs);
			os.writeObject(obj); 
			os.close();
		} catch (IOException e) { e.printStackTrace(); }
		return bs.toByteArray(); 
	}
	
	public static Object[] deserializeParams(byt[] b) {
		try {
			Object[] response = (Object[]) new Object();
			
			for(int i = 0; i < b.length; i++) {
				ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(b[i].obj));	
				response[i] = (Object)is.readObject();
				is.close();
			}
			
			return response;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace(); 
			return null; 
		}
	}
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, HashMap<String, String>> deserializeConnections(byte[] files) {
		try {
			ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(files));
			HashMap<String, HashMap<String, String>> aux = (HashMap<String, HashMap<String, String>>) is.readObject();
			
			is.close();
			return aux;
		} catch(Exception e) {
			e.printStackTrace(); 
			return null;
		}
	}
}