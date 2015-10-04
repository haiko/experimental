/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * ReisMogelijkheidNSApi van NSAPI.
 *
 * @author haiko
 *
 */
@Root(name="ReisMogelijkheid")
public class ReisMogelijkheidNSApi {

	@ElementList(entry="Melding", inline=true, required=false)
	private List<Melding> meldingen;


	@Element(name="AantalOverstappen")
	private Integer aantalOverstappen;

	@Element(name="GeplandeReisTijd")
	private String geplandeReistijd;

	@Element(name="ActueleReisTijd")
	private String actueleReisTijd;

	@Element(name="GeplandeVertrekTijd")
	private String geplandeVertrekTijd;

	@Element(name="ActueleVertrekTijd")
	private String actueleVertrektijd;

	@Element(name="Optimaal")
	private Boolean optimaal;

	@Element(name="GeplandeAankomstTijd")
	private String geplandeAankomstTijd;

	@Element(name="ActueleAankomstTijd")
	private String actueleAankomstTijd;

	@Element(name="Status")
	private String status;

	@Element(name="VertrekVertraging", required=false)
	private String vertrekVertraging;

	@Element(name="AankomstVertraging", required=false)
	private String aankomstVertraging;

	@ElementList(name="ReisDeelNSApi", inline=true)
	private List<ReisDeelNSApi> reisdelen;

	public Integer getAantalOverstappen() {
		return aantalOverstappen;
	}

	public String getGeplandeReistijd() {
		return geplandeReistijd;
	}

	public String getActueleReisTijd() {
		return actueleReisTijd;
	}

	public String getGeplandeVertrekTijd() {
		return geplandeVertrekTijd;
	}

	public String getActueleVertrektijd() {
		return actueleVertrektijd;
	}

	public String getGeplandeAankomstTijd() {
		return geplandeAankomstTijd;
	}

	public String getActueleAankomstTijd() {
		return actueleAankomstTijd;
	}

	public String getStatus() {
		return status;
	}

	public List<ReisDeelNSApi> getReisdelen() {
		return reisdelen;
	}

	public List<Melding> getMeldingen() {
		return meldingen;
	}

	public Boolean getOptimaal() {
		return optimaal;
	}
}
