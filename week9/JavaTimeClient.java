package week9;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
* Java Client for the Java Web Service
* @author Martin Kalin (Java Web Services...Up and Running)
*
*/
public class JavaTimeClient {
	
	public static void main(String[] args) throws MalformedURLException
	{
		//Use the data from http://localhost:9999/ts?wsdl
		
		URL url = new URL("http://localhost:9999/ts?wsdl");
		//use the xmlns:tns value and the name in the definitions element
		QName qname = new QName("http://week9/", "TimeServerImplService");
		Service service = Service.create(url,qname);
		//you need to have the Interface in your classpath for this
		TimeServer eif = service.getPort(TimeServer.class);
		System.out.println("The time retrieved from the service is : "+eif.getTimeAsString());
	}

}
