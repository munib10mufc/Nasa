package com.nasa.eonet.nasa.beans.error;

import com.nasa.eonet.nasa.beans.CommonResponse;

public class ErrorResponse extends CommonResponse {

	public ErrorResponse(String respCode, String respDesc) {
		super(respCode, respDesc);
	}
}
