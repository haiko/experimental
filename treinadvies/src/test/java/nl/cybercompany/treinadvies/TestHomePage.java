package nl.cybercompany.treinadvies;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletContext;

import nl.cybercompany.treinadvies.business.ReisplannerService;
import nl.cybercompany.treinadvies.domain.StationList;
import nl.cybercompany.treinadvies.web.application.TreinAdviesApplication;
import nl.cybercompany.treinadvies.web.pages.HomePage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 * Simple test using the WicketTester
 */
@RunWith(MockitoJUnitRunner.class)
public class TestHomePage
{
	private WicketTester tester;


	private TreinAdviesApplication app;

	@Mock
	private ReisplannerService reisplannerService;

	@Before
	public void setUp() throws IOException, Exception
	{
		MockitoAnnotations.initMocks(this);
		tester = new WicketTester(app = new TreinAdviesApplication(){

			/**
			 * adjust the servletcontext.
			 */
			@Override
			public ServletContext getServletContext() {
				ServletContext servletContext = super.getServletContext();
				XmlWebApplicationContext  applicationContext = new XmlWebApplicationContext();
				applicationContext.setConfigLocation("classpath:applicationContext.xml");
				applicationContext.setServletContext(servletContext);
				applicationContext.refresh();
				servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);

				return servletContext;
			}

		});

		//replace Spring bean with Mock
		app.setReisplannerService(reisplannerService);

		Serializer serializer = new Persister();
		StationList stationList = serializer.read(StationList.class, new ClassPathResource("ns-api-stations.xml").getInputStream());

		when(reisplannerService.getStationList()).thenReturn(stationList);
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		verify(reisplannerService).getStationList();
	}
}
