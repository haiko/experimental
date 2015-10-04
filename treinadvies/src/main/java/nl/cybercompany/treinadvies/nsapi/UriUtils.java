/**
 *
 */
package nl.cybercompany.treinadvies.nsapi;

import java.util.Iterator;
import java.util.Map;

/**
 * Utility class for creating url's.
 *
 *
 * @author haiko
 *
 */
public class UriUtils {

	/**
	 * Add params of given {@link Map} to baseUrl.
	 *
	 * @param baseUrl url of a service.
	 * @param params a {@link Map} with params.
	 * @return url with query params.
	 */
	public static String addQueryParams(String baseUrl, Map<String, String> params){
		StringBuffer sb = new StringBuffer(baseUrl);

		if(!baseUrl.contains("?")){
			sb.append("?");
		}

		Iterator<String> it = params.keySet().iterator();

		while(it.hasNext()){
			String key = it.next();

			String value=params.get(key);

			if(value == null){
				throw new IllegalArgumentException("value is null for key:" + key);
			}

			sb.append(key).append("=").append(value);

			if(it.hasNext()){
			  sb.append("&");
			}
		}

		return sb.toString();
	}

}
