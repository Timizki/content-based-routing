package io.vksn.camel.passtrough.route;

import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.http.NettyChannelBufferStreamCache;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class PassthroughRoute extends RouteBuilder {
	private static final Logger LOG = Logger.getLogger(PassthroughRoute.class.getName());
	
	
	@Override
	public void configure() throws Exception {
		from("netty-http:localhost:8080/in")
		.streamCaching()
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				NettyChannelBufferStreamCache body = exchange.getIn().getBody(NettyChannelBufferStreamCache.class);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(body);
				XPath x = XPathFactory.newInstance().newXPath();
				String name = x.evaluate("//*[local-name()='name']/text()", doc, XPathConstants.STRING).toString().trim();
				LOG.info(name);
				if("first".equals(name)) {
					exchange.getIn().setHeader("destination", "http4://localhost:7070/ws/out?bridgeEndpoint=true");
				}
				else  {
					exchange.getIn().setHeader("destination", "http4://localhost:7071/ws/out?bridgeEndpoint=true");
				}
			}
		})
		.recipientList(header("destination"));
		
	}

}
