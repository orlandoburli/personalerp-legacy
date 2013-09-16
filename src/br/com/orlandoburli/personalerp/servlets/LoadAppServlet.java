package br.com.orlandoburli.personalerp.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


import br.com.orlandoburli.SystemManager;

@WebServlet(loadOnStartup=1)
public class LoadAppServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		SystemManager.INITIAL_APP_DIRECTORY = this.getServletContext().getRealPath("") + File.separator;
		
		if (SystemManager.listSessions == null) {
			SystemManager.listSessions = new ArrayList<HttpSession>();
		}
		
		try {
			SystemManager.properties = new Properties();
            SystemManager.properties.load(new FileInputStream(SystemManager.INITIAL_APP_DIRECTORY + SystemManager.GENERAL_PROPERTIES_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Iniciando Personal ERP em " + SystemManager.INITIAL_APP_DIRECTORY);
		super.init();
	}	
}