package week9;

import javax.xml.ws.Endpoint;

public class TimeServerPublisher {
	
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:9999/ts", new TimeServerImpl());
	}

}
