package br.com.orlandoburli.core.web.framework.filters;

import java.io.Serializable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.pages.BasePage;

public abstract class BaseFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	protected HttpServletRequest getRequest() {
		return this.request;
	}

	protected HttpServletResponse getResponse() {
		return this.response;
	}

	protected ServletContext getContext() {
		return this.context;
	}

	public abstract boolean doFilter(BaseFacade facade)
			throws IllegalArgumentException, IllegalAccessException;

	public abstract boolean doFilter(BasePage facade) throws IllegalArgumentException,
			IllegalAccessException;
}