package br.com.orlandoburli.core.web.framework.servlets;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.AutorizathionFilter;
import br.com.orlandoburli.core.web.framework.filters.InjectionFilter;

public class MainControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String URL = req.getRequestURI();
		URL = URL.substring(URL.lastIndexOf("/") + 1);
		URL = URL.substring(0, URL.lastIndexOf("."));
		
		String facadeName = null;
		String methodName = null;
		
		facadeName = URL.indexOf(".") >= 0?URL.substring(0, URL.indexOf(".")):URL;
		methodName = URL.indexOf(".") >= 0?URL.substring(URL.indexOf(".")+1):"execute";
		
		String className = null;
		
		try {
			className = Utils.getFacadeName(Utils.WEBINF_CLASSES_DIRECTORY, facadeName + "Facade" + Utils.DOT_CLASS, getServletContext());
			if (className == null) {
				System.out.println("Classe [" + facadeName + "] nao encontrada (null)");
				return;
			}
			Class<?> klass = Class.forName(className);
			Object facade = null;
			
			try {
				Constructor constructor = klass.getConstructor(new Class[]{ HttpServletRequest.class, HttpServletResponse.class, ServletContext.class, String.class });
				facade = constructor.newInstance(new Object[]{req, resp, getServletContext(), methodName});
			} catch (NoSuchMethodException ex) { // Se nao tem o construtor com 4 parametros, tenta o padrao
				facade = klass.newInstance();
			}
			
			if (facade instanceof BaseFacade) { // Injeta os atributos request, response e servletcontext
				((BaseFacade)facade).setRequest(req);
				((BaseFacade)facade).setResponse(resp);
				((BaseFacade)facade).setContext(getServletContext());
				((BaseFacade)facade).setMethodName(methodName);
			}
			
			// Seta o nome do metodo no request
			req.setAttribute("methodname", methodName);
			
			// Execucao dos filtros
			// Filtro para injecao de dependencias
			InjectionFilter ifilter = new InjectionFilter();
			ifilter.setContext(getServletContext());
			ifilter.setRequest(req);
			ifilter.setResponse(resp);
			resp.setCharacterEncoding("UTF-8");
			if (!ifilter.doFilter((BaseFacade) facade)) {
				return;
			}
			
			// Filtro de autorizacao / autenticacao
			AutorizathionFilter afilter = new AutorizathionFilter();
			afilter.setContext(getServletContext());
			afilter.setRequest(req);
			afilter.setResponse(resp);
			if (!afilter.doFilter((BaseFacade)facade)) {
				return;
			}
			
			// Invocação dos métodos
			System.out.println("Invocando m�todo " + methodName + " do facade " + facadeName);
			
			Method method = klass.getMethod(methodName, new Class<?>[]{}); 
			method.invoke(facade, new Object[]{});
			
			if (facade instanceof BaseFacade) { // Processamento de Outjection
				((BaseFacade)facade).dispatch();
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println("Classe [" + facadeName + "] n�o encontrada");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println( "M�todo [" + methodName + "] n�o existe em [" + className + "]");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
}