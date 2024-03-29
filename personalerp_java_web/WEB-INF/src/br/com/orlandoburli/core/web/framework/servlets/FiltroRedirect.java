package br.com.orlandoburli.core.web.framework.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FiltroRedirect implements Filter {
	
	FilterConfig config;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		((HttpServletResponse)response).sendRedirect("index.action"); // Se caiu no filtro jsp, retorna para index.action
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
}