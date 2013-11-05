package br.com.orlandoburli;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpSession;

public final class SystemManager {
	
	public static final String GENERAL_PROPERTIES_FILE = "system.properties";
	public static final String LABEL_PROPERTIES_FILE = "labels.properties";
	public static final String TITLE_APPLICATION = "application.title";
	public static String INITIAL_APP_DIRECTORY;
	
	public static List<HttpSession> listSessions = null;
	
	public static Properties properties;
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public static int getNumeroUsuariosLicenca(Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
		
//		ParametroLojaDAO paramDao = new ParametroLojaDAO();
//		ParametroLojaVO paramVo = new ParametroLojaVO();
//		paramVo.setCodigoEmpresa(CodigoEmpresa);
//		paramVo.setCodigoLoja(CodigoLoja);
//		paramVo.setChaveParametro(Constants.Parameters.Licenca.LICENCA_SISTEMA);
//		paramVo = paramDao.get(paramVo);
//		
//		if (paramVo == null) {
//			return -1;
//		}
//		
//		String dados = null;
//		
//		try {
//			dados = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(paramVo.getValorParametro());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if (dados == null || dados.trim().equals("")) {
//			return 0;
//		}
//		
//		int quantidadeUsuariosLicenca = 0;
//		
//		try {
//			quantidadeUsuariosLicenca = Integer.parseInt(dados.substring(26, 31));
//		} catch (NumberFormatException e) {	}
//		
//		return quantidadeUsuariosLicenca;
		return 0;
	}
	
	public static boolean isLicenciado(Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
//		ParametroLojaDAO paramDao = new ParametroLojaDAO();
//		ParametroLojaVO paramVo = new ParametroLojaVO();
//		paramVo.setCodigoEmpresa(CodigoEmpresa);
//		paramVo.setCodigoLoja(CodigoLoja);
//		paramVo.setChaveParametro(Constants.Parameters.Licenca.LICENCA_SISTEMA);
//		paramVo = paramDao.get(paramVo);
//		
//		if (paramVo == null) {
//			return false;
//		}
//		
//		String dados = null;
//		try {
//			dados = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(paramVo.getValorParametro());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if (dados == null || dados.trim().equals("")) {
//			return false;
//		}
		
		return true;
	}

	public static String getSchemaName() {
		String nomeSchema = SystemManager.getProperty("db.schema");
		return nomeSchema;
	}
}