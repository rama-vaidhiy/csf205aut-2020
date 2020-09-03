package week7;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doGet Request Data");
		System.out.println("----------------------------------");
		printData(req);
		resp.setContentType("text/plain");
		resp.getWriter().println("Service Method: Welcome to your Servlet!");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doPost Request Data");
		System.out.println("----------------------------------");
		printData(req);
		resp.setContentType("text/plain");
		resp.getWriter().println("Service Method: Welcome to your Servlet!");
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doHead Request Data");
		System.out.println("----------------------------------");
		printData(req);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SimpleServlet doOptions Request Data");
		System.out.println("----------------------------------");
		printData(req);
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
	 */
	private void printData(HttpServletRequest req) {
		if (null != req) {
			System.out.println("Character Encoding: " + req.getCharacterEncoding());
			System.out.println("Content Length: " + req.getContentLength());
			System.out.println("Authentication Type: " + req.getAuthType());
			System.out.println("Server Name: " + req.getServerName());
			Map<String, String[]> params = req.getParameterMap();
			if (null != params && !(params.isEmpty())) {
				for (Map.Entry<String, String[]> p : params.entrySet())
					System.out.println("Parameter Key: " + p.getKey() + ", Parameter Value: " + p.getValue());
			}
		}
	}

}
