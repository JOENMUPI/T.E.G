package TEGApp;

/**
* TEGApp/BHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class BHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.B value = null;

  public BHolder ()
  {
  }

  public BHolder (TEGApp.B initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.BHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.BHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.BHelper.type ();
  }

}
