package week8;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.JDOMException;

/**
 * Servlet implementation class XMLReaderServlet
 */
public class XMLReaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XMLReaderServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		printData(request);
		returnXMLFile(response);
	}

	
	/**
	 * API to read a file and set it to the response stream
	 * @param resp
	 * @throws IOException
	 */
	private void returnXMLFile(HttpServletResponse resp) throws IOException
	{
		String myfile =
				  getServletContext().getRealPath("/week3/CSF205SoWNoSchema.xml");
		System.out.println("**** "+new File(myfile).getAbsolutePath());
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("utf-8");
		ServletXMLReader reader = new ServletXMLReader();
		try {
			reader.loadBuilders();
			String xmlcontent = reader.parseXMLFile(myfile);
			resp.getWriter().println(xmlcontent);
		} catch (ParserConfigurationException | JDOMException e) {
		 
			e.printStackTrace(System.out);
		}
	}

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
