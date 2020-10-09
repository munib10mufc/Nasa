package com.nasa.eonet.nasa.externalcommhandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nasa.eonet.nasa.beans.external.LocationIqResponse;
import com.nasa.eonet.nasa.beans.external.NasaCategoriesResponse;
import com.nasa.eonet.nasa.beans.external.NasaCommonResponse;
import com.nasa.eonet.nasa.beans.external.NasaDisastersResponse;
import com.nasa.eonet.nasa.beans.external.NasaSourcesResponse;
import com.nasa.eonet.nasa.utils.Constants;

@RestController
public class ExternalRequestHandler {
	private static final Logger logger = LogManager.getLogger(ExternalRequestHandler.class);

	public static NasaCommonResponse getSourcesList(String url) {
		NasaCommonResponse sourcesResponse = null;
		logger.debug(logger.isDebugEnabled() ? "Going to call " + url + " to fetch sources information..." : null);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate = configureMessageConcertor(restTemplate);
		sourcesResponse = restTemplate.getForObject(url, NasaSourcesResponse.class);
		return sourcesResponse;
	}

	public static NasaCommonResponse getCategoriesList(String url) {
		NasaCommonResponse sourcesResponse = null;
		logger.debug(logger.isDebugEnabled() ? "Going to call " + url + " to fetch sources information..." : null);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate = configureMessageConcertor(restTemplate);
		sourcesResponse = restTemplate.getForObject(url, NasaCategoriesResponse.class);
		return sourcesResponse;
	}

	public static LocationIqResponse getLocationCoordinates(String url, String token, String locationName) {
		LocationIqResponse[] locationIqResponseArr = null;
		LocationIqResponse locationIqResponse = null;

		StringBuilder sb = new StringBuilder(url);
		RestTemplate restTemplate = new RestTemplate();
		try {
			logger.debug(logger.isDebugEnabled()
					? "Going to call " + url + " to fetch location information for name = " + locationName + "..."
					: null);
			sb.append(Constants.KEY_KEYWORD + token);
			sb.append(Constants.COUNTRY_KEYWORD + locationName);

			restTemplate = configureMessageConcertor(restTemplate);

			locationIqResponseArr = restTemplate.getForObject(sb.toString(), LocationIqResponse[].class);
			if (null != locationIqResponseArr) {
				return locationIqResponseArr[0];
			}
		} catch (HttpClientErrorException e) {
			logger.error("Exception occured with provided url details, preparing error response");
			locationIqResponse = new LocationIqResponse();
			locationIqResponse.setError("Unable to geocode requested parameters");
			return locationIqResponse;
		}
		return null;
	}

	public static NasaDisastersResponse getDisasterInfo(String url) {
		NasaDisastersResponse disasterResponse = null;
		logger.debug(logger.isDebugEnabled() ? "Going to call " + url + " to fetch disasters information..." : null);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate = configureMessageConcertor(restTemplate);
		disasterResponse = restTemplate.getForObject(url, NasaDisastersResponse.class);
		return disasterResponse;
	}

	private static RestTemplate configureMessageConcertor(RestTemplate restTemplate) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

}
