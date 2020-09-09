package week8;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "moreservlets", urlPatterns = {"/AddlServlet"})
public class MoreServlets extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * I am going to redirect to a different location based on the input parameters
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		manageSession(req);
		printDataAndRedirect(req,resp);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		manageSession(req);
		printDataAndRequestDispatch(req,resp);
	}

	private void manageSession(HttpServletRequest req)
	{
		HttpSession theSession = req.getSession (true);
	
		// add data to the session object
		theSession.setAttribute("ID", "week8");

	}

	/**
	 * API to redirect the requests accordingly for the GET method
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void printDataAndRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (null != req) {
			Map<String, String[]> params = req.getParameterMap();
			if (null != params && !(params.isEmpty())) {
				String[] dest = params.get("dest");
				if(null!=dest && dest.length>0)
				{
					String destination = dest[0];
					System.out.println("destination is "+destination);
					if(destination.equalsIgnoreCase("farfaraway"))
					{
						//I can redirect to a totally new location
						resp.sendRedirect("https://www.google.co.uk");
					}
					if(destination.equalsIgnoreCase("nearby"))
					{
						//I can also redirect to a different file in the web app
						resp.sendRedirect("CSF205SoWModulePlan.html");
					}
				}
			}
		}
	}
	/**
	 * API to redirect the requests accordingly for the GET method
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void printDataAndRequestDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (null != req) {
			Map<String, String[]> params = req.getParameterMap();
			if (null != params && !(params.isEmpty())) {
				String[] dest = params.get("dest");
				if(null!=dest && dest.length>0)
				{
					String destination = dest[0];
					System.out.println("destination is "+destination);
					if(destination.equalsIgnoreCase("farfaraway"))
					{
						//I can redirect to a totally new location
						RequestDispatcher dispatcher = 
								req.getRequestDispatcher("https://www.google.co.uk");
						dispatcher.forward(req, resp);
					}
					if(destination.equalsIgnoreCase("nearby"))
					{
						//I can also redirect to a different file in the web app
						RequestDispatcher dispatcher = 
								req.getRequestDispatcher("CSF205SoWModulePlan.html");
						dispatcher.forward(req, resp);
					}
				}
			}
		}
	}
}
