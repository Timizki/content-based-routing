package io.vksn.camel.passtrough.route;

import java.util.Map;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

@Component
public class ContentBasedEndpointResolver implements Processor {
	private static final Logger LOG = Logger.getLogger(ContentBasedEndpointResolver.class.getName());
	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, DataHandler> att = exchange.getIn().getAttachments();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(att.get("another.xml").getInputStream());
		XPath x = XPathFactory.newInstance().newXPath();
		String name = x.evaluate("//*[local-name()='name']/text()", doc, XPathConstants.STRING).toString().trim();
		LOG.info(name);
		if ("first".equals(name)) {
			exchange.getIn().setHeader("destination", "http4://localhost:7070/ws/out?bridgeEndpoint=true");
		} else {
			exchange.getIn().setHeader("destination", "http4://localhost:7071/ws/out?bridgeEndpoint=true");
		}
	}
}
