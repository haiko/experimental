/**
 *
 */
package nl.cybercompany.treinadvies.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Different kind of checks.
 *
 * @author haiko
 *
 */
public class Checks {

	public static boolean isNull(Object o) {
		return o == null;
	}

	public static boolean notNull(Object o) {
		return !isNull(o);
	}

	public static void nullCheck(Object... objects) {
		List<Object> list = Arrays.asList(objects);

		for (Object object : list) {
			if (object == null) {
				throw new IllegalStateException("null object. Parameter position:" + (list.indexOf(object) + 1));
			}
		}
	}
}
