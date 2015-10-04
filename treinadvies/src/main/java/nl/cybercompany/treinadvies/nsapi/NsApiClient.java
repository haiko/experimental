/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;

import org.joda.time.DateTime;

/**
 * Communicates with NS API service.
 *
 * @author haiko
 *
 */
public interface NsApiClient {


	/**
	 *  Haal de stationslijst op.
	 */
	public StationList fetchStationsList();

	/**
	 * Haal {@link ReisAdviesNSApi} op voor gegeven vertrek en aankomst
	 *
	 * @param vertrek {@link Station} van vertrek.
	 * @param aankomst {@link Station} van aankomst.
	 * @param tijd tijd van de reis als {@link DateTime}.
	 * @return een {@link ReisAdviesNSApi}.
	 * @throws NoResultsException in case of errors.
	 */
	public ReisAdviesNSApi getReisAdvies(Station vertrek, Station aankomst, DateTime tijd) throws NoResultException;

}
