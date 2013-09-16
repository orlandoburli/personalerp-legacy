package br.com.orlandoburli.personalerp.facades.geral;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.acesso.UsuarioDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.utils.Criptografia;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.sistema.ParametroLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

@IgnoreFacadeAuthentication
public class CheckLicencaFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private LojaVO lojasessao;
	private EmpresaVO empresasessao;
	private String LicencaKey;
	
	@IgnoreMethodAuthentication
	public void execute() {
		// Checa se existe a licenca FULL ENTERPRISE
		try {
			String licencaFull = SystemManager.getProperty("licenca.sistema");
			licencaFull = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(licencaFull);
			if (licencaFull != null && licencaFull.equals("ENTERPRISE EDITION")) {
				write("ok");
				return;
			}
		} catch (Exception ex) {
			
		}
		
		ParametroLojaDAO paramDao = new ParametroLojaDAO();
		try {
			
			String licenca = paramDao.getStringParametro(Constants.Parameters.Licenca.LICENCA_SISTEMA, empresasessao.getCodigoEmpresa(), lojasessao.getCodigoLoja());
			//SystemManager.LICENCA_SISTEMA = licenca;
			
			String dataSistemaStr = paramDao.getStringParametro(Constants.Parameters.Licenca.DATA_SISTEMA, empresasessao.getCodigoEmpresa(), lojasessao.getCodigoLoja());
			
			String dados = null;
			
			try {
				dados = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(licenca);
			} catch (Exception e) {
				
			}
			
			if (dados == null || dados.trim().equals("")) {
				writeErrorMessage("Sistema não licenciado!", "N");
				return;
			}
			
			// Busca data do sistema 
			try {
				dataSistemaStr = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(dataSistemaStr);
			} catch (Exception e) {
				dataSistemaStr = "01/01/1900";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dataAtual = Calendar.getInstance();
			
			// Checa se houve modificacao na data do sistema
			if (dataSistemaStr != null) {
				Calendar dataSistema = Calendar.getInstance();
				dataSistema.setTime(sdf.parse(dataSistemaStr));
				if (dataSistema.after(dataAtual)) {
					writeErrorMessage("Data do sistema alterada!", "N");
					return;
				}
			}
			
			if (validarDados(dados)) {
				write("ok");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void adicionar() {
		try {
			// Adicionar Licenca
			if (LicencaKey == null || LicencaKey.trim().equals("")) {
				writeErrorMessage("Informe a chave de licença!");
				return;
			}
			
			String dados = null;
			try {
				dados = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(getLicencaKey());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (dados == null || dados.trim().equals("") || dados.length() < 31) {
				writeErrorMessage("Chave de licenca inválida!");
				return;
			}
			
			ParametroLojaDAO dao = new ParametroLojaDAO();
			
			if (validarDados(dados)) {
				// Salva a licenca
				ParametroLojaVO parmLoja = new ParametroLojaVO();
				
				parmLoja.setChaveParametro(Constants.Parameters.Licenca.LICENCA_SISTEMA);
				parmLoja.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
				parmLoja.setCodigoLoja(lojasessao.getCodigoLoja());
				parmLoja = dao.get(parmLoja);
				
				if (parmLoja == null) {
					parmLoja = new ParametroLojaVO();
					parmLoja.setNewRecord(true);
					parmLoja.setChaveParametro(Constants.Parameters.Licenca.LICENCA_SISTEMA);
					parmLoja.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
					parmLoja.setCodigoLoja(lojasessao.getCodigoLoja());
				}
				
				parmLoja.setValorParametro(getLicencaKey());
				dao.persist(parmLoja);
				
				write("ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage("Chave de licença inválida!");
		}
	}

	private boolean validarDados(String dados) throws ParseException, SQLException {
		
		// Checa se existe a licenca FULL ENTERPRISE
		try {
			String licencaFull = SystemManager.getProperty("licenca.sistema");
			licencaFull = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).decripto(licencaFull);
			if (licencaFull != null && licencaFull.equals("ENTERPRISE EDITION")) {
				write("ok");
				return true;
			}
		} catch (Exception ex) {
			
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dataAtual = Calendar.getInstance();
		
		// Checa os dados
		String dataStr = dados.substring(0, 10);
		Calendar data = Calendar.getInstance(); 
		data.setTime(sdf.parse(dataStr));
		
		// Verifica se já está vencido...
		if (data.before(dataAtual)) {
			// o campo fieldname indica se podera ou nao fechar a janela
			int diasAtual = (dataAtual.get(Calendar.YEAR) * 365) + dataAtual.get(Calendar.DAY_OF_YEAR); 
			int dias = (data.get(Calendar.YEAR) * 365) + data.get(Calendar.DAY_OF_YEAR);
			int diferenca = diasAtual - dias;
			writeErrorMessage("Sistema vencido à " + diferenca + " dias!", diferenca>30?"N":"S");
			return false;
		}
		
		//  Verifica o CNPJ da loja
		String cnpj = dados.substring(11, 25);
		if (!cnpj.equals(lojasessao.getCNPJLoja())) {
			writeErrorMessage("Cnpj da licença difere do Cnpj desta loja!");
			return false;
		}
		
		int quantidadeUsuariosLicenca = Integer.parseInt(dados.substring(26, 31));
		
		// Verifica numero de usuarios
		UsuarioDAO usuDao = new UsuarioDAO();
		List<UsuarioVO> list = usuDao.getList();
		
		// Verifica quantos estao ativos
		int quantidadeAtivos = 0;
		for (UsuarioVO usuario : list) {
			if (usuario.isAtivo()) {
				quantidadeAtivos++;
			}
		}
		
		if (quantidadeUsuariosLicenca < quantidadeAtivos) {
			writeErrorMessage("Licença inválida para a quantidade de usuários ativos no sistema!", "N");
			return false;
		}
		
		// Salva a data de hoje
		ParametroLojaVO parmData = new ParametroLojaVO();
		parmData.setChaveParametro(Constants.Parameters.Licenca.DATA_SISTEMA);
		parmData.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
		parmData.setCodigoLoja(lojasessao.getCodigoLoja());
		
		ParametroLojaDAO dao = new ParametroLojaDAO();
		parmData = dao .get(parmData);
		
		if (parmData == null) {
			parmData = new ParametroLojaVO();
			parmData.setChaveParametro(Constants.Parameters.Licenca.DATA_SISTEMA);
			parmData.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
			parmData.setCodigoLoja(lojasessao.getCodigoLoja());
			parmData.setNewRecord(true);
		}
		
		String dataHojeStr = sdf.format(Utils.getToday());
		
		try {
			dataHojeStr = Criptografia.newInstance(SystemManager.getProperty(Constants.Parameters.Licenca.LICENCA_KEY)).cripto(dataHojeStr);
		} catch (Exception e) { }
		
		parmData.setValorParametro(dataHojeStr);
		dao.persist(parmData);
		
		return true;
	}

	public void setLicencaKey(String licencaKey) {
		LicencaKey = licencaKey;
	}

	public String getLicencaKey() {
		return LicencaKey;
	}
}