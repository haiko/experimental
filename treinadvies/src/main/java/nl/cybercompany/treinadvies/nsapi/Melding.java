/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Melding verstoring.
 *
 * @author haiko
 *
 */
@Root(name="Melding")
public class Melding implements Serializable{

	@Element(name="Id", required=false)
	private String id;

	@Element(name="Ernstig")
	private Boolean ernstig;

	@Element(name="Text")
	private String text;

	public String getId() {
		return id;
	}

	public Boolean getErnstig() {
		return ernstig;
	}

	public String getText() {
		return text;
	}
}
