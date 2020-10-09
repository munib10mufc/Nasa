package com.nasa.eonet.nasa.requestcontroller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nasa.eonet.nasa.beans.CommonRequest;
import com.nasa.eonet.nasa.beans.CommonResponse;
import com.nasa.eonet.nasa.beans.DisasterInfoRequest;
import com.nasa.eonet.nasa.beans.UserDTO;
import com.nasa.eonet.nasa.beans.error.ErrorResponse;
import com.nasa.eonet.nasa.beans.jwt.JwtRequest;
import com.nasa.eonet.nasa.beans.jwt.JwtResponse;
import com.nasa.eonet.nasa.beans.success.CategoriesResponse;
import com.nasa.eonet.nasa.beans.success.DisasterInquiryResponse;
import com.nasa.eonet.nasa.beans.success.SourcesResponse;
import com.nasa.eonet.nasa.beans.success.SuccessResponse;
import com.nasa.eonet.nasa.dao.services.JwtUserDetailsService;
import com.nasa.eonet.nasa.security.JwtTokenUtil;
import com.nasa.eonet.nasa.services.ServiceFactory;
import com.nasa.eonet.nasa.services.ServiceHandlerInterface;
import com.nasa.eonet.nasa.utils.Constants;
import com.nasa.eonet.nasa.utils.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/nasa-eonet")
@Api(value = "NASA Eonet Service", description = "Request handler for NASA Eonet service")
@Validated
public class RequestHandler {
	private static final Logger logger = LogManager.getLogger(RequestHandler.class);

	private static ServiceHandlerInterface handler;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ApiOperation(value = "Invalid url specified by user")
	public CommonResponse noResource() {
		ErrorResponse resp = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Invalid URL fallback method" : null);
			resp = new ErrorResponse("12", "Invalid URL");
			logger.info(logger.isInfoEnabled() ? "returning response = " + resp : null);
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			resp = Utils.prepareErrorResponse(resp, Constants.EXCEPTION_RESPONSE,
					"Exception occured, please check with admin");
			return resp;
		}
		return resp;
	}

	@GetMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Echo service to check if server is up and running")
	public SuccessResponse echo() {
		SuccessResponse resp = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Request recieved in echo method" : null);
			resp = new SuccessResponse("00", "Service is up and running...");
			logger.info(logger.isInfoEnabled() ? "returning response = " + resp : null);
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			resp = new SuccessResponse(Constants.EXCEPTION_RESPONSE, "Exception occured, please check with admin");
			return resp;
		}
		return resp;
	}

	@GetMapping(value = "/getsources", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service to check available list of sources to get information of any event")
	public SourcesResponse getSources() {
		SourcesResponse resp = null;
		CommonRequest req = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Request recieved in echo method" : null);
			handler = ServiceFactory.getService(Constants.SOURCERS_SERVICE);
			req = Utils.prepareRequestFromServerRequest(Constants.SOURCERS_SERVICE);
			resp = (SourcesResponse) handler.handleRequest(req);
			logger.info(logger.isInfoEnabled() ? "returning response = " + resp : null);
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			return new SourcesResponse(Constants.EXCEPTION_RESPONSE, "Exception occured, please check with admin");
		}
		return resp;
	}

	@GetMapping(value = "/getcategories", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service to check available list of sources to get information of any event")
	public CategoriesResponse getcategories() {
		CategoriesResponse resp = null;
		CommonRequest req = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Request recieved in categories method" : null);
			handler = ServiceFactory.getService(Constants.CATEGORIES_SERVICE);
			req = Utils.prepareRequestFromServerRequest(Constants.CATEGORIES_SERVICE);
			resp = (CategoriesResponse) handler.handleRequest(req);
			logger.info(logger.isInfoEnabled() ? "returning response = " + resp : null);
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			return new CategoriesResponse(Constants.EXCEPTION_RESPONSE, "Exception occured, please check with admin");
		}
		return resp;
	}

	@PostMapping(value = "/disasterInquiry", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service to check open disasters in particular region")
	public DisasterInquiryResponse disasterInquiryService(@Valid @RequestBody DisasterInfoRequest disasterInfoRequest) {
		DisasterInquiryResponse resp = null;
		CommonRequest req = null;
		try {
			logger.info(logger.isInfoEnabled() ? "Request recieved in check disasters detail method" : null);
			handler = ServiceFactory.getService(Constants.DISASTER_SERVICE);
			req = Utils.prepareRequestFromServerRequest(disasterInfoRequest, Constants.DISASTER_SERVICE);
			resp = (DisasterInquiryResponse) handler.handleRequest(req);
			logger.info(logger.isInfoEnabled() ? "returning response = " + resp : null);
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			return new DisasterInquiryResponse(Constants.EXCEPTION_RESPONSE,
					"Exception occured, please check with admin");
		}
		return resp;
	}

	@PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service to authenticate user")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getName(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getName());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Service to register user")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
