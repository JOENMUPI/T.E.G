package TEGApp;

/**
* TEGApp/baseSHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class baseSHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.baseS value = null;

  public baseSHolder ()
  {
  }

  public baseSHolder (TEGApp.baseS initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.baseSHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.baseSHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.baseSHelper.type ();
  }

}