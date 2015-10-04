/**
 *
 */
package nl.cybercompany.treinadvies.business;

import static nl.cybercompany.treinadvies.util.DateUtil.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.Punt;
import nl.cybercompany.treinadvies.domain.ReisAdvies;
import nl.cybercompany.treinadvies.domain.ReisDeel;
import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;
import nl.cybercompany.treinadvies.domain.ReisVraag;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;
import nl.cybercompany.treinadvies.domain.StationTransferPunt;
import nl.cybercompany.treinadvies.domain.Vervoer;
import nl.cybercompany.treinadvies.nsapi.NsApiClient;
import nl.cybercompany.treinadvies.nsapi.ReisDeelNSApi;
import nl.cybercompany.treinadvies.nsapi.ReisMogelijkheidNSApi;
import nl.cybercompany.treinadvies.nsapi.ReisStop;
import nl.cybercompany.treinadvies.util.Checks;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.ehcache.annotations.Cacheable;

/**
 * @author haiko
 *
 */
public class ReisplannerServiceImpl implements ReisplannerService {

	@Autowired
	private NsApiClient nsApiClient;

	/*
	 * (non-Javadoc)
	 *
	 * @see nl.cybercompany.treinadvies.web.pages.application.business.ReisplannerService#planReis(nl.
	 * cybercompany.treinadvies.domain.Station,
	 * nl.cybercompany.treinadvies.web.pages.application.domain.Station)
	 */
	@Override
	@Cacheable(cacheName="reisadviesCache")
	public ReisAdvies planReis(ReisVraag reisVraag) throws NoResultException {
		return convert(nsApiClient.getReisAdvies(Station.createSimple(reisVraag.getVertrekStation()
				), Station.createSimple(reisVraag.getAankomstStation()), reisVraag.getTijd()), reisVraag);
	}

	@Override
	public StationList getStationList() {
		return nsApiClient.fetchStationsList();
	}

	private ReisAdvies convert(
			nl.cybercompany.treinadvies.nsapi.ReisAdviesNSApi reisAdviesNSApi, ReisVraag reisVraag) throws NoResultException {

		//check results
		if(Checks.isNull(reisAdviesNSApi) || Checks.isNull(reisAdviesNSApi.getReisMogelijkheden()) || reisAdviesNSApi.getReisMogelijkheden().isEmpty()){
			throw new NoResultException("No results found for\n:" + reisVraag);
		}

		List<ReisMogelijkheidNSApi> reisMogelijkhedenNsApi = reisAdviesNSApi
				.getReisMogelijkheden().subList(1, (reisAdviesNSApi.getReisMogelijkheden().size()));
		List<ReisMogelijkheid> reisMogelijkhedenDomain = new ArrayList<ReisMogelijkheid>();

		for (Iterator iterator = reisMogelijkhedenNsApi.iterator(); iterator
				.hasNext();) {
			ReisMogelijkheidNSApi reisMogelijkheidNSApi = (ReisMogelijkheidNSApi) iterator
					.next();

			reisMogelijkhedenDomain.add(convert(reisMogelijkheidNSApi));
		}

		return ReisAdvies.createReisAdvies(reisMogelijkhedenDomain, reisVraag);
	}

	/**
	 * Convert {@link ReisMogelijkheidNSApi} to {@link ReisMogelijkheidNSApi}
	 *
	 * @param reisMogelijkheidNSApi
	 * @return
	 */
	private ReisMogelijkheid convert(
			ReisMogelijkheidNSApi reisMogelijkheidNSApi) {


		List<ReisDeelNSApi> reisDelenNSApi = reisMogelijkheidNSApi.getReisdelen();
		List<ReisDeel> reisDelenDomain = new ArrayList<ReisDeel>();

		for (Iterator iteratorRD = reisDelenNSApi.iterator(); iteratorRD.hasNext();) {
			ReisDeelNSApi reisDeelNSApi = (ReisDeelNSApi) iteratorRD.next();
			ReisDeel reisDeel = convert(reisDeelNSApi);
			reisDelenDomain.add(reisDeel);
		}
		return  ReisMogelijkheid.createReisMogelijkheid(reisDelenDomain, parse(reisMogelijkheidNSApi.getActueleVertrektijd()), parse(reisMogelijkheidNSApi.getActueleAankomstTijd()), reisMogelijkheidNSApi.getMeldingen());

	}

	/**
	 * Create {@link ReisDeel} from {@link ReisDeelNSApi}.
	 *
	 * @param reisDeelNSApi
	 * @return
	 */
	private ReisDeel convert(ReisDeelNSApi reisDeelNSApi) {
		return ReisDeel.createReisDeel(convert(reisDeelNSApi.getReisStops()), Vervoer.create(reisDeelNSApi.getVervoerderType(), reisDeelNSApi.getVervoerder()));
	}

	/**
	 * Create {@link List} of {@link Punt} from {@link List} of {@link ReisStop}.
	 *
	 * @param reisStops
	 * @return
	 */
	private List<Punt> convert(List<ReisStop> reisStops) {
		List<Punt> punten = new ArrayList<Punt>();

		for (Iterator iterator = reisStops.iterator(); iterator.hasNext();) {
			ReisStop reisStop = (ReisStop) iterator.next();
			Punt punt;

			if(reisStop.getSpoor() != null){
				punt = StationTransferPunt.create(reisStop.getTijd(), Station.createSimple(reisStop.getNaam()), reisStop.getSpoor().getValue());
			}
			else {
				punt= Punt.createPunt(reisStop.getTijd(), Station.createSimple(reisStop.getNaam()));
			}

			punten.add(punt);
		}

		return punten;
	}
}
