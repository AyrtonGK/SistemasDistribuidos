package arquivo;

import javax.xml.ws.Endpoint;

public class ArquivoServerPublisher {
	public static void main(String[] args) {
		Endpoint.publish("http://127.0.0.1:9876/arquivo", new ArquivoServerImpl());
	}
}