package br.com.orlandoburli.personalerp.facades.reports.impressaotroca;

import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class ImpressaoTrocaManutencaoFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoTrocaManutencao;
	
	@IgnoreMethodAuthentication
	public void execute() {
		super.execute();
	}
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioProtocoloManutencao.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		super.doParameter(parameters);
		
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoTrocaManutencao", getCodigoTrocaManutencao());
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

	public Integer getCodigoTrocaManutencao() {
		return CodigoTrocaManutencao;
	}

	public void setCodigoTrocaManutencao(Integer codigoTrocaManutencao) {
		CodigoTrocaManutencao = codigoTrocaManutencao;
	}
	
}
