package nl.cybercompany.treinadvies.nsapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Test the integration with configuration of {@link RestTemplate} and Simple XML.
 *
 * @author haiko
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class NsApiClientIntegretionTest {

	@Autowired
	private NsApiClientImpl nsApiClient;

	@Before
	public void setUp() throws Exception {
	}

	//@Test
	public void testFetchStationsList() {
		StationList stationList = nsApiClient.fetchStationsList();
		assertNotNull(stationList);
		assertTrue(stationList.getStations().size() > 0);
	}

	@Test
	public void testGetReisAdvies() throws NoResultException {
		Station vertrek = new Station("Amsterdam Centraal", null, null);
		Station aankomst = new Station("Utrecht Centraal", null, null);
		//ReisAdviesNSApi reisAdvies = nsApiClient.getReisAdvies(vertrek, aankomst, new DateTime());

		//assertNotNull(reisAdvies);
		//assertTrue(reisAdvies.getReisMogelijkheden().size() > 0);
	}

}
