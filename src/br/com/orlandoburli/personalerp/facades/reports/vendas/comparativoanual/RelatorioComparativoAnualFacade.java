package br.com.orlandoburli.personalerp.facades.reports.vendas.comparativoanual;

import java.util.Map;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioComparativoAnualFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Integer Ano;
	private Integer Semestre;
	private String FlagTodasLojas;

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioGerencialVendasAnual.jasper";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doParameter(Map parameters) {
		String filtroDescricao = "";

		parameters.put("imageUp", SystemManager.INITIAL_APP_DIRECTORY + "reports/up.png");
		parameters.put("imageDown", SystemManager.INITIAL_APP_DIRECTORY + "reports/down.png");
		
		parameters.put("ano", Ano);
		parameters.put("anoAnterior", Ano - 1);
		
		if (getSemestre().equals(1)) { // 1 semestre
			filtroDescricao += "1º semestre de " + getAno();
			
			for (int i = 1; i < 7; i++) {
				parameters.put("mes" + i, i);
			}
			parameters.put("nomeMes1", "Janeiro");
			parameters.put("nomeMes2", "Fevereiro");
			parameters.put("nomeMes3", "Março");
			parameters.put("nomeMes4", "Abril");
			parameters.put("nomeMes5", "Maio");
			parameters.put("nomeMes6", "Junho");
		} else if (getSemestre().equals(2)) { // 2 semestre
			filtroDescricao += "2º semestre de " + getAno();
			
			for (int i = 1; i < 7; i++) {
				parameters.put("mes" + i, i+6);
			}
			parameters.put("nomeMes1", "Julho");
			parameters.put("nomeMes2", "Agosto");
			parameters.put("nomeMes3", "Setembro");
			parameters.put("nomeMes4", "Outubro");
			parameters.put("nomeMes5", "Novembro");
			parameters.put("nomeMes6", "Dezembro");
		}
		
		if (getFlagTodasLojas() == null || getFlagTodasLojas().equals("S")) {
			filtroDescricao += "\nTodas as lojas";
			parameters.put("CodigoEmpresa", null);
			parameters.put("CodigoLoja", null);
		} else {
			filtroDescricao += "\n" + getLojasessao().getNomeLoja();
			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		}
		
		parameters.put("filtroDescricao", filtroDescricao);
		
		super.doParameter(parameters);
	}

	public Integer getAno() {
		return Ano;
	}

	public void setAno(Integer ano) {
		Ano = ano;
	}

	public Integer getSemestre() {
		return Semestre;
	}

	public void setSemestre(Integer semestre) {
		Semestre = semestre;
	}

	public String getFlagTodasLojas() {
		return FlagTodasLojas;
	}

	public void setFlagTodasLojas(String flagTodasLojas) {
		FlagTodasLojas = flagTodasLojas;
	}
}