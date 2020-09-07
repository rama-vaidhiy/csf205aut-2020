import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A sample servlet with annotations and no entries in the web.xml
 * It sounds oh so lovely
 * @author rvaidhiy
 *
 */
@WebServlet("/AnnotatedServlet")
public class AnnotatedServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processAndReturnData(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processAndReturnData(req,resp);
	}

	/**
	 * API to process the input and return some HTML text to the 
	 * Servlet's Output stream 
	 * @param req HttpServletRequest object
	 * @param res HttpServletResponse object
	 */
	private void processAndReturnData(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html");
		PrintWriter sout = null;
		try {
			
			if (null != req ) {
				sout = res.getWriter();
				if(null!=sout)
				{
					sout.println("<!DOCTYPE html>");
					sout.println("<html>");
					sout.println("<body>");
					sout.println("<h1>Welcome to the Annotated World!</h1>");
					sout.println("<p>You are beaming to this Servlet from " + req.getServerName()+"</p>");
					Map<String, String[]> params = req.getParameterMap();
					if (null != params && !(params.isEmpty())) {
						sout.println("<h2>Parameters</h2>");
						sout.println("<p>Here are the list of parameters received</p>");
						sout.println("<ul>");
						for (Map.Entry<String, String[]> p : params.entrySet()) {
							sout.println("<li>Parameter Key: " + p.getKey() + ", Parameter Value: " + req.getParameter(p.getKey())+"</li>");
						}
						sout.println("</ul>");
					}
				}
				
			}
		} catch (IOException e) {
			//I am just going to ignore this for now. I can deal with this later
			e.printStackTrace();
		}
	}
		
}
