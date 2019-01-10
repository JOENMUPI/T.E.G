package TEGApp;


/**
* TEGApp/DPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from TEG.idl
* mi�rcoles 9 de enero de 2019 05:17:35 PM VET
*/

public abstract class DPOA extends org.omg.PortableServer.Servant
 implements TEGApp.DOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("shutdown", new java.lang.Integer (0));
    _methods.put ("dataRequest", new java.lang.Integer (1));
    _methods.put ("sendLog", new java.lang.Integer (2));
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
       case 0:  // TEGApp/D/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       case 1:  // TEGApp/D/dataRequest
       {
         TEGApp.XD data = TEGApp.XDHelper.read (in);
         TEGApp.byt $result = null;
         $result = this.dataRequest (data);
         out = $rh.createReply();
         TEGApp.bytHelper.write (out, $result);
         break;
       }

       case 2:  // TEGApp/D/sendLog
       {
         TEGApp.XL data = TEGApp.XLHelper.read (in);
         this.sendLog (data);
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
    "IDL:TEGApp/D:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public D _this() 
  {
    return DHelper.narrow(
    super._this_object());
  }

  public D _this(org.omg.CORBA.ORB orb) 
  {
    return DHelper.narrow(
    super._this_object(orb));
  }


} // class DPOA
