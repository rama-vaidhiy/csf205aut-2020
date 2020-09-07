package week8;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
public class SimpleServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String webContextName = null;
	

	@Override
	public void init() throws ServletException {
		webContextName = getInitParameter("newCtxName");
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doGet Request Data");
		System.out.println("----------------------------------");
		printData(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doPost Request Data");
		System.out.println("----------------------------------");
		printData(req, resp);
		
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doHead Request Data");
		System.out.println("----------------------------------");
		printData(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doOptions Request Data");
		System.out.println("----------------------------------");
		printData(req, resp);
	}

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("SimpleServlet service Request Data");
//		System.out.println("----------------------------------");
//		printData(req);
//		resp.setContentType("text/plain");
//		resp.getWriter().println("Service Method: Welcome to your Servlet!");
//		
//	}
//	
	/**
	 * API to print the data from the request
	 * 
	 * @param req
	 * @throws IOException 
	 */
	private void printData(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (null != req) {
			PrintWriter mout = resp.getWriter();
			resp.setContentType("text/plain");
			mout.println("Character Encoding: " + req.getCharacterEncoding());
			mout.println("Content Length: " + req.getContentLength());
			mout.println("Authentication Type: " + req.getAuthType());
			mout.println("Server Name: " + req.getServerName());
			Map<String, String[]> params = req.getParameterMap();
			if (null != params && !(params.isEmpty())) {
				for (Map.Entry<String, String[]> p : params.entrySet())
					mout.println("Parameter Key: " + p.getKey() + ", Parameter Value: " + p.getValue());
			}
			mout.println("Context name from the Web XML " +webContextName);
		}
	}

}
