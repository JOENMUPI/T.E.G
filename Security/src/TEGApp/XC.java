package TEGApp;


/**
* TEGApp/XC.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* domingo 14 de octubre de 2018 05:43:45 PM VET
*/

public final class XC implements org.omg.CORBA.portable.IDLEntity
{
  public String sessionId = null;
  public byte Response[] = null;

  public XC ()
  {
  } // ctor

  public XC (String _sessionId, byte[] _Response)
  {
    sessionId = _sessionId;
    Response = _Response;
  } // ctor

} // class XC
