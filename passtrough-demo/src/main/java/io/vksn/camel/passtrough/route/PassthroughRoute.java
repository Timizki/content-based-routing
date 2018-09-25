package io.vksn.camel.passtrough.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassthroughRoute extends RouteBuilder {

	@Autowired
	private ReturnFirstAggregateStrategy aggregationStrategy;
	@Autowired
	private ContentBasedEndpointResolver endpointResolver;
	
	@Override
	public void configure() throws Exception {
		from("netty-http:localhost:8080/in")
		.streamCaching()		
		.multicast(aggregationStrategy)			
			.pipeline()
				.unmarshal().mimeMultipart()				
					.process(endpointResolver)
				.marshal().mimeMultipart("related")
				.recipientList(header("destination"))
				.end()			
			.end()
			.pipeline()
				.log("p2: ${body}")
				.wireTap("file://target?fileName=message.xml")	
			.end()	
		.end();
	}

}
