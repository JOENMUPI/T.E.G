package TEGApp;


/**
* TEGApp/TPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* viernes 11 de enero de 2019 09:39:31 AM VET
*/

public abstract class TPOA extends org.omg.PortableServer.Servant
 implements TEGApp.TOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("shutdown", new java.lang.Integer (0));
    _methods.put ("getConnection", new java.lang.Integer (1));
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
       case 0:  // TEGApp/T/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       case 1:  // TEGApp/T/getConnection
       {
         String objName = in.read_string ();
         String host = in.read_string ();
         String port = in.read_string ();
         TEGApp.byt $result = null;
         $result = this.getConnection (objName, host, port);
         out = $rh.createReply();
         TEGApp.bytHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:TEGApp/T:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public T _this() 
  {
    return THelper.narrow(
    super._this_object());
  }

  public T _this(org.omg.CORBA.ORB orb) 
  {
    return THelper.narrow(
    super._this_object(orb));
  }


} // class TPOA