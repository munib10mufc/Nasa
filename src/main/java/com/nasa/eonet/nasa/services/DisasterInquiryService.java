package com.nasa.eonet.nasa.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;
import com.nasa.eonet.nasa.beans.dao.ExternalApi;
import com.nasa.eonet.nasa.beans.dao.LocationsInfo;
import com.nasa.eonet.nasa.beans.external.LocationIqResponse;
import com.nasa.eonet.nasa.beans.external.NasaDisastersResponse;
import com.nasa.eonet.nasa.beans.success.DisasterInquiryResponse;
import com.nasa.eonet.nasa.dao.services.ExternalApiService;
import com.nasa.eonet.nasa.dao.services.LocationsInfoService;
import com.nasa.eonet.nasa.externalcommhandler.ExternalRequestHandler;
import com.nasa.eonet.nasa.utils.Constants;
import com.nasa.eonet.nasa.utils.Utils;

@Component
public class DisasterInquiryService implements ServiceHandlerInterface {

	private static final Logger logger = LogManager.getLogger(DisasterInquiryService.class);
	@Autowired
	private ExternalApiService externalApiService;
	@Autowired
	private LocationsInfoService locationsInfoService;

	@Override
	public CommonResponse handleRequest(CommonRequest reqObj) {
		DisasterInquiryResponse disasterInquiryResponse = null;
		LocationsInfo locationsInfo = null;
		LocationIqResponse locationIqResponse = null;
		ExternalApi locationInfoApi = null;
		ExternalApi disasterInfoApi = null;
		String disasterInfoUrl = null;
		NasaDisastersResponse externalDisasterResponse = null;
		try {
			disasterInquiryResponse = new DisasterInquiryResponse(Constants.SUCCESS_RESPONSE,
					Constants.SUCCESS_RESPONSE_DESC);

			if (null != reqObj.getLocationName()) {
				locationsInfo = locationsInfoService.getLocationDetails(reqObj.getLocationName());
				if (null == locationsInfo) {
					locationInfoApi = externalApiService.getExternalApi(Constants.LOCATION_IQ_SERVICE);

					locationIqResponse = ExternalRequestHandler.getLocationCoordinates(locationInfoApi.getUrl(),
							locationInfoApi.getApiKey(), reqObj.getLocationName());

					if (null == locationIqResponse || null != locationIqResponse.getError()) {
						disasterInquiryResponse = Utils.prepareErrorResponse(disasterInquiryResponse,
								Constants.ERROR_RESPONSE, "Locations details couldn't be fetched.");
						return disasterInquiryResponse;
					}
					locationsInfo = mapServerResponseToJava(locationIqResponse, reqObj.getLocationName());
					locationsInfo = locationsInfoService.saveLocation(locationsInfo);
					logger.info(logger.isInfoEnabled() ? "Locations saved with id = " + locationsInfo.getId() : null);
				}
			}

			disasterInfoApi = externalApiService.getExternalApi(Constants.DISASTER_SERVICE);
			disasterInfoUrl = prepareQueryParameters(reqObj, locationsInfo, disasterInfoApi.getUrl());
			externalDisasterResponse = ExternalRequestHandler.getDisasterInfo(disasterInfoUrl);
			if (null != externalDisasterResponse) {
				disasterInquiryResponse = mapResponse(disasterInquiryResponse, externalDisasterResponse);
			}
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			disasterInquiryResponse = Utils.prepareErrorResponse(disasterInquiryResponse, Constants.EXCEPTION_RESPONSE,
					"Exception occured, please check with admin");
			return disasterInquiryResponse;
		}
		return disasterInquiryResponse;

		
	}

	@Override
	public String getServiceName() {
		return Constants.DISASTER_SERVICE;
	}

	private DisasterInquiryResponse mapResponse(DisasterInquiryResponse disasterInquiryResponse,
			NasaDisastersResponse externalResponse) {
		disasterInquiryResponse.setTitle(externalResponse.getTitle());
		disasterInquiryResponse.setDescription(externalResponse.getDescription());
		disasterInquiryResponse.setLink(externalResponse.getLink());
		disasterInquiryResponse.setEvents(externalResponse.getEvents());
		return disasterInquiryResponse;
	}

	private LocationsInfo mapServerResponseToJava(LocationIqResponse serverResp, String locationName) {
		LocationsInfo locationsInfo = null;
		int index = 0;
		locationsInfo = new LocationsInfo();
		locationsInfo.setLocationName(locationName);
		locationsInfo.setMinLatitude(truncateCoordinates(serverResp.getBoundingbox().get(index++)));
		locationsInfo.setMaxLatitude(truncateCoordinates(serverResp.getBoundingbox().get(index++)));
		locationsInfo.setMinLongitude(truncateCoordinates(serverResp.getBoundingbox().get(index++)));
		locationsInfo.setMaxLongitude(truncateCoordinates(serverResp.getBoundingbox().get(index++)));
		return locationsInfo;
	}

	private String truncateCoordinates(String coordinateValue) {
		if (coordinateValue == null) {
			return null;
		}
		String result = coordinateValue.substring(0, coordinateValue.indexOf(".") + 5);
		logger.info(
				logger.isInfoEnabled() ? "coordinateValue = " + coordinateValue + " truncated to = " + result : null);
		return result;
	}

	private String prepareQueryParameters(CommonRequest reqObj, LocationsInfo loc, String url) {
		StringBuilder sb = new StringBuilder(url);
		if (null != reqObj.getSourcesList()) {
			sb.append(Constants.SOURCES_KEYWORD + Utils.convertListToString(reqObj.getSourcesList()));
		}
		if (0 < reqObj.getLimit()) {
			sb.append(Constants.LIMIT_KEYWORD + reqObj.getLimit());
		}
		if (null != loc) {
			sb.append(Constants.BBOX_KEYWORD + loc.getMinLongitude() + "," + loc.getMaxLatitude() + ","
					+ loc.getMaxLongitude() + "," + loc.getMinLatitude());
		}
		if (null != reqObj.getStartDate() && null != reqObj.getEndDate()) {
			sb.append(Constants.NEW_PARAM_KEYWORD + Constants.START_DATE_KEYWORD + reqObj.getStartDate()
					+ Constants.AND_KEYWORD + Constants.END_DATE_KEYWORD + reqObj.getEndDate());
		} else if (null != reqObj.getStartDate()) {
			sb.append(Constants.NEW_PARAM_KEYWORD + Constants.START_DATE_KEYWORD + reqObj.getStartDate());
		} else if (null != reqObj.getEndDate()) {
			sb.append(Constants.NEW_PARAM_KEYWORD + Constants.END_DATE_KEYWORD + reqObj.getEndDate());
		}
		logger.info(logger.isInfoEnabled() ? "Url prepared for disaster inquiry = " + sb.toString() : null);
		return sb.toString();
	}

}
