/**
 *
 */
package nl.cybercompany.treinadvies.domain;

import org.joda.time.DateTime;

/**
 * Reiziger moet in- of uitstappen.
 *
 * @author haiko
 *
 */
public class StationTransferPunt extends Punt {

	public static StationTransferPunt create(String tijd, Station station, String spoor){
		return new StationTransferPunt(DateTime.parse(tijd), station, spoor);
	}

	StationTransferPunt(DateTime tijd, Station station, String spoor) {
		super(tijd, station);
		this.spoor = spoor;
	}

	private String spoor;

	public String getSpoor() {
		return spoor;
	}
}
