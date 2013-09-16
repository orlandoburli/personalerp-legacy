package br.com.orlandoburli.personalerp.facades.reports.rh.folhapagamento;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioFolhaPagamentoFacade extends BaseRelatorioFacade {

	public static final String TIPO_RELATORIO_HOLERITE = "holerite";
	public static final String TIPO_RELATORIO_RELATORIO = "relatorio";
	
	private static final long serialVersionUID = 1L;
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoFolhaPagamento;
	private String TipoRelatorio;

	public RelatorioFolhaPagamentoFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoFolhaPagamento", getCodigoFolhaPagamento());
		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		if (getTipoRelatorio() != null) {
			if (getTipoRelatorio().equalsIgnoreCase(TIPO_RELATORIO_HOLERITE)) {
				return "reports/holerite.jasper";
			} else if (getTipoRelatorio().equalsIgnoreCase(TIPO_RELATORIO_RELATORIO)) {
				return "reports/relatorioFolhaPagamento.jasper";				
			}
		}
		return "reports/relatorioFolhaPagamento.jasper";
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoFolhaPagamento(Integer codigoFolhaPagamento) {
		CodigoFolhaPagamento = codigoFolhaPagamento;
	}

	public Integer getCodigoFolhaPagamento() {
		return CodigoFolhaPagamento;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}
}