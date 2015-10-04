/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import static nl.cybercompany.treinadvies.util.Checks.nullCheck;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * Tijd en Lokatie.
 *
 * @author haiko
 *
 */
public class Punt implements Serializable{

	private DateTime vertrekTijd;

	private Station station;

	public static Punt createPunt(String tijd, Station station) {
		return new Punt(DateTime.parse(tijd), station);
	}

	Punt(DateTime tijd, Station station) {
		nullCheck(tijd, station);
		this.station = station;
		this.vertrekTijd = tijd;
	}

	static Punt createPunt(DateTime tijd, Station station) {
		return new Punt(tijd, station);
	}

	public DateTime getVertrekTijd() {
		return vertrekTijd;
	}

	public Station getStation() {
		return station;
	}
}
