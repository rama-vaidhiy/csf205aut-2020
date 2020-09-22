package week9;

import javax.xml.ws.Endpoint;
/**
* Service Publisher
* @author Martin Kalin (Java Web Services...Up and Running)
*
*/
public class TimeServerPublisher {
	
	public static void main(String[] args)
	{
		System.out.println("The process is going to start");
		Endpoint.publish("http://localhost:9999/ts", new TimeServerImpl());
		Endpoint.publish("http://localhost:9888/ts", new DocTimeServerImpl());
	}

}
