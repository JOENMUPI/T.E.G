package TEGApp;


/**
* TEGApp/CtoSPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* domingo 14 de octubre de 2018 05:43:45 PM VET
*/

public abstract class CtoSPOA extends org.omg.PortableServer.Servant
 implements TEGApp.CtoSOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("senData", new java.lang.Integer (0));
    _methods.put ("test", new java.lang.Integer (1));
    _methods.put ("shutdown", new java.lang.Integer (2));
    _methods.put ("confirmation", new java.lang.Integer (3));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // TEGApp/CtoS/senData
       {
         TEGApp.CS data = TEGApp.CSHelper.read (in);
         boolean $result = false;
         $result = this.senData (data);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 1:  // TEGApp/baseI/test
       {
         boolean $result = false;
         $result = this.test ();
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       case 2:  // TEGApp/baseI/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       case 3:  // TEGApp/baseI/confirmation
       {
         String code = in.read_string ();
         this.confirmation (code);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:TEGApp/CtoS:1.0", 
    "IDL:TEGApp/baseI:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CtoS _this() 
  {
    return CtoSHelper.narrow(
    super._this_object());
  }

  public CtoS _this(org.omg.CORBA.ORB orb) 
  {
    return CtoSHelper.narrow(
    super._this_object(orb));
  }


} // class CtoSPOA
