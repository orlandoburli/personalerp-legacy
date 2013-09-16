package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.romaneiomadeiraserrada;

import java.math.BigDecimal;
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
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioRomaneioEntradaMadeiraSerradaFacade extends
		BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEssencia;
	private Integer CodigoEmpresaGerente;
	private Integer CodigoLojaGerente;
	private Integer CodigoGerente;
	
	private String TipoRelatorio;
	
	private Date DataInicial;
	private Date DataFinal;
	
	@Precision(decimals=3)
	private BigDecimal LarguraDe;
	@Precision(decimals=3)
	private BigDecimal LarguraAte;
	@Precision(decimals=3)
	private BigDecimal AlturaDe;
	@Precision(decimals=3)
	private BigDecimal AlturaAte;
	@Precision(decimals=3)
	private BigDecimal ComprimentoDe;
	@Precision(decimals=3)
	private BigDecimal ComprimentoAte;

	public RelatorioRomaneioEntradaMadeiraSerradaFacade(
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		
		if (LarguraDe != null && LarguraDe.compareTo(BigDecimal.ZERO) <= 0) {
			LarguraDe = null;
		}
		if (LarguraAte != null && LarguraAte.compareTo(BigDecimal.ZERO) <= 0) {
			LarguraAte = null;
		}
		
		if (ComprimentoDe != null && ComprimentoDe.compareTo(BigDecimal.ZERO) <= 0) {
			ComprimentoDe = null;
		}
		if (ComprimentoAte != null && ComprimentoAte.compareTo(BigDecimal.ZERO) <= 0) {
			ComprimentoAte = null;
		}
		if (AlturaDe != null && AlturaDe.compareTo(BigDecimal.ZERO) <= 0) {
			AlturaDe = null;
		}
		if (AlturaAte != null && AlturaAte.compareTo(BigDecimal.ZERO) <= 0) {
			AlturaAte = null;
		}
		if (DataInicial == null) {
			DataInicial = Date.valueOf("0001-01-01");
		}
		if (DataFinal == null) {
			DataFinal = Date.valueOf("2099-12-31");
		}
		
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		parameters.put("CodigoEssencia", CodigoEssencia);
		parameters.put("DataInicial", DataInicial);
		parameters.put("DataFinal", DataFinal);
		parameters.put("LarguraDe", LarguraDe);
		parameters.put("LarguraAte", LarguraAte);
		parameters.put("AlturaDe", AlturaDe);
		parameters.put("AlturaAte", AlturaAte);
		parameters.put("ComprimentoDe", ComprimentoDe);
		parameters.put("ComprimentoAte", ComprimentoAte);
		
		parameters.put("CodigoEmpresaGerente", CodigoEmpresaGerente);
		parameters.put("CodigoLojaGerente", CodigoLojaGerente);
		parameters.put("CodigoGerente", CodigoGerente);
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
			new java.sql.Date(1);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	@Override
	protected String getJasperFileName() {
		if (getTipoRelatorio() == null || getTipoRelatorio().equalsIgnoreCase("+")) {
			return "reports/relatorioRomaneioEntradaMadeiraSerradaGerente.jasper";
		} else if (getTipoRelatorio().equalsIgnoreCase("-")) {
			return "reports/relatorioRomaneioMadeiraSerradaSaidaMensal.jasper";
		}
		return null;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
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

	public BigDecimal getLarguraDe() {
		return LarguraDe;
	}

	public void setLarguraDe(BigDecimal larguraDe) {
		LarguraDe = larguraDe;
	}

	public BigDecimal getLarguraAte() {
		return LarguraAte;
	}

	public void setLarguraAte(BigDecimal larguraAte) {
		LarguraAte = larguraAte;
	}

	public BigDecimal getAlturaDe() {
		return AlturaDe;
	}

	public void setAlturaDe(BigDecimal alturaDe) {
		AlturaDe = alturaDe;
	}

	public BigDecimal getAlturaAte() {
		return AlturaAte;
	}

	public void setAlturaAte(BigDecimal alturaAte) {
		AlturaAte = alturaAte;
	}

	public BigDecimal getComprimentoDe() {
		return ComprimentoDe;
	}

	public void setComprimentoDe(BigDecimal comprimentoDe) {
		ComprimentoDe = comprimentoDe;
	}

	public BigDecimal getComprimentoAte() {
		return ComprimentoAte;
	}

	public void setComprimentoAte(BigDecimal comprimentoAte) {
		ComprimentoAte = comprimentoAte;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}

}
