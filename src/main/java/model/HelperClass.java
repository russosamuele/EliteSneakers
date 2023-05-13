package model;

import java.nio.charset.StandardCharsets;

public class HelperClass {
	
	public static String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 
                              ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }
	
	
	public static String filter(String input) {
		
		StringBuffer filtered = new StringBuffer(input.length());
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
