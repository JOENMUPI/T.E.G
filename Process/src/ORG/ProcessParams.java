package ORG;

public class ProcessParams {
	private static Object getParams(String param, String type) {
		switch (type) {
			case "int": return Integer.parseInt(param);
			case "Integer": return new Integer(param);
			case "float": return Float.parseFloat(param);
			case "Float": return new Float(param);
			case "double": return Double.parseDouble(param);
			case "Double": return new Double(param);
			case "string": return new String(param);
			case "byte": return Byte.parseByte(param);
			case "Byte": return new Byte(param);
			case "char": return new Character(param.charAt(0));
			case "Character": return new Character(param.charAt(0));
			case "boolean": return Boolean.parseBoolean(param);
			case "Boolean": return new Boolean(param);
			default: return null;
		}
	}	
	
	public static Object[] parseParams(String[] param, String[] type) {
		Object[] params = new Object[param.length];
		for(int i = 0; i < param.length; i++) {
			params[i] = getParams(param[i], type[i]);
		}
		
		return params;
	}
}