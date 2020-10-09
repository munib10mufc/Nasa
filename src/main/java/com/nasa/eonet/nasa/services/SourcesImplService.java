package com.nasa.eonet.nasa.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;
import com.nasa.eonet.nasa.beans.dao.ExternalApi;
import com.nasa.eonet.nasa.beans.external.NasaSourcesResponse;
import com.nasa.eonet.nasa.beans.success.SourcesResponse;
import com.nasa.eonet.nasa.dao.services.ExternalApiService;
import com.nasa.eonet.nasa.externalcommhandler.ExternalRequestHandler;
import com.nasa.eonet.nasa.utils.Constants;
import com.nasa.eonet.nasa.utils.Utils;

@Component
public class SourcesImplService implements ServiceHandlerInterface {

	private static final Logger logger = LogManager.getLogger(SourcesImplService.class);
	@Autowired
	private ExternalApiService externalApiService;

	@Override
	public CommonResponse handleRequest(CommonRequest req) {
		NasaSourcesResponse nasaResponse = null;
		SourcesResponse sourcesResponse = null;
		try {
			sourcesResponse = new SourcesResponse(Constants.SUCCESS_RESPONSE, Constants.SUCCESS_RESPONSE_DESC);
			ExternalApi sourcesApi = externalApiService.getExternalApi(Constants.SOURCERS_SERVICE);
			if (null == sourcesApi) {
				sourcesResponse = Utils.prepareErrorResponse(sourcesResponse, Constants.ERROR_RESPONSE,
						"Required configuration not found to execute sources service");
				return sourcesResponse;
			}
			nasaResponse = (NasaSourcesResponse) ExternalRequestHandler.getSourcesList(sourcesApi.getUrl());
			if (null != nasaResponse) {
				sourcesResponse.setAvailableSources(nasaResponse.getSources());
			}
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			sourcesResponse = Utils.prepareErrorResponse(sourcesResponse, Constants.EXCEPTION_RESPONSE,
					"Exception occured, please check with admin");
			return sourcesResponse;
		}
		return sourcesResponse;

	}

	@Override
	public String getServiceName() {
		return Constants.SOURCERS_SERVICE;
	}

}
