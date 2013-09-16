package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.movimentacaomadeiraserradafacade;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioMovimentacaoEstoqueMadeiraSerradaFacade extends
		BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private Integer CodigoEssencia;
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
	
	private Date DataInicial;
	private Date DataFinal;
	private String Estoque;

	public RelatorioMovimentacaoEstoqueMadeiraSerradaFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		new java.sql.Date(0);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioMovimentacaoEstoqueMadeiraSerrada.jasper";
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
		parameters.put("DataInicial", new Timestamp(DataInicial.getTime()));
		parameters.put("DataFinal", new Timestamp(DataFinal.getTime()));
		parameters.put("LarguraDe", LarguraDe);
		parameters.put("LarguraAte", LarguraAte);
		parameters.put("AlturaDe", AlturaDe);
		parameters.put("AlturaAte", AlturaAte);
		parameters.put("ComprimentoDe", ComprimentoDe);
		parameters.put("ComprimentoAte", ComprimentoAte);
		parameters.put("Estoque", Estoque);
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

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
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

	public String getEstoque() {
		return Estoque;
	}

	public void setEstoque(String estoque) {
		Estoque = estoque;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}
}