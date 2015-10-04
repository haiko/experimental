/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import static nl.cybercompany.treinadvies.util.Checks.nullCheck;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Reis vraag voor planner.
 *
 * @author haiko
 *
 */
public class ReisVraag implements Serializable {

	public ReisVraag(){}

	public static ReisVraag createSimpleReisVraag(String vertrekStation, String aankomstStation, DateTime tijd){
		nullCheck(vertrekStation, aankomstStation, tijd);
		ReisVraag reisVraag = new ReisVraag();
		reisVraag.setVertrekStation(vertrekStation);
		reisVraag.setAankomstStation(aankomstStation);
		reisVraag.setTijd(tijd);
		return reisVraag;
	}

	private String vertrekStation;

	private String aankomstStation;

	private DateTime tijd;

	public String getVertrekStation() {
		return vertrekStation;
	}

	public void setVertrekStation(String vertrekStation) {
		this.vertrekStation = vertrekStation;
	}

	public String getAankomstStation() {
		return aankomstStation;
	}

	public void setAankomstStation(String aankomstStation) {
		this.aankomstStation = aankomstStation;
	}

	public DateTime getTijd() {
		return tijd;
	}

	public void setTijd(DateTime tijd) {
		this.tijd = tijd;
	}

	@Override
	public String toString() {
		return String.format("Reisvraag: van %s naar %s om %s", vertrekStation, aankomstStation, ISODateTimeFormat.basicDate().print(tijd));
	}



}
