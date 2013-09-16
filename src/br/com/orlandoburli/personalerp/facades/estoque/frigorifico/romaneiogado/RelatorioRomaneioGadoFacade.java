package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneiogado;

import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioRomaneioGadoFacade extends BaseRelatorioFacade {
	
	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoRomaneio;

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioRomaneioGado.jasper";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("CodigoEmpresa", CodigoEmpresa);
		parameters.put("CodigoLoja", CodigoLoja);
		parameters.put("CodigoRomaneio", CodigoRomaneio);
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

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}
}
