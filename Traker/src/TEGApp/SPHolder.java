package TEGApp;

/**
* TEGApp/SPHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class SPHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.SP value = null;

  public SPHolder ()
  {
  }

  public SPHolder (TEGApp.SP initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.SPHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.SPHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.SPHelper.type ();
  }

}
