/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nl.cybercompany.treinadvies.nsapi.Melding;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Een mogelijkheid voor een Reis van {@link Station} A naar B.
 * 
 * @author haiko
 * 
 */
public class ReisMogelijkheid implements Serializable {

	public static ReisMogelijkheid createReisMogelijkheid(
			List<ReisDeel> reisDelen, DateTime vertrekTijd,
			DateTime aankomstTijd, List<Melding> meldingen) {
		if (meldingen == null) {
			meldingen = new ArrayList<Melding>();
		}
		return new ReisMogelijkheid(reisDelen, vertrekTijd, aankomstTijd,
				meldingen);
	}

	public static ReisMogelijkheid createReisMogelijkheid(
			List<ReisDeel> reisDelen, DateTime vertrekTijd,
			DateTime aankomstTijd) {
		return new ReisMogelijkheid(reisDelen, vertrekTijd, aankomstTijd,
				new ArrayList<Melding>());
	}

	private ReisMogelijkheid(List<ReisDeel> delen, DateTime vertrektijd,
			DateTime aankomstTijd, List<Melding> meldingen) {
		this.delen = delen;
		this.vertrekTijd = vertrektijd;
		this.aankomstTijd = aankomstTijd;
		this.meldingen = meldingen;
	}

	private DateTime vertrekTijd;

	private DateTime aankomstTijd;

	private List<Melding> meldingen;

	private List<ReisDeel> delen;

	ReisDeel getFirstReisDeel() {
		return delen.get(0);
	}

	ReisDeel getLastReisdeel() {
		return delen.get(delen.size());
	}

	public String getVertrekTijdInUurEnMinuten() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
		return formatter.print(vertrekTijd);
	}

	public String getAankomstTijdInUurEnMinuten() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
		return formatter.print(aankomstTijd);
	}

	public DateTime getVertrekTijd() {
		return vertrekTijd;
	}

	public DateTime getAankomstTijd() {
		return aankomstTijd;
	}

	public List<ReisDeel> getDelen() {
		return delen;
	}

	public String getReisTijd() {

		Period reistijd = new Period(vertrekTijd, aankomstTijd,
				PeriodType.dayTime());

		StringBuffer reistijdFormat = new StringBuffer()
				.append(reistijd.getHours())
				.append(":")
				.append(ParseUtil.formatToTwoCharacterNumber(reistijd
						.getMinutes()));
		return reistijdFormat.toString();
	}

	public List<Melding> getMeldingen() {
		return meldingen;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("VT:").append(vertrekTijd.toString())
				.append("|AT:").append(aankomstTijd.toString())
				.append("|nrDelen:").append(delen.size()).toString();
	}

}
