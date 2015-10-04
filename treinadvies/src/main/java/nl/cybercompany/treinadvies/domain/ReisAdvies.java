package nl.cybercompany.treinadvies.domain;

import java.util.List;

import nl.cybercompany.treinadvies.nsapi.ReisAdviesNSApi;


/**
 * Bevat {@link ReisAdviesNSApi} van punt A naar B.
 *
 * @author haiko
 *
 */
public class ReisAdvies {

	private ReisVraag reisVraag;

	private Station vertrek;

	private Station aankomst;

	private List<ReisMogelijkheid> reisMogelijkheden;

	private ReisAdvies(List<ReisMogelijkheid> mogelijkheden) {

		this.reisMogelijkheden = mogelijkheden;
		List<ReisDeel> delenEersteMogelijkheid = mogelijkheden.get(0).getDelen();
		this.vertrek = delenEersteMogelijkheid.get(0).getVertrek().getStation();
		this.aankomst = delenEersteMogelijkheid.get(delenEersteMogelijkheid.size() - 1).getAankomst().getStation();

	}

	public static ReisAdvies createReisAdvies(List<ReisMogelijkheid> mogelijkheden, ReisVraag reisVraag) {
		ReisAdvies advies =  new ReisAdvies(mogelijkheden);
		advies.reisVraag  = reisVraag;
		return advies;
	}

	public Station getVertrek() {
		return vertrek;
	}

	public Station getAankomst() {
		return aankomst;
	}

	public List<ReisMogelijkheid> getReisMogelijkheden() {
		return reisMogelijkheden;
	}

	public ReisVraag getReisVraag() {
		return reisVraag;
	}
}
