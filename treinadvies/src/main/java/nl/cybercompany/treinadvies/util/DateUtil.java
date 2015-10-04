/**
 *
 */
package nl.cybercompany.treinadvies.util;

import org.joda.time.DateTime;

/**
 * Utility methods for date formats used in this application. Keeps formats in one place.
 *
 * @author haiko
 *
 */
public class DateUtil {


		/**
		 * Parses dates as {@link String} to a {@link DateTime}.
		 *
		 *
		 * @param s a {@link String}
		 * @return a {@link DateTime} representaation.
		 */
		public static DateTime parse(String s){
			return DateTime.parse(s);
		}
}
