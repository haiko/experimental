/**
 *
 */
package nl.cybercompany.treinadvies.business;

import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.ReisAdvies;
import nl.cybercompany.treinadvies.domain.ReisVraag;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;
import nl.cybercompany.treinadvies.nsapi.ReisAdviesNSApi;

/**
 * Plant een reis met opgegeven criteria.
 *
 * @author haiko
 *
 */
public interface ReisplannerService {

	/**
	 * Plant reis voor opgegeven vertrek en aankomst
	 *
	 * @param reisVraag een {@link ReisVraag}.
	 * @return {@link ReisAdviesNSApi} voor opgegeven vertrek {@link Station} en aankomst {@link Station}.
	 * @throws NoResultException als er geen resultaten worden gevonden.
	 */
	public ReisAdvies planReis(ReisVraag reisVraag) throws NoResultException;

	/**
	 * Retrieve all {@link Station}s.
	 *
	 *
	 * @return a {@link StationList} with all {@link Station}s.
	 */
	public StationList getStationList();

}
