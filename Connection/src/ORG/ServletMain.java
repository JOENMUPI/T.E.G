package ORG;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ORB.ClientOrb;
import TEGApp.CS;
import TEGApp.XD;

@MultipartConfig
@WebServlet("/ServletMain")
public class ServletMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ServletMain() { super(); }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {	
    		HttpSession session = request.getSession();
			JSONObject reqBody = (JSONObject) new JSONParser().parse(request.getReader());
			switch(reqBody.get("methodName").toString()){
				case"connection":
					ClientOrb co =  new ClientOrb();
					XD xd = new XD();
					xd.schema = "user";
					xd.queryId = "6";//ejemplo de como irian los tiro mas o menos 
					co.iniOrb();
					co.initXtoD();
					co.getXtoDImpl().dataRequest(xd);
					reqBody.get("id").toString();
					reqBody.get("pass").toString();
					break;
				default:
					CS cs = new CS();
					break;
			}
		} 
    	
    	catch (ParseException e) { e.printStackTrace(); }
	}	
}