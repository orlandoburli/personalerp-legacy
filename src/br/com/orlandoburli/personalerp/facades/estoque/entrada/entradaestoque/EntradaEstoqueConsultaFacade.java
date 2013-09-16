package br.com.orlandoburli.personalerp.facades.estoque.entrada.entradaestoque;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.entradaestoque.EntradaEstoqueDAO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.EntradaEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EntradaEstoqueConsultaFacade extends BaseConsultaFlexFacade<EntradaEstoqueVO, EntradaEstoqueDAO>{

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	private String StatusEntrada;
	
	@Override
	protected void doBeforeFilter() {
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		String sb = "";
		
		if (DataInicial != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraEntradaEstoque >= '" + DataInicial.toString() + " 00:00:00' ";
		}
		
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraEntradaEstoque <= '" + DataFinal.toString() + " 23:59:59' ";
		}
		
		if (getStatusEntrada() != null && !getStatusEntrada().equals("T")) {
			getFilter().setStatusEntradaEstoque(getStatusEntrada());
		}
		
		getFilter().setNomeFornecedor(getFiltro() + "%");
		
		if (getOrderFields() == null || getOrderFields().trim().equals("")) {
			setOrderFields("DataHoraEntradaEstoque DESC");
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

	public void setStatusEntrada(String statusEntrada) {
		StatusEntrada = statusEntrada;
	}

	public String getStatusEntrada() {
		return StatusEntrada;
	}
}