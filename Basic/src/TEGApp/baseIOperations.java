package TEGApp;


/**
* TEGApp/baseIOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public interface baseIOperations 
{
  void updateConnections (String objName, String host, String port);
  void shutdown ();
  void confirmation (TEGApp.baseS data, String objectName);
} // interface baseIOperations