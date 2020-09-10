package week9;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * The Service Endpoint Interface
 * @author Martin Kalin (Java Web Services...Up and Running)
 *
 */
@WebService
@SOAPBinding(style = Style.RPC)
public interface TimeServer {

	@WebMethod String getTimeAsString();
	@WebMethod long getTimeAsElapsed();
}
