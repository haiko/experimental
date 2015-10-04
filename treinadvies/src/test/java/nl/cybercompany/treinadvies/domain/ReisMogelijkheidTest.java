package nl.cybercompany.treinadvies.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static nl.cybercompany.treinadvies.util.DateUtil.*;

import java.util.ArrayList;

import nl.cybercompany.treinadvies.nsapi.Melding;

import org.junit.Test;

public class ReisMogelijkheidTest {

	//@Test
	public void testCreateReisMogelijkheid() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetFirstReisDeel() {
		fail("Not yet implemented");
	}

	//@Test
	public void testGetLastReisdeel() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertrekTijd() {
		ReisMogelijkheid reisMogelijkheid = ReisMogelijkheid.createReisMogelijkheid(null,parse("2012-10-20T17:38:00+0200"), parse("2012-10-20T18:39:00+0200"), new ArrayList<Melding>());
		assertEquals("17:38", reisMogelijkheid.getVertrekTijdInUurEnMinuten());
	}

	@Test
	public void testGetAankomstTijd() {
		ReisMogelijkheid reisMogelijkheid = ReisMogelijkheid.createReisMogelijkheid(null, parse("2012-10-20T17:38:00+0200"), parse("2012-10-20T18:39:00+0200"), new ArrayList<Melding>()
				);
		assertEquals("18:39", reisMogelijkheid.getAankomstTijdInUurEnMinuten());
	}

	//@Test
	public void testGetDelen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReisTijd() {
		ReisMogelijkheid reisMogelijkheid = ReisMogelijkheid.createReisMogelijkheid(null, parse("2012-10-20T17:38:00+0200"), parse("2012-10-20T18:39:00+0200"), new ArrayList<Melding>());
		assertEquals("1:01", reisMogelijkheid.getReisTijd());
	}

}
