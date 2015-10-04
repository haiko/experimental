/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * @author haiko
 *
 */
@Root(name="Spoor", strict=false)
public class Spoor {

	@Attribute(name="wijziging", required=false)
	private Boolean wijziging;

	@Text(required=false)
	private String value;

	public Boolean getWijziging() {
		return wijziging;
	}

	public String getValue() {
		return value;
	}
}
