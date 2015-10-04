package nl.cybercompany.treinadvies.web.components;

import static org.junit.Assert.*;
import static nl.cybercompany.treinadvies.util.DateUtil.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import nl.cybercompany.treinadvies.domain.Punt;
import nl.cybercompany.treinadvies.domain.ReisDeel;
import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.Vervoer;

import org.apache.wicket.Component;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.WicketTester;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ReisMogelijkheidPanelTest {

	private ReisMogelijkheid reisMogelijkheid;

	private WicketTester tester;

	@Before
	public void setup() {
		tester = new WicketTester();
		Locale local = new Locale("nl");
		DateTime vertrekTijd = new DateTime();
		DateTime aankomstTijd = new DateTime().plusHours(4);

		Punt a = Punt.createPunt("2012-12-04T08:32:00+0100", Station.createSimple("Amsterdam Centraal"));
		Punt b = Punt.createPunt("2012-12-04T09:32:00+0100", Station.createSimple("Utrecht Centraal"));
		Punt c = Punt.createPunt("2012-12-04T10:32:00+0100", Station.createSimple("Eindhoven"));

		Punt d = Punt.createPunt("2012-12-04T10:37:00+0100", Station.createSimple("Eindhoven"));
		Punt e = Punt.createPunt("2012-12-04T11:32:00+0100", Station.createSimple("Venlo"));
		Punt f = Punt.createPunt("2012-12-04T18:32:00+0100", Station.createSimple("Duisburg"));


		List<Punt> puntenABC = Arrays.asList(new Punt[] {a,b,c});
		List<Punt> puntenDEF = Arrays.asList(new Punt[] {d,e,f});

		ReisDeel reisDeel1 = ReisDeel.createReisDeel(puntenABC, Vervoer.create("intercity", "NS"));
        ReisDeel reisDeel2 = ReisDeel.createReisDeel(puntenDEF, null);

        List<ReisDeel> reisDelen = Arrays.asList(new ReisDeel[]{reisDeel1, reisDeel2});
	    reisMogelijkheid = ReisMogelijkheid.createReisMogelijkheid(reisDelen, vertrekTijd , aankomstTijd);
	}

	/**
	 * Test wanneer er geen vervoerder is.
	 */
	//@Test
	//TODO fix test
	public void testReisMogelijkheidPanelGeenVervoerder() {
		Component reisMogelijkheidPanel = tester.startComponent(new ReisMogelijkheidPanel("reisMogelijkheid", new  Model(reisMogelijkheid)));

		tester.assertComponent("reisMogelijkheid", ReisMogelijkheidPanel.class);

		tester.assertContains("Eindhoven");
	}

	//@Test
	public void testOnEventIEventOfQ() {
		fail("Not yet implemented");
	}

}
