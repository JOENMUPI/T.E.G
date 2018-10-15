package TEGApp;


/**
* TEGApp/SPHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* domingo 14 de octubre de 2018 05:43:45 PM VET
*/

abstract public class SPHelper
{
  private static String  _id = "IDL:TEGApp/SP:1.0";

  public static void insert (org.omg.CORBA.Any a, TEGApp.SP that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TEGApp.SP extract (org.omg.CORBA.Any a)
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
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [5];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = TEGApp.baseSHelper.type ();
          _members0[0] = new org.omg.CORBA.StructMember (
            "infoR",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "ObjectName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "methodName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "params",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "typeParams",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (TEGApp.SPHelper.id (), "SP", _members0);
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

  public static TEGApp.SP read (org.omg.CORBA.portable.InputStream istream)
  {
    TEGApp.SP value = new TEGApp.SP ();
    value.infoR = TEGApp.baseSHelper.read (istream);
    value.ObjectName = istream.read_string ();
    value.methodName = istream.read_string ();
    int _len0 = istream.read_long ();
    value.params = new String[_len0];
    for (int _o1 = 0;_o1 < value.params.length; ++_o1)
      value.params[_o1] = istream.read_string ();
    int _len1 = istream.read_long ();
    value.typeParams = new String[_len1];
    for (int _o2 = 0;_o2 < value.typeParams.length; ++_o2)
      value.typeParams[_o2] = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TEGApp.SP value)
  {
    TEGApp.baseSHelper.write (ostream, value.infoR);
    ostream.write_string (value.ObjectName);
    ostream.write_string (value.methodName);
    ostream.write_long (value.params.length);
    for (int _i0 = 0;_i0 < value.params.length; ++_i0)
      ostream.write_string (value.params[_i0]);
    ostream.write_long (value.typeParams.length);
    for (int _i1 = 0;_i1 < value.typeParams.length; ++_i1)
      ostream.write_string (value.typeParams[_i1]);
  }

}
