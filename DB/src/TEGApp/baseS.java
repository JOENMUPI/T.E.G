package TEGApp;


/**
* TEGApp/baseS.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* domingo 14 de octubre de 2018 05:43:45 PM VET
*/

public final class baseS implements org.omg.CORBA.portable.IDLEntity
{
  public String ipResponse = null;
  public String portResponse = null;
  public String sessionId = null;

  public baseS ()
  {
  } // ctor

  public baseS (String _ipResponse, String _portResponse, String _sessionId)
  {
    ipResponse = _ipResponse;
    portResponse = _portResponse;
    sessionId = _sessionId;
  } // ctor

} // class baseS