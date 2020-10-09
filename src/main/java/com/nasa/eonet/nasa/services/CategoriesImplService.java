package com.nasa.eonet.nasa.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;
import com.nasa.eonet.nasa.beans.dao.ExternalApi;
import com.nasa.eonet.nasa.beans.external.NasaCategoriesResponse;
import com.nasa.eonet.nasa.beans.success.CategoriesResponse;
import com.nasa.eonet.nasa.dao.services.ExternalApiService;
import com.nasa.eonet.nasa.externalcommhandler.ExternalRequestHandler;
import com.nasa.eonet.nasa.utils.Constants;
import com.nasa.eonet.nasa.utils.Utils;

@Component
public class CategoriesImplService implements ServiceHandlerInterface {

	private static final Logger logger = LogManager.getLogger(CategoriesImplService.class);
	@Autowired
	private ExternalApiService externalApiService;

	@Override
	public CommonResponse handleRequest(CommonRequest req) {
		NasaCategoriesResponse nasaResponse = null;
		CategoriesResponse categoriesResponse = null;
		try {
			categoriesResponse = new CategoriesResponse(Constants.SUCCESS_RESPONSE, Constants.SUCCESS_RESPONSE_DESC);
			ExternalApi categoriesApi = externalApiService.getExternalApi(Constants.CATEGORIES_SERVICE);
			if (null == categoriesApi) {
				categoriesResponse = Utils.prepareErrorResponse(categoriesResponse, Constants.ERROR_RESPONSE,
						"Required configuration not found to execute categories service");
				return categoriesResponse;
			}
			nasaResponse = (NasaCategoriesResponse) ExternalRequestHandler.getCategoriesList(categoriesApi.getUrl());
			if (null != nasaResponse) {
				categoriesResponse.setAvailableCategories(nasaResponse.getCategories());
			}
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			categoriesResponse = Utils.prepareErrorResponse(categoriesResponse, Constants.EXCEPTION_RESPONSE,
					"Exception occured, please check with admin");
			return categoriesResponse;
		}
		return categoriesResponse;

	}

	@Override
	public String getServiceName() {
		return Constants.CATEGORIES_SERVICE;
	}

}
