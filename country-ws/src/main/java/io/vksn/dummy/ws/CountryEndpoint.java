package io.vksn.dummy.ws;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final Logger LOG = Logger.getLogger(CountryEndpoint.class.getName());
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	@Value("${server.port}")
	private String port;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		LOG.info(request.getName());
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(CountryBuilder.EUR().withName(request.getName()).withPopulation(Integer.valueOf(port)).build());
		return response;
	}
}