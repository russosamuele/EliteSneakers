package model;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
		HashMap<Character, String> characterMap = new HashMap<>();
		// characterMap.put('è', "&egrave;");
        //characterMap.put('à', "&agrave;");
        characterMap.put('<', "&lt;");
        characterMap.put('>', "&gt;");
        characterMap.put('&', "&amp;");
        characterMap.put('"', "&quot;");

		char c;
		for(int i=0; i<input.length(); i++) {
			c = input.charAt(i);
				
			// Filtra il carattere desiderato
            String replacement = characterMap.get(c);
            if (replacement != null) {
                filtered.append(replacement);
            } else if(c == 'Ã'){
    				i++;
    				c = input.charAt(i);
    				if(c=='¨') {
    					filtered.append("&egrave");
    				}else
    					filtered.append("&agrave");
    		}else{
    			
                filtered.append(c);
            }
		} //fine for
		
		return filtered.toString();

	}
	
	
		
	
}
