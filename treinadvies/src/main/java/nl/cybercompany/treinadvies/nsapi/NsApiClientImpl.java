/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import static nl.cybercompany.treinadvies.nsapi.UriUtils.addQueryParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.math.RandomUtils;
import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


/**
 * @author haiko
 *
 */
public class NsApiClientImpl implements NsApiClient {

	private static Logger logger =  LoggerFactory.getLogger(NsApiClientImpl.class);

    static String stationList = "/ns-api-stations";

	static String planner= "/ns-api-treinplanner";

	@Value("${nsapi.url}")
	private String url;

	@Value("${nsapi.username}")
	private String username;

	@Value("${nsapi.password}")
	private String password;


	@Value("${nsapi.username2}")
	private String username2;

	@Value("${nsapi.password2}")
	private String password2;

	@Autowired
	private RestTemplate restTemplate;



	/* (non-Javadoc)
	 * @see nl.cybercompany.treinadvies.web.pages.application.business.NsApiClient#fetchStationsList()
	 */
	@Override
	public StationList fetchStationsList() {

		HttpHeaders requestHeaders = createHeaders();

		// Populate the headers in an HttpEntity object to use for the request
		HttpEntity<StationList> requestEntity = new HttpEntity<StationList>(requestHeaders);

		ResponseEntity<StationList> responseEntity = restTemplate.exchange(url.concat(stationList), HttpMethod.GET, requestEntity, StationList.class);
		return responseEntity.getBody();
	}


	/* (non-Javadoc)
	 * @see nl.cybercompany.treinadvies.web.pages.application.nsapi.NsApiClient#getReisAdvies(nl.cybercompany.treinadvies.web.pages.application.domain.Station, nl.cybercompany.treinadvies.web.pages.application.domain.Station)
	 */
	@Override
	public ReisAdviesNSApi getReisAdvies(Station vertrek, Station aankomst, DateTime time) throws NoResultException {
		HttpHeaders requestHeaders = createHeaders();

		String tijd = ISODateTimeFormat.basicDate().print(time);

		Map<String, String> params = new HashMap<String, String>();
		params.put("fromStation", vertrek.getName());
		params.put("toStation", aankomst.getName());
		params.put("previousAdvices", Integer.toString(2));
		params.put("nextAdvices", Integer.toString(3));
	//	params.put("dateTime", tijd);

		HttpEntity<ReisAdviesNSApi> requestEntity = new HttpEntity<ReisAdviesNSApi>(requestHeaders);


		ResponseEntity<ReisAdviesNSApi> responseEntity;
		try {
			Stopwatch watch = SimonManager.getStopwatch("NSAPI-call");

			Split split = watch.start();

			responseEntity = restTemplate.exchange(addQueryParams(url.concat(planner), params), HttpMethod.GET, requestEntity, ReisAdviesNSApi.class);

			split.stop();

			logger.info("NSAPI-call:" + watch);
		} catch (RestClientException e) {
			logger.info(e.getMessage(),e);
			throw new NoResultException(e.getMessage(), e);
		}
		return responseEntity.getBody();
	}

	/**
	 * Create Headers for request NSAPI.
	 *
	 * @return {@link HttpHeaders} for request to NSAPI.
	 */
	private HttpHeaders createHeaders() {
		// Set the Accept header for "application/xml"
		HttpHeaders requestHeaders = new HttpHeaders();
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.TEXT_XML);
		requestHeaders.add("Authorization", new StringBuffer("Basic").append(" ").append(getCredentials()).toString());
		return requestHeaders;
	}

	private String getCredentials() {
		String source;

		if(RandomUtils.nextBoolean()){
			 source = new StringBuffer().append(username).append(":").append(password).toString();
		}
		else {
			 source = new StringBuffer().append(username2).append(":").append(password2).toString();
		}

		return Base64.encodeBase64URLSafeString(source.getBytes());
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
