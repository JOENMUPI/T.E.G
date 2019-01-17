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

import LB.ConnectionsMap;
import LB.LB;

import ORB.ClientOrb;
import TEGApp.CS;
import TEGApp.XC;
import TEGApp.XD;
import TEGApp.XL;
import TEGApp.baseS;
import Utilities.Props;
import Utilities.Serial;

@MultipartConfig
@WebServlet("/ServletMain")
@SuppressWarnings("unused")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	
	public ServletMain() { 
    	super(); 
    	try { this.host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) { e1.printStackTrace(); }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.setAccessControlHeaders(response);
    	switch(request.getParameter("methodName")) {
			case "Logout":
				BO.logout(request, response);
				break;
			default:
				BO.sendata(request, response, this.host);
				break;
		}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		JSONObject reqBody = new JSONObject(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));

		switch(reqBody.getString("methodName")) {
			case "Login":
				BO.login(request.getSession(), reqBody, response, this.host);
				break;
			case "Register":
				BO.register(request.getSession(), reqBody, response, this.host);
				break;
			case "UpdatePass":
				BO.updatePassword(request.getSession(), reqBody, response, this.host);
				break;
			default:
				System.out.println("default");
				BO.sendata(request.getSession(), reqBody, response, this.host);
				break;
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.setAccessControlHeaders(response);
		switch(request.getParameter("methodName")) {
			default:
				BO.sendata(request, response, this.host);
				break;
		}
    }
	
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
    }
	
	private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers\", \"Content-Type, Accept, X-Requested-With, remember-me");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
    }
}