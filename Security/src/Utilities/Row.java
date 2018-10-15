package Utilities;

import java.io.Serializable;
import java.util.HashMap;
public class Row implements Serializable{
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> hm;
	
	public Row() { this.hm = new HashMap<String, Object>(); }
	public void setField(String key, Object value) { this.hm.put(key, value); }
	public Caster getField(String key) { return new Caster(this.hm.get(key)); }
	public Caster getField(int i) { Object[] arr = this.hm.values().toArray(); return new Caster(arr[i]); }
}