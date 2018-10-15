package TEGApp;


/**
* TEGApp/XtoDHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* domingo 14 de octubre de 2018 05:43:45 PM VET
*/

abstract public class XtoDHelper
{
  private static String  _id = "IDL:TEGApp/XtoD:1.0";

  public static void insert (org.omg.CORBA.Any a, TEGApp.XtoD that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TEGApp.XtoD extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (TEGApp.XtoDHelper.id (), "XtoD");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static TEGApp.XtoD read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_XtoDStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TEGApp.XtoD value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static TEGApp.XtoD narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TEGApp.XtoD)
      return (TEGApp.XtoD)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TEGApp._XtoDStub stub = new TEGApp._XtoDStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static TEGApp.XtoD unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TEGApp.XtoD)
      return (TEGApp.XtoD)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TEGApp._XtoDStub stub = new TEGApp._XtoDStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
