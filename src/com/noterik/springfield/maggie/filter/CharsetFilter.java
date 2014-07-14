package com.noterik.springfield.maggie.filter;

import java.io.*;
import javax.servlet.*;

public class CharsetFilter implements Filter {
	private String encoding;

	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("requestEncoding");

		if (encoding == null){
			encoding = "ISO-8859-1";
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException,
			ServletException {
		// Respect the client-specified character encoding
		// (see HTTP specification section 3.4.1)
		if (null == request.getCharacterEncoding()){
			request.setCharacterEncoding(encoding);
		}

		next.doFilter(request, response);
	}

	public void destroy() {
	}
}
