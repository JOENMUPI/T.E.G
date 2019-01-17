package LB;

import java.util.LinkedList;

public class TailTime {
	private  LinkedList<Double> ll = new LinkedList<Double>();
	private int maxElements = 5;
	
	public TailTime() {
		for(int i = 0; i < this.maxElements; i++) { this.ll.add(new Double(0)); } 
	}
	
	public void setTime(double d) {  
		this.ll.poll();
		this.ll.add(d); 
	}
	
	public void setElements(int elements) { this.maxElements = elements; }
	public double averageTime() { 
		double d = 0; 
		for(Double val : this.ll) { d += val; }
		return d / this.maxElements;  
	}
}