package br.com.orlandoburli.personalerp.facades.reports.estoque.levantamentoestoque;

import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioLevantamentoEstoqueFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoLevantamentoEstoque;
	
	@Override
	protected String getJasperFileName() {
		return "reports/RelatorioLevantamentoEstoque.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoLevantamentoEstoque", getCodigoLevantamentoEstoque());
		super.doParameter(parameters);
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

	public void setCodigoLevantamentoEstoque(Integer codigoLevantamentoEstoque) {
		CodigoLevantamentoEstoque = codigoLevantamentoEstoque;
	}

	public Integer getCodigoLevantamentoEstoque() {
		return CodigoLevantamentoEstoque;
	}
}