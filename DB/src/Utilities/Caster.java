package Utilities;

import java.io.Serializable;

public class Caster implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object object;
	
	public Caster(Object o) { this.object = o; }
	public Integer asInteger() { return Integer.valueOf(this.object.toString()); }
	public String asString() { return String.valueOf(this.object.toString()); }
	public Float asFloat() { return Float.valueOf(this.object.toString()); }
	public Double asDouble() { return Double.valueOf(this.object.toString()); }	
	public Boolean asBoolean() { return Boolean.valueOf(this.object.toString()); }	
}