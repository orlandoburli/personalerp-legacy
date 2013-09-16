package br.com.orlandoburli.personalerp.facades.bi.vendadiaria;

import java.sql.Date;

import br.com.orlandoburli.core.dao.bi.vendas.VendaDiariaDAO;
import br.com.orlandoburli.core.vo.bi.vendas.VendaDiariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class VendaDiariaConsultaFacade extends BaseConsultaFlexFacade<VendaDiariaVO, VendaDiariaDAO>{

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	private String FlagTodasLojas;
	
	@Override
	protected void doBeforeFilter() {
		
		String sb = "";
		if (getDataInicial() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataVenda >= '" + getDataInicial().toString() + " 00:00:00' ";
		}
		
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataVenda <= '" + DataFinal.toString() + " 23:59:59' ";
		}
		
		if (getFlagTodasLojas() != null && getFlagTodasLojas().equals("S")) {
			getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
			getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		}
		
		getDao().setSpecialCondition(sb);
		
		setOrderFields("DataVenda DESC");
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public String getFlagTodasLojas() {
		return FlagTodasLojas;
	}

	public void setFlagTodasLojas(String flagTodasLojas) {
		FlagTodasLojas = flagTodasLojas;
	}
}