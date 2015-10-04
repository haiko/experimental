package nl.cybercompany.treinadvies.nsapi;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * ReisAdviesNSApi van NSAPI.
 *
 *
 * @author haiko
 *
 */
@Root(name="ReisMogelijkheden")
public class ReisAdviesNSApi {

	@ElementList(inline=true, required=false)
	private List<ReisMogelijkheidNSApi> reisMogelijkheden;

	public List<ReisMogelijkheidNSApi> getReisMogelijkheden() {
		return reisMogelijkheden;
	}
}
