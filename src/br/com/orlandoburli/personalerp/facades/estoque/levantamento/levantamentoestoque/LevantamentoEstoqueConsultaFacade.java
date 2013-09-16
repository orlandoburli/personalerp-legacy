package br.com.orlandoburli.personalerp.facades.estoque.levantamento.levantamentoestoque;

import java.sql.Date;

import br.com.orlandoburli.core.dao.estoque.levantamento.LevantamentoEstoqueDAO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class LevantamentoEstoqueConsultaFacade extends BaseConsultaFlexFacade<LevantamentoEstoqueVO, LevantamentoEstoqueDAO> {

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	private String StatusEntrada;
	
	@Override
	protected void doBeforeFilter() {
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		getFilter().setNomeUsuarioLevantamentoEstoque(getFiltro() + "%");
		
		String sb = "";
		
		if (DataInicial != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraInicioLevantamentoEstoque >= '" + DataInicial.toString() + " 00:00:00' ";
		}
		
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataHoraInicioLevantamentoEstoque <= '" + DataFinal.toString() + " 23:59:59' ";
		}
		
		if (getStatusEntrada() != null && !getStatusEntrada().equals("T")) {
			getFilter().setStatusLevantamentoEstoque(getStatusEntrada());
		}
		
		if (getOrderFields() == null || getOrderFields().trim().equals("")) {
			setOrderFields("DataHoraInicioLevantamentoEstoque DESC");
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