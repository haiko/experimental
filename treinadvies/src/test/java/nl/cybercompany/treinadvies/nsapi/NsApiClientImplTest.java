/**
 * 
 */
package nl.cybercompany.treinadvies.nsapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import nl.cybercompany.treinadvies.domain.StationList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author haiko
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class NsApiClientImplTest {

	@Autowired
	private NsApiClientImpl nsApiClientImpl;

	@Mock
	private RestTemplate mockRestTemplate;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		nsApiClientImpl.setRestTemplate(mockRestTemplate);
	}

	/**
	 * Test method for
	 * {@link nl.cybercompany.treinadvies.web.pages.application.nsapi.NsApiClientImpl#fetchStationsList()}
	 * .
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testFetchStationsList() throws IOException, Exception {
		// create testdata
		Serializer serializer = new Persister();
		StationList stationList = serializer.read(StationList.class,
				new ClassPathResource("ns-api-stations.xml").getInputStream());

		ResponseEntity<StationList> responseEntity = mock(ResponseEntity.class);

		// set behaviour
		when(responseEntity.getBody()).thenReturn(stationList);
		when(
				mockRestTemplate.exchange(anyString(), any(HttpMethod.class),
						any(HttpEntity.class), eq(StationList.class)))
				.thenReturn(responseEntity);

		// test
		StationList list = nsApiClientImpl.fetchStationsList();

		// verify
		assertNotNull(list);
		assertNotNull(list.getStations());
		assertTrue(list.getStations().size() > 100);
		
		verify(mockRestTemplate, times(1)).exchange(contains("/ns-api-stations"), eq(HttpMethod.GET
				), any(HttpEntity.class), eq(StationList.class));
	}

	/**
	 * Test method for
	 * {@link NsApiClient#getReisAdvies(nl.cybercompany.treinadvies.web.pages.application.domain.Station, nl.cybercompany.treinadvies.web.pages.application.domain.Station)}
	 * .
	 * 
	 * @throws Exception
	 * @throws IOException
	 */
	@Test
	public void testGetReisAdvies() throws IOException, Exception {
		// create testdata
		Serializer serializer = new Persister();
		ReisAdviesNSApi advies = serializer.read(ReisAdviesNSApi.class,
				new ClassPathResource(
						"reisadvies-amsterdam-naar-heerlendekissel.xml")
						.getInputStream());

		ResponseEntity<ReisAdviesNSApi> responseEntity = mock(ResponseEntity.class);

		// set behavior
		when(responseEntity.getBody()).thenReturn(advies);
		when(
				mockRestTemplate.exchange(anyString(), eq(HttpMethod.GET),
						any(HttpEntity.class), eq(ReisAdviesNSApi.class)))
				.thenReturn(responseEntity);
	}

}
