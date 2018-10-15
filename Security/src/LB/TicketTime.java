package LB;

import java.util.Calendar;

public class TicketTime {
	private double[] t = new double[2];
	private double code;
	
	public double getCode() { return this.code; }
	public void initialCaptureTime() { this.captureTime(1); createCode(); }
	public void finalCaptureTime() { this.captureTime(2); }
	private void captureTime(int i) { this.t[i] =  Calendar.getInstance().getTimeInMillis(); }
	private void createCode() { this.code = t[0] + Math.random() / Math.random() * 1000; }
	public double getResult(){ return t[1] - t[0]; }
}