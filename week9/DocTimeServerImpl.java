package week9;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "week9.DocTimeServer")
public class DocTimeServerImpl implements DocTimeServer {

	@Override
	public String getTimeAsString() {
		return (new Date()).toString();
	}

	@Override
	public long getTimeAsElapsed() {
		return (new Date()).getTime();
	}

}
