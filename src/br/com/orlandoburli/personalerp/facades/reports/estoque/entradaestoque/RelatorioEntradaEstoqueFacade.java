package br.com.orlandoburli.personalerp.facades.reports.estoque.entradaestoque;

import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioEntradaEstoqueFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoEntradaEstoque;
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioEntradaEstoque.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoEntradaEstoque", getCodigoEntradaEstoque());
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

	public void setCodigoEntradaEstoque(Integer codigoEntradaEstoque) {
		CodigoEntradaEstoque = codigoEntradaEstoque;
	}

	public Integer getCodigoEntradaEstoque() {
		return CodigoEntradaEstoque;
	}
}