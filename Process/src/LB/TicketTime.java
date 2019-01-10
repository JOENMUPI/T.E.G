package LB;

public class TicketTime {
	private long[] t = new long[2];
	private String code;
	
	public TicketTime(String id) {
		this.captureTime(1);
		this.code = id;
	}
	
	public String getCode() { return this.code; }
	public void finalCaptureTime() { this.captureTime(2); }
	private void captureTime(int i) { this.t[i] =  System.currentTimeMillis(); }
	public long getResult(){ return t[1] - t[0]; }
}