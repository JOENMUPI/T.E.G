package TEGApp;

/**
* TEGApp/DHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public final class DHolder implements org.omg.CORBA.portable.Streamable
{
  public TEGApp.D value = null;

  public DHolder ()
  {
  }

  public DHolder (TEGApp.D initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = TEGApp.DHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    TEGApp.DHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return TEGApp.DHelper.type ();
  }

}
