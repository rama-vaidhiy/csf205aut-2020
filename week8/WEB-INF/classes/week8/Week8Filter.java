package week8;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "Week8Filter", urlPatterns = {"/AddlServlet"})
public class Week8Filter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String uri = ((HttpServletRequest)req).getRequestURI();
		System.out.println("FILTER CODE : " +uri);
		chain.doFilter(req,resp);
		
	}

}
