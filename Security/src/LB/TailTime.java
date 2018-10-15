package LB;

import java.util.Queue;

public class TailTime {
	private  Queue<Integer> q;
	private int maxElements = 5;
	
	public void setTime(int d) { if(this.checklong()) { this.q.poll(); } this.q.add(d); }
	public void setElements(int elements) { this.maxElements = elements; }
	public double averageTime() { 
		double d = 0; 
		for(int i = 0; i < this.q.size(); i++) { d += this.q.peek(); }
		return d/this.q.size();
	}
	
	private Boolean checklong() {
		if(this.q.size() == this.maxElements) { return true; }
		else { return false; }
	}
}