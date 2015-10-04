/**
 * 
 */
package nl.cybercompany.treinadvies.util;

import org.springframework.util.StringUtils;

/**
 * Handy stringutils
 * 
 * @author haiko
 *
 */
public class StringUtil {
	
	public static String trimAll(String s){
		return StringUtils.trimAllWhitespace(s);
	}
	
	public static Integer length(String s){
		return Integer.valueOf(s.length());
	}
	
	public static boolean startsWithIgnoreCase(String s, String prefix){
		return StringUtils.startsWithIgnoreCase(trimAll(s), prefix);
	}

}
