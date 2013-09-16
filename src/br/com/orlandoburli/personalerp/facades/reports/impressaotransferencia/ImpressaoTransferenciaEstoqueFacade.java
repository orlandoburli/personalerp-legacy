package br.com.orlandoburli.personalerp.facades.reports.impressaotransferencia;

import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class ImpressaoTransferenciaEstoqueFacade extends BaseRelatorioFacade{

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoTransferenciaEstoque;

	@IgnoreMethodAuthentication
	public void execute() {
		super.execute();
	}
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioTransferenciaEstoque.jasper";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		super.doParameter(parameters);
		
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoTransferenciaEstoque", getCodigoTransferenciaEstoque());
	}
	
	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoTransferenciaEstoque() {
		return CodigoTransferenciaEstoque;
	}

	public void setCodigoTransferenciaEstoque(Integer codigoTransferenciaEstoque) {
		CodigoTransferenciaEstoque = codigoTransferenciaEstoque;
	}

}
