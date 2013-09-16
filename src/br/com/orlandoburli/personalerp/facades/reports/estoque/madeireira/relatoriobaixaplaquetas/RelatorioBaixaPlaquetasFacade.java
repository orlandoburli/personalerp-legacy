package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.relatoriobaixaplaquetas;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.cadastro.madeireira.GerenteMadeireiraDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioBaixaPlaquetasFacade extends BaseRelatorioFacade {
	public static final String AGRUPAMENTO_ESSENCIA = "E";
	public static final String AGRUPAMENTO_GERENTE = "G";
	
	private static final long serialVersionUID = 1L;
	private String TipoRelatorio;
	private Date DataInicial;
	private Date DataFinal;
	
	private Integer CodigoEmpresaGerente;
	private Integer CodigoLojaGerente;
	private Integer CodigoGerente;
	
	private Integer CodigoEssencia;

	public RelatorioBaixaPlaquetasFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		if (DataInicial == null) {
			DataInicial = Date.valueOf("0001-01-01");
		}
		System.out.println(new java.text.SimpleDateFormat("dd/MM/yyyy").format(DataInicial));
		if (DataFinal == null) {
			DataFinal = Date.valueOf("2099-12-31");
		}
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		parameters.put("CodigoEmpresaGerente", getCodigoEmpresaGerente());
		parameters.put("CodigoLojaGerente", getCodigoLojaGerente());
		parameters.put("CodigoGerente", getCodigoGerente());
		parameters.put("CodigoEssencia", getCodigoEssencia());
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
		super.doParameter(parameters);
	}
	
	@IgnoreMethodAuthentication
	public void essencias() {
		EssenciaDAO _dao = new EssenciaDAO();
		
		try {
			List<EssenciaVO> list = _dao.getList(new EssenciaVO(), "NomeEssencia");
			int count = _dao.getListCount(new EssenciaVO());
			String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
			for (EssenciaVO _vo : list) {
				xmlList += Utils.voToXml(_vo, false);
			}
			xmlList += "<count>" + count + "</count>";
			xmlList += "</list>";
			write(xmlList);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void gerentes() {
		GerenteMadeireiraDAO _dao = new GerenteMadeireiraDAO();
		
		try {
			List<GerenteMadeireiraVO> list = _dao.getList(new GerenteMadeireiraVO(), "NomeGerente");
			int count = _dao.getListCount(new GerenteMadeireiraVO());
			String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
			for (GerenteMadeireiraVO _vo : list) {
				xmlList += Utils.voToXml(_vo, false);
			}
			xmlList += "<count>" + count + "</count>";
			xmlList += "</list>";
			write(xmlList);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	@Override
	protected String getJasperFileName() {
		if (getTipoRelatorio() != null) {
			if (getTipoRelatorio().equalsIgnoreCase(AGRUPAMENTO_ESSENCIA)) {
				return "reports/relatorioRomaneioBaixaPlaquetaEssencia.jasper";
			} else if (getTipoRelatorio().equalsIgnoreCase(AGRUPAMENTO_GERENTE)) {
				return "reports/relatorioRomaneioBaixaPlaquetaGerente.jasper";
			}
		}
		return null;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Integer getCodigoEmpresaGerente() {
		return CodigoEmpresaGerente;
	}

	public void setCodigoEmpresaGerente(Integer codigoEmpresaGerente) {
		CodigoEmpresaGerente = codigoEmpresaGerente;
	}

	public Integer getCodigoLojaGerente() {
		return CodigoLojaGerente;
	}

	public void setCodigoLojaGerente(Integer codigoLojaGerente) {
		CodigoLojaGerente = codigoLojaGerente;
	}

	public Integer getCodigoGerente() {
		return CodigoGerente;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		CodigoGerente = codigoGerente;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}
}
