package com.nasa.eonet.nasa.services;

import org.jvnet.hk2.annotations.Service;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;

@Service
public interface ServiceHandlerInterface {
	String getServiceName();

	CommonResponse handleRequest(CommonRequest req);
}
