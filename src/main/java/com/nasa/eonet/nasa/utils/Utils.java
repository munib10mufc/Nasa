package com.nasa.eonet.nasa.utils;

import java.util.List;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;
import com.nasa.eonet.nasa.beans.DisasterInfoRequest;
import com.nasa.eonet.nasa.beans.error.ErrorResponse;
import com.nasa.eonet.nasa.beans.success.CategoriesResponse;
import com.nasa.eonet.nasa.beans.success.DisasterInquiryResponse;
import com.nasa.eonet.nasa.beans.success.SourcesResponse;

public class Utils {

	public static ErrorResponse prepareErrorResponse(ErrorResponse response, String respCode, String respDesc) {
		if (null == response) {
			response = new ErrorResponse(respCode, respDesc);
			return response;
		}
		response.setResponseCode(respCode);
		response.setResponseDesc(respDesc);
		return response;
	}

	public static CommonResponse prepareErrorResponse(CommonResponse response, String respCode, String respDesc) {
		if (null == response) {
			response = new CommonResponse(respCode, respDesc);
			return response;
		}
		response.setResponseCode(respCode);
		response.setResponseDesc(respDesc);
		return response;
	}

	public static SourcesResponse prepareErrorResponse(SourcesResponse response, String respCode, String respDesc) {
		if (null == response) {
			response = new SourcesResponse(respCode, respDesc);
			return response;
		}
		response.setResponseCode(respCode);
		response.setResponseDesc(respDesc);
		return response;
	}

	public static CategoriesResponse prepareErrorResponse(CategoriesResponse response, String respCode,
			String respDesc) {
		if (null == response) {
			response = new CategoriesResponse(respCode, respDesc);
			return response;
		}
		response.setResponseCode(respCode);
		response.setResponseDesc(respDesc);
		return response;
	}

	public static DisasterInquiryResponse prepareErrorResponse(DisasterInquiryResponse response, String respCode,
			String respDesc) {
		if (null == response) {
			response = new DisasterInquiryResponse(respCode, respDesc);
			return response;
		}
		response.setResponseCode(respCode);
		response.setResponseDesc(respDesc);
		return response;
	}

	public static CommonRequest prepareRequestFromServerRequest(String serviceName) {
		CommonRequest req = new CommonRequest();
		req.setServiceName(serviceName);
		return req;
	}

	public static CommonRequest prepareRequestFromServerRequest(DisasterInfoRequest webserviceReq, String serviceName) {
		CommonRequest req = new CommonRequest();
		req.setServiceName(serviceName);
		req.setSourcesList(webserviceReq.getSourcesList());
		req.setLocationName(webserviceReq.getlocationName());
		req.setStartDate(webserviceReq.getStartDate());
		req.setEndDate(webserviceReq.getEndDate());
		req.setLimit(webserviceReq.getLimit());
		return req;
	}

	public static String convertListToString(List<String> data) {
		StringBuilder sb = null;

		if (null == data) {
			return null;
		}
		sb = new StringBuilder();
		for (String s : data) {
			sb.append(s + ",");
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
}
