/**
 *
 */
package nl.cybercompany.treinadvies.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import nl.cybercompany.treinadvies.domain.ReisAdvies;
import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;
import nl.cybercompany.treinadvies.domain.ReisVraag;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.nsapi.NsApiClient;
import nl.cybercompany.treinadvies.nsapi.ReisAdviesNSApi;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.core.io.ClassPathResource;

/**
 * @author haiko
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ReisplannerServiceTest {


	@Mock
	NsApiClient nsApiClient;

	@InjectMocks
	ReisplannerServiceImpl reisplannerServiceImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test method for {@link nl.cybercompany.treinadvies.web.pages.application.business.ReisplannerServiceImpl#planReis(nl.cybercompany.treinadvies.web.pages.application.domain.Station, nl.cybercompany.treinadvies.web.pages.application.domain.Station)}.
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testPlanReis() throws IOException, Exception {
		// setup
		Serializer serializer = new Persister();
	    ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("reisadvies-amsterdam-naar-heerlendekissel.xml").getInputStream());


		// behavior
		when(nsApiClient.getReisAdvies(any(Station.class), any(Station.class), any(DateTime.class))).thenReturn(advies);


		//test
		ReisVraag reisVraag = ReisVraag.createSimpleReisVraag("asd", "rtd", new DateTime());
		ReisAdvies  reisAdvies = reisplannerServiceImpl.planReis(reisVraag);

		//verify
		verify(nsApiClient).getReisAdvies(any(Station.class), eq(Station.createSimple("rtd")), any(DateTime.class));
		assertNotNull(reisAdvies);
		assertEquals(11, reisAdvies.getReisMogelijkheden().size());
		List<ReisMogelijkheid> reisMogelijkheden = reisAdvies.getReisMogelijkheden();

		ReisMogelijkheid laatste = reisMogelijkheden.get(10);

		assertNotNull(laatste);

		assertEquals(2, laatste.getDelen().size());

	}

}
