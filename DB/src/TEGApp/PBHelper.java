package TEGApp;


/**
* TEGApp/PBHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

abstract public class PBHelper
{
  private static String  _id = "IDL:TEGApp/PB:1.0";

  public static void insert (org.omg.CORBA.Any a, TEGApp.PB that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TEGApp.PB extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = TEGApp.baseSHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "response",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "methodName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "params",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (TEGApp.PBHelper.id (), "PB", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static TEGApp.PB read (org.omg.CORBA.portable.InputStream istream)
  {
    TEGApp.PB value = new TEGApp.PB ();
    value.response = TEGApp.baseSHelper.read (istream);
    value.methodName = istream.read_string ();
    int _len0 = istream.read_long ();
    value.params = new byte[_len0];
    istream.read_octet_array (value.params, 0, _len0);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TEGApp.PB value)
  {
    TEGApp.baseSHelper.write (ostream, value.response);
    ostream.write_string (value.methodName);
    ostream.write_long (value.params.length);
    ostream.write_octet_array (value.params, 0, value.params.length);
  }

}
