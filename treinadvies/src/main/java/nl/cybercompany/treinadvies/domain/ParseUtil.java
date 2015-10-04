/**
 *
 */
package nl.cybercompany.treinadvies.domain;

/**
 * Parsing utility methods.
 *
 *
 * @author haiko
 *
 */
public class ParseUtil {

	/**
	 * add 0 for numbers lower then 9.
	 *
	 * @param minutes
	 * @return
	 */
	public static String formatToTwoCharacterNumber(int number) {
		if(number < 10){
			return new StringBuffer("0").append(number).toString();
		}
		return Integer.toString(number);
	}

}
