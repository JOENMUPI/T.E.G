package TEGApp;


/**
* TEGApp/XL.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

public final class XL implements org.omg.CORBA.portable.IDLEntity
{
  public String host = null;
  public String objectName = null;
  public String idMsg = null;
  public String typeMsg = null;
  public String infoMsg = null;

  public XL ()
  {
  } // ctor

  public XL (String _host, String _objectName, String _idMsg, String _typeMsg, String _infoMsg)
  {
    host = _host;
    objectName = _objectName;
    idMsg = _idMsg;
    typeMsg = _typeMsg;
    infoMsg = _infoMsg;
  } // ctor

} // class XL