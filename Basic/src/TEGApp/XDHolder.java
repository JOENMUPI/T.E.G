package TEGApp;

/**
* TEGApp/XDHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class XDHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.XD value = null;

  public XDHolder ()
  {
  }

  public XDHolder (TEGApp.XD initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.XDHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.XDHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.XDHelper.type ();
  }

}
