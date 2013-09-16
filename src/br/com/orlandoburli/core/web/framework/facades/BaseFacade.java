package br.com.orlandoburli.core.web.framework.facades;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.filters.OutjectionFilter;

public abstract class BaseFacade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext context;
	private String methodName;
	
	public BaseFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		this.request = request;
		this.response = response;
		this.context = context;
		this.methodName = methodName;
	}
	
	public BaseFacade() {
		
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	public String getUrl() {
	    String scheme = request.getScheme();             // http
	    String serverName = request.getServerName();     // hostname.com
	    int serverPort = request.getServerPort();        // 80
	    String contextPath = request.getContextPath();   // /mywebapp
	    String servletPath = request.getServletPath();   // /servlet/MyServlet
	    String pathInfo = request.getPathInfo();         // /a/b;c=123
	    String queryString = request.getQueryString();          // d=789

	    // Reconstruct original requesting URL
	    String url = scheme+"://"+serverName+":"+serverPort+contextPath+servletPath;
	    if (pathInfo != null) {
	        url += pathInfo;
	    }
	    if (queryString != null) {
	        url += "?"+queryString;
	    }
	    return url;
	}
	
	public String getApplicationUrl() {
	    String scheme = request.getScheme();             // http
	    String serverName = request.getServerName();     // hostname.com
	    int serverPort = request.getServerPort();        // 80
	    String contextPath = request.getContextPath();   // /mywebapp

	    // Reconstruct original requesting URL
	    String url = scheme+"://"+serverName+":"+serverPort+contextPath;
	    return url;
	}
	
	public void forward(String url) {
		dispatch();
		
		if (!url.startsWith("/")) {
			url = "/" + url;
		}
		RequestDispatcher dispatcher = context.getRequestDispatcher(url);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void redir(String url) {
		dispatch();
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newwindow(String url) {
		dispatch();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<script language=\"javascript\">");
		sb.append("		window.open('" + url + "');");
		sb.append("</script>");
		
		try {
			response.getWriter().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void dispatch() {
		// Filtro de Outjection (saida de dados)
		OutjectionFilter ofilter = new OutjectionFilter();
		ofilter.setContext(this.context);
		ofilter.setRequest(this.request);
		ofilter.setResponse(this.response);
		
		try {
			if (!ofilter.doFilter(this)) {
				return;
			}
		} catch (IllegalArgumentException e1) {
		} catch (IllegalAccessException e1) {
		}
	}
	
	public Object getAttribute(String key) {
		if (this.request != null) {
			if (request.getMethod().equalsIgnoreCase("POST")) {
				if (request.getAttribute(key) != null) {
					return request.getAttribute(key);
				} else {
					return request.getParameter(key);					
				}
			} else {
				return request.getParameter(key);
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Escreve na saída usando o encoding ISO-8859-1
	 * @param value
	 */
	public void writeIso88591(String value) {
		write(value, "ISO-8859-1");
	}
	
	/**
	 * Escreve na saida usando o encoding especificado
	 * @param value
	 * @param Encoding
	 */
	public void write(String value, String Encoding) {
		try {
			response.setCharacterEncoding(Encoding);
			response.getWriter().write(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Escreve na saida usando o encoding UTF-8
	 * @param value
	 */
	public void write(String value) {
		write(value, "UTF-8");
	}
	
	public void writeErrorMessage(String errormessage) {
		writeErrorMessage(errormessage, null);
	}
	
	public void writeErrorMessage(String errormessage, String fieldName) {
		MessageVO message = new MessageVO(errormessage, fieldName);
		List<MessageVO> list = new ArrayList<MessageVO>();
		list.add(message);
		write(Utils.voToXml(list));
	}
	
	public void setAttribute(String key, Object value) {
		if (this.request != null) {
			request.setAttribute(key, value);
		}
	}
	
	public boolean isPost() {
		return this.request.getMethod().equalsIgnoreCase("POST");
	}
	
	public String getFacadeName() {
		return this.getClass().getSimpleName().replace("Facade", "").toLowerCase();
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodName() {
		return methodName;
	}
}