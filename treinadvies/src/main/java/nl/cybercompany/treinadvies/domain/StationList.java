/**
 * 
 */
package nl.cybercompany.treinadvies.domain;

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * @author haiko
 *
 */
@Root(name="stations")
public class StationList implements Serializable {
	
	@ElementList(inline=true)
	private List<Station> stations;


	public StationList() {	}


	public StationList(List<Station> stations) {
		this.stations = stations;
	}


	public List<Station> getStations() {
		return stations;
	}


	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
}
