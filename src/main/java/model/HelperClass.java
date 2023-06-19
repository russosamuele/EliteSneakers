package model;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelperClass {
	
	private HelperClass() {
	}
	private static Logger logger = Logger.getAnonymousLogger();
	
	public static String toHash(String password) {
		StringBuilder sb = new StringBuilder();
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 
                              ).toLowerCase().substring(1,3));
            }
        } catch (java.security.NoSuchAlgorithmException e) {
        	logger.log(Level.WARNING, "Problema hash pswd!");
        }
        return sb.toString();
    }
	
	
	public static String filter(String input) {
		
		StringBuilder filtered = new StringBuilder();
		char c;
		for(int i=0; i<input.length(); i++) {
			c = input.charAt(i);
			
			if(c == '<')
				filtered.append("&lt;");
			else if(c == '>')
				filtered.append("&gt;");
			else if(c=='"')
				filtered.append("&quot;");
			else if(c=='&')
				filtered.append("&amp;");
			else
				filtered.append(c);
		}
		
		return (filtered.toString());
		
	}
	
	

}
