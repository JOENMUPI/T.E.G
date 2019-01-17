package ORG;

import java.io.IOException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.omg.CORBA.ORB;

import LB.ConnectionsMap;
import LB.LB;
import ORB.ArgsParser;
import ORB.ClientOrb;
import TEGApp.CS;
import TEGApp.XC;
import TEGApp.XD;
import TEGApp.XL;
import TEGApp.baseS;
import Utilities.DataSet;
import Utilities.Props;
import Utilities.Serial;

@SuppressWarnings("unused")
public class BO {
	private static final String objName = "Connection";
	
	public static void sendata(HttpSession session, JSONObject reqBody, HttpServletResponse response, String host) throws IOException {  	
		JSONObject json = new JSONObject();
		String code  = "" + new SecureRandom().nextLong();
		String[] s = ConnectionsMap.bestConnetion("Security");
		
		try { 
			if(!session.isNew()) {		
				LB.createticket(code);               
				XC xc = ClientOrb.getSImpl(ORB.init(ArgsParser.serverInfo(s[0], s[1]), null)).senData(new CS(new baseS(code, host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), Integer.parseInt(session.getAttribute("profile").toString()), reqBody.get("objectName").toString(), reqBody.get("methodName").toString(), fragment(reqBody.getString("params")), fragment(reqBody.getString("typeParams"))));
				String[] header =  xc.typeResponse.split("-");
				
				LB.confirmation("Security", s[0], s[1], code);
				ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "info", "the msg with" + code + "code is sent to security"));
				switch(header[2]) {
				case "String":
					json.put("success", true).put("data", new String(xc.Response)).put("response", header[1]).put("status", 200);
					break;
				case "DataSet":
					DataSet ds = (DataSet)Serial.deserializeElement(xc.Response);
					if(ds.hasNext()) {
						switch(reqBody.getString("methodName")) {
							case "getItem":
								ds.getField("").asString();
								break;
							default:
								break;
						}
						json.put("success", (boolean) Serial.deserializeElement(xc.Response)).put("data", JSONObject.NULL).put("response", header[1]).put("status", 200);	
					} else {
						json.put("success", (boolean) Serial.deserializeElement(xc.Response)).put("data", JSONObject.NULL).put("response", header[1]).put("status", 200);
					}		
					break;
				case "Boolean":
					json.put("success", (boolean) Serial.deserializeElement(xc.Response)).put("data", JSONObject.NULL).put("response", header[1]).put("status", 200);                            
					break;
				default:
					break;
				}
			} else { session.invalidate(); }
		} catch(Exception e) {
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "Internal error"));
			json.put("status", 500).put("response", "Internal error: " + e);
		} finally { 
			code = null;
			response.getWriter().println(json); 
		}
	}
	
	public static void sendata(HttpServletRequest request, HttpServletResponse response, String host) throws IOException {  	
		JSONObject json = new JSONObject();
		String code  = "" + new SecureRandom().nextLong();
		
		try { 
			if(!request.getSession().isNew()) {				
				LB.createticket(code);
				XC xc = ClientOrb.getSImpl(LB.getOrb("Security")).senData(new CS(new baseS(code, host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), Integer.parseInt(request.getSession().getAttribute("profile").toString()), request.getParameter("objectName").toString(), request.getParameter("methodName").toString(), fragment(request.getParameter("params")), fragment(request.getParameter("typeParams"))));
				ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Info", "the msg with" + code + "code is sent to security"));
			} else { request.getSession().invalidate(); }
		} catch(Exception e) {
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "Internal error"));
			json.put("status", 500).put("response", "Internal error: " + e);
		} finally {
			code = null;
			response.getWriter().println(json); 
		}
	}
	
	public static void updatePassword(HttpSession session, JSONObject reqBody, HttpServletResponse response, String host) throws IOException {
		JSONObject json = new JSONObject();
    	String code = "" + new SecureRandom().nextLong();
    	String[] s = ConnectionsMap.bestConnetion("DataBase"), params = fragment(reqBody.getString("params"));
		
    	try {
			if(!session.isNew()) {
				LB.createticket(code);
				if(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataUpdate(new XD(code, "user", "updatePass", Serial.serializeElement(new Object[] { params[0], session.getAttribute("pass"), session.getAttribute("user") })))) {
					LB.confirmation("DataBase", s[0], s[1], code);
					json.put("status", 200).put("response", "password updated").put("success", true);
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Info", " The user " + session.getAttribute("user").toString() + " has updated his password"));
				} else {
					LB.confirmation("DataBase", s[0], s[1], code);
					json.put("status", 200).put("response", "error on db, update").put("success", false);
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "update failed, db."));
				}
			} else {
				json.put("status", 400).put("response", "invalid session").put("success", false);
				ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "invalid session on updatePassword."));
			}
		} catch(Exception e) {
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "internal error on update"));
			e.printStackTrace(); 
		} finally {
			s = null; params = null; code = null;
			response.getWriter().print(json.toString()); 
		}
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		
    	if(request.getSession().isNew()) { json.put("status", 401).put("response", "You're not logged in");
		} else { json.put("status", 200).put("response", "You're out"); }
    	request.getSession().invalidate();
		response.getWriter().print(json.toString());
	}
	
	public static void register(HttpSession session, JSONObject reqBody, HttpServletResponse response, String host) throws IOException {		
		JSONObject json = new JSONObject();
		String code = "" + new SecureRandom().nextLong();
		String[] s = ConnectionsMap.bestConnetion("DataBase"), params = fragment(reqBody.getString("params"));
		
		try {
			if(((DataSet)Serial.deserializeElement(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataRequest(new XD(code, "user", "checkUser", Serial.serializeElement(new Object[] { params[0] }))).obj)).hasNext()) {
				json.put("status", 400).put("response", "user already used").put("success", false);                                                                                                                  
		    	session.invalidate();
		    } else {
		    	LB.createticket(code);
		    	if(ClientOrb.getDImpl(LB.getOrb("DataBase")).dataInsert(new XD(code, "user", "register", Serial.serializeElement(new Object[] { params[0], params[1], params[2] })))) {
		    		LB.confirmation("DataBase", s[0], s[1], code);
		    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Info", "the registration for  " + params[0] + " has been successful"));
			    	json.put("status", 200).put("response", "signup finished").put("success", true);
			    	storeValue(reqBody.getString("username"), reqBody.getString("password"), /*ds.getField("user_pro").toString()*/1, session);
		    	} else {
		    		LB.confirmation("DataBase", s[0], s[1], code);
		    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Info", "the registration for  " + reqBody.getString("username") + " has been successful"));
			    	json.put("status", 500).put("response", "Internal error on register, db").put("success", false);
		    	}
			}
		} catch(Exception e) { 
			ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "internal error on register"));
			e.printStackTrace();
			json.put("status", 500).put("response", "Internal error " + e).put("success", false);
		} finally {
			s = null; params = null; code = null;
			response.getWriter().println(json); 
		} 
	}
    
	public static void login(HttpSession session, JSONObject reqBody, HttpServletResponse response, String host) throws IOException {
    	JSONObject json = new JSONObject();
    	String code = "" + new SecureRandom().nextLong();
    	String[] s = ConnectionsMap.bestConnetion("DataBase"), params = fragment(reqBody.getString("params"));
    	
    	try {
	    	if(session.isNew()) {
	    		LB.createticket(code);
	    		DataSet ds = (DataSet) Serial.deserializeElement(ClientOrb.getDImpl(ORB.init(ArgsParser.serverInfo(s[0],s[1]), null)).dataRequest(new XD(code, "user", "login", Serial.serializeElement(new Object[] { params[0], params[1] }))).obj);     
	    		LB.confirmation("DataBase", s[0], s[1], code);                                                                                                                                                             
	    		if(ds.hasNext()) {
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Info", "ther user: " + params[0] + " is loggend"));
					storeValue(params[0], params[1], /*ds.getField("user_pro").toString()*/1, session);
					json.put("status", 200).put("response", params[0] + " logged").put("success", true).put("data", ds.getField("fullname").toString());
				} else {
					ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "wrong user:" + params[0] + " and pass: " + params[1]));
					json.put("response", "Wrong email or password").put("status", 400).put("success", false);
					session.invalidate();
				}
	    	} else { json.put("response", "you're logged in").put("status", 400).put("success", false); }
    	} catch(Exception e) { 
    		ClientOrb.getDImpl(LB.getOrb("DataBase")).sendLog(new XL(host, objName, code, "Error", "internal error on login"));
    		json.put("status", 500).put("response", "Internal error: " + e).put("success", false); 
    		e.printStackTrace(); 
    	} finally {
    		code = null; params = null; s = null;
    		response.getWriter().println(json); 
    	}
	}	
    
    private static String[] fragment(String array) { return array.substring(1, array.length() - 1).split(","); }
	private static void storeValue(String user, String pass, int profile, HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("pass", pass);
		session.setAttribute("profile", profile);
	}
}