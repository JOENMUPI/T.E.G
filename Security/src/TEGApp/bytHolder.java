package TEGApp;

/**
* TEGApp/bytHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class bytHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.byt value = null;

  public bytHolder ()
  {
  }

  public bytHolder (TEGApp.byt initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.bytHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.bytHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.bytHelper.type ();
  }

}
