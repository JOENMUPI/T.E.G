package TEGApp;


/**
* TEGApp/SP.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

public final class SP implements org.omg.CORBA.portable.IDLEntity
{
  public TEGApp.baseS response = null;
  public String ObjectName = null;
  public String methodName = null;
  public String params[] = null;
  public String typeParams[] = null;

  public SP ()
  {
  } // ctor

  public SP (TEGApp.baseS _response, String _ObjectName, String _methodName, String[] _params, String[] _typeParams)
  {
    response = _response;
    ObjectName = _ObjectName;
    methodName = _methodName;
    params = _params;
    typeParams = _typeParams;
  } // ctor

} // class SP
