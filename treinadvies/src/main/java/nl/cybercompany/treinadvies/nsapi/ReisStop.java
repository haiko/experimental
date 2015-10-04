/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author haiko
 *
 */
@Root(name="ReisStop")
public class ReisStop {

	@Element(name="Naam", required=false)
	private String naam;

	@Element(name="Tijd", required=false)
	private String tijd;

	@Element(name="VertrekVertraging", required=false)
	private String vertrekVertraging;

	@Element(name="Spoor", required=false)
	private Spoor spoor;

	public String getNaam() {
		return naam;
	}

	public String getTijd() {
		return tijd;
	}

	public Spoor getSpoor() {
		return spoor;
	}

}
