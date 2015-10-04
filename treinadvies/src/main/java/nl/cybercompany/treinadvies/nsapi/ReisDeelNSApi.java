/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Reisdeel NSAPI.
 *
 * @author haiko
 *
 */
@Root(name="ReisDeel", strict=false)
public class ReisDeelNSApi {

	@Attribute(name="reisSoort")
	private String reisSoort;

	@Element(name="Vervoerder", required=false)
	private String vervoerder;

	@Element(name="VervoerType")
	private String vervoerderType;

	@ElementList(name="ReisStop", inline=true)
	private List<ReisStop> reisStops;

	public String getReisSoort() {
		return reisSoort;
	}

	public String getVervoerder() {
		return vervoerder;
	}

	public String getVervoerderType() {
		return vervoerderType;
	}

	public List<ReisStop> getReisStops() {
		return reisStops;
	}
}
