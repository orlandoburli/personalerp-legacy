package br.com.orlandoburli.personalerp.facades.estoque.troca.trocamercadoria;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.troca.TrocaMercadoriaDAO;
import br.com.orlandoburli.core.vo.estoque.troca.TrocaMercadoriaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TrocaMercadoriaConsultaFacade extends BaseConsultaFlexFacade<TrocaMercadoriaVO, TrocaMercadoriaDAO>{

	private static final long serialVersionUID = 1L;
	
	private Date DataInicial;
	private Date DataFinal;
	private String StatusTroca;

	@Override
	protected void doBeforeFilter() {
		setPageSize(9);
		String sb = "";
		
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		getFilter().setNomeVendedor(getFiltro() + "%");
		
		if (DataInicial != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraTroca >= '" + DataInicial.toString() + " 00:00:00' ";
		}
		
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraTroca <= '" + DataFinal.toString() + " 23:59:59' ";
		}
		
		if (getStatusTroca() != null && !getStatusTroca().equals("T")) {
			getFilter().setStatusTroca(getStatusTroca());
		}
		
		if (getOrderFields() == null || getOrderFields().trim().equals("")) {
			setOrderFields("DataHoraTroca DESC");
		}
		
		if (sb.trim().length() > 0) {
			dao.setSpecialCondition(sb);
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

	public void setStatusTroca(String statusTroca) {
		StatusTroca = statusTroca;
	}

	public String getStatusTroca() {
		return StatusTroca;
	}
}