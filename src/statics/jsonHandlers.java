package statics;

public  class jsonHandlers {

	public static String getJsonResponse(String statue,String title,String msg) {
		
		return "{\"type\":\""+statue+"\","
				+ "\"title\":\""+title+"\","
				+ "\"msg\":\""+msg+"\"}";
	}
	
}
