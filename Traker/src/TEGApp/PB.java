package TEGApp;


/**
* TEGApp/PB.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

public final class PB implements org.omg.CORBA.portable.IDLEntity
{
  public TEGApp.baseS response = null;
  public String methodName = null;
  public byte params[] = null;

  public PB ()
  {
  } // ctor

  public PB (TEGApp.baseS _response, String _methodName, byte[] _params)
  {
    response = _response;
    methodName = _methodName;
    params = _params;
  } // ctor

} // class PB