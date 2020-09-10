package week9;

import java.util.Date;

import javax.jws.WebService;

/**
 * Service Implementation
 * @author Martin Kalin (Java Web Services...Up and Running)
 *
 */
@WebService(endpointInterface = "week9.TimeServer")
public class TimeServerImpl implements TimeServer {

	@Override
	public String getTimeAsString() {
		
		return (new Date()).toString();
	}

	@Override
	public long getTimeAsElapsed() {
		 
		return (new Date()).getTime();
	}

}
