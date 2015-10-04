/**
 *
 */
package nl.cybercompany.treinadvies.web.util;

import org.apache.wicket.Component;

/**
 * @author haiko
 *
 */
public class WicketUtil {

	/**
	 * Prefix for i18n files
	 *
	 * @param component
	 * @return name of {@link Component} in lowercase.
	 */
	public static String prefix(Component component) {
		return component.getClass().getSimpleName().toLowerCase().concat(".");
	}
}
