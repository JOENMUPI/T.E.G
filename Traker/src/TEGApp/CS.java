package TEGApp;


/**
* TEGApp/CS.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class CS implements org.omg.CORBA.portable.IDLEntity
{
  public TEGApp.baseS response = null;
  public int profile = (int)0;
  public String ObjectName = null;
  public String methodName = null;
  public String params[] = null;
  public String typeParams[] = null;

  public CS ()
  {
  } // ctor

  public CS (TEGApp.baseS _response, int _profile, String _ObjectName, String _methodName, String[] _params, String[] _typeParams)
  {
    response = _response;
    profile = _profile;
    ObjectName = _ObjectName;
    methodName = _methodName;
    params = _params;
    typeParams = _typeParams;
  } // ctor

} // class CS
