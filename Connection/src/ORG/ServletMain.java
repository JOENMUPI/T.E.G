package ORG;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.omg.CORBA.ORB;

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

@MultipartConfig
@WebServlet("/ServletMain")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String objName = "Connection";
	private  String host;
	
	public ServletMain() { 
    	super(); 
    	try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getParameter("methodName")) {
			case "Logout":
				this.logout(request, response);
				break;
			default:
				this.sendata(request, response);
				break;
		}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

		switch(reqBody.getString("methodName")) {
			case "Login":
				this.login(session, reqBody, response);
				break;
			case "Register":
				this.register(session, reqBody, response);
				break;
			default:
				this.sendata(session, reqBody, response);
				break;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch(request.getParameter("methodName")) {
			case "updatePassword":
				this.updatePassword(request, response);
				break;
		
			default:
				this.sendata(request, response);
				break;
		}
    }
	
	private void sendata(HttpSession session, JSONObject reqBody, HttpServletResponse response) throws IOException {  	
		JSONObject json = new JSONObject();
		String code  = "" + new SecureRandom().nextLong();
		
		try { 
			if(!session.isNew()) {		
				LB.createticket(code);
				XC xc = ClientOrb.getSImpl(LB.getOrb("Security")).senData(new CS(new baseS(code, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), Integer.parseInt(session.getAttribute("profile").toString()), reqBody.get("objectName").toString(), reqBody.get("methodName").toString(), this.fragment(reqBody.getString("params")), this.fragment(reqBody.getString("typeParams"))));
				ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "info", "the msg with" + code + "code is sent to security"));
				//aqui hay que gestionar el tipo de respuesta traida, dicha estructura no la e arquitectado... depende de los b.o y como se va desenvolviendo esto.....
			} else { session.invalidate(); }
		} catch(Exception e) {
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "Internal error"));
			json.put("status", 500).put("response", "Internal error: " + e);
		} finally { response.getWriter().println(json); }
	}
	
	private void sendata(HttpServletRequest request, HttpServletResponse response) throws IOException {  	
		HttpSession session = request.getSession();
		JSONObject json = new JSONObject();
		String code  = "" + new SecureRandom().nextLong();
		
		try { 
			if(!session.isNew()) {				
				LB.createticket(code);
				XC xc = ClientOrb.getSImpl(LB.getOrb("Security")).senData(new CS(new baseS(code, this.host, Props.getPropertiesFile("Connections", "Server").getProperty("port")), Integer.parseInt(session.getAttribute("profile").toString()), request.getParameter("objectName").toString(), request.getParameter("methodName").toString(), this.fragment(request.getParameter("params")), this.fragment(request.getParameter("typeParams"))));
				ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Info", "the msg with" + code + "code is sent to security"));
			} else { session.invalidate(); }
		} catch(Exception e) {
			e.printStackTrace();
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "Internal error"));
			json.put("status", 500).put("response", "Internal error: " + e);
		} finally { response.getWriter().println(json); }
	}
	
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
    	JSONObject json = new JSONObject();
    	String code = "" + new SecureRandom().nextLong();
		
    	try {
			if(!session.isNew()) {
				LB.createticket(code);
				ClientOrb.getDImpl(LB.getOrb("DB")).dataRequest(new XD(code, "user", "updatePass", Serial.serializeParams(new Object[] { request.getAttribute("password"), session.getAttribute("pass"), session.getAttribute("user") })));
				json.put("status", 200).put("response", "password updated").put("success", true);
				ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Info", " The user " + session.getAttribute("user").toString() + " has updated his password"));
			} else {
				json.put("status", 400).put("response", "invalid session").put("success", false);
				ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "invalid session on updatePassword."));
			}
			
		} catch(Exception e) {
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "internal error on update"));
			e.printStackTrace(); 
		} finally { response.getWriter().print(json.toString()); }
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
    	JSONObject json = new JSONObject();
		
    	if(session.isNew()) {
    		json.put("status", "401").put("response", "You're not logged in");
			session.invalidate();
		} else { session.invalidate(); }
		response.getWriter().print(json.toString());
	}
	
	private void register(HttpSession session, JSONObject reqBody, HttpServletResponse response) throws IOException {		
		JSONObject json = new JSONObject();
		String code = "" + new SecureRandom().nextLong();
		
		try {
			if(Serial.deserializeDS(ClientOrb.getDImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("connections", "server")), null)).dataRequest(new XD(code, "user", "checkUser", Serial.serializeParams(new Object[] { reqBody.getString("fullname"), reqBody.getString("username"), reqBody.getString("password") }))).obj).hasNext()) {
				json.put("status", 400).put("response", "user already used").put("success", false);
		    	session.invalidate();
		    } else {
		    	LB.createticket(code);
		    	DataSet ds = Serial.deserializeDS(ClientOrb.getDImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("connections", "server")), null)).dataRequest(new XD(code, "user", "login", Serial.serializeParams(new Object[] { reqBody.getString("username"), reqBody.getString("password") }))).obj);	
		    	ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Info", "the registration for  " + reqBody.getString("username") + " has been successful"));
		    	json.put("status", 200).put("response", "signup finished").put("success", true);
		    	this.storeValue(reqBody.getString("username"), reqBody.getString("password"), ds.getField("user_pro").toString(), session);
			}
		} catch(Exception e) { 
			ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "internal error on register"));
			e.printStackTrace();
			json.put("status", 500).put("response", "Internal error " + e).put("success", false);
		} finally { response.getWriter().println(json); } 
	}
    
    private void login(HttpSession session, JSONObject reqBody, HttpServletResponse response) throws IOException {
    	JSONObject json = new JSONObject();
    	String code = "" + new SecureRandom().nextLong();
    	
    	try {
	    	if(session.isNew()) {
	    		LB.createticket(code);
	    		DataSet ds = Serial.deserializeDS(ClientOrb.getDImpl(ORB.init(ArgsParser.serverInfo(Props.getPropertiesFile("connections", "db")), null)).dataRequest(new XD(code, "user", "login", Serial.serializeParams(new Object[] { reqBody.getString("username"), reqBody.getString("password") }))).obj);
				
	    		if(ds.hasNext()) {
					ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Info", "ther user: " + reqBody.getString("username") + " is loggend"));
					this.storeValue(reqBody.getString("username"), reqBody.getString("password"), ds.getField("user_pro").toString(), session);
					json.put("status", 200).put("response", reqBody.getString("username") + " logged").put("success", true).put("fullname", ds.getField("user_ful_nam").toString());
				} else {
					ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "wrong user:" + reqBody.getString("username") + " and pass: " + reqBody.getString("password")));
					json.put("response", "Wrong email or password").put("status", 400).put("success", false);
					session.invalidate();
				}
	    	} else { json.put("response", "you're logged in").put("status", 400).put("success", false); }
    	} catch(Exception e) { 
    		ClientOrb.getDImpl(LB.getOrb("DB")).sendLog(new XL(this.host, objName, code, "Error", "internal error on login"));
    		json.put("status", 500).put("response", "Internal error: " + e).put("success", false); 
    		e.printStackTrace(); 
    	} finally { response.getWriter().println(json); }
	}	
    
    private String[] fragment(String array) { return array.substring(1, array.length() - 1).split(","); }
	private void storeValue(String user, String pass, String profile, HttpSession session) {
		session.setAttribute("user", user);
		session.setAttribute("pass", pass);
		session.setAttribute("profile", profile);
	}
}