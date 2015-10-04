/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

/**
 * Test correct mapping XML response NS API.
 *
 * @author haiko
 *
 */
public class XmlMappingTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test whether {@link ReisAdviesNSApi} is correctly mapped.
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testMappingReisAdvies() throws IOException, Exception {
		// read xml
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("reisadvies-amsterdam-naar-heerlendekissel.xml").getInputStream());

		assertEquals(12, advies.getReisMogelijkheden().size());

		//retrieve derde Reismogelijkheid
		ReisMogelijkheidNSApi reisMogelijkheidNSApi = advies.getReisMogelijkheden().get(2);

		assertEquals("2012-08-29T20:08:00+0200", reisMogelijkheidNSApi.getGeplandeVertrekTijd());
		assertEquals("2012-08-29T22:45:00+0200", reisMogelijkheidNSApi.getActueleAankomstTijd());
		assertEquals(2, reisMogelijkheidNSApi.getReisdelen().size());

		// tweede reisdeel
		ReisDeelNSApi reisDeelNSApi = reisMogelijkheidNSApi.getReisdelen().get(1);

		assertEquals("Veolia", reisDeelNSApi.getVervoerder());
		assertEquals("TRAIN", reisDeelNSApi.getReisSoort());
		assertEquals(2, reisDeelNSApi.getReisStops().size());

		ReisStop reisStop  = reisDeelNSApi.getReisStops().get(0);

		assertEquals("Heerlen", reisStop.getNaam());
		assertEquals("2", StringUtils.trimAllWhitespace(reisStop.getSpoor().getValue()));
	}

	/**
	 * Test whether {@link ReisAdviesNSApi} is correctly mapped.
	 * Empty Spoor element.
	 *
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testMappingReisAdviesAmsterdamToLeeuwarden() throws IOException, Exception {
		// read xml
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("reisadvies-amsterdam-naar-leeuwarden.xml").getInputStream());

		assertNotNull(advies);
	}

	/**
	 * Test whether {@link ReisAdviesNSApi} is correctly mapped.
	 * Empty Id element in {@link Melding}.
	 *
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testMappingReisAdviesAmsterdamAirportToUtrechtCentraal() throws IOException, Exception {
		// read xml
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("reisadvies-amsterdamairport-naar-utrechtcentraal.xml").getInputStream());

		assertNotNull(advies);
	}

	/**
	 * Test whether {@link ReisAdviesNSApi} is correctly mapped.
	 * Meerdere {@link Melding}en in een {@link ReisMogelijkheidNSApi}.
	 *
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testMappingReisAdvies2MeldingenInReisMogelijkheid() throws IOException, Exception {
		// read xml
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("ns-api-treinplanner-2-meldingen-in-reismogelijkheid.xml").getInputStream());

		assertNotNull(advies);
	}

	/**
	 * Test whether {@link ReisAdviesNSApi} is correctly mapped.
	 * Geen Vervoerder aanwezig op een ReisDeel.
	 *
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testMappingReisAdviesGeenVervoerderInReisDeel() throws IOException, Exception {
		// read xml
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class, new ClassPathResource("reisadvies-amsterdam-naar-koln.xml").getInputStream());

		assertNotNull(advies);
	}

}
