package TEGApp;

/**
* TEGApp/SHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

public final class SHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.S value = null;

  public SHolder ()
  {
  }

  public SHolder (TEGApp.S initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.SHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.SHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.SHelper.type ();
  }

}
