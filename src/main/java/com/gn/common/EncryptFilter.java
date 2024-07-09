package com.gn.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(servletNames = {"userCreateEnd","userLoginEnd"})
public class EncryptFilter extends HttpFilter implements Filter {
       
    public EncryptFilter() {
        super();
    }

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PasswordEncodingWrapper pew 
			= new PasswordEncodingWrapper((HttpServletRequest)request);
		chain.doFilter(pew, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
