package TEGApp;


/**
* TEGApp/XD.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class XD implements org.omg.CORBA.portable.IDLEntity
{
  public String idMsg = null;
  public String schema = null;
  public String queryId = null;
  public byte params[] = null;

  public XD ()
  {
  } // ctor

  public XD (String _idMsg, String _schema, String _queryId, byte[] _params)
  {
    idMsg = _idMsg;
    schema = _schema;
    queryId = _queryId;
    params = _params;
  } // ctor

} // class XD
