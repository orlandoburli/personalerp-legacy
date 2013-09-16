package br.com.orlandoburli.personalerp.facades.rh.valefuncionario;

import java.sql.Date;

import br.com.orlandoburli.core.dao.rh.ValeFuncionarioDAO;
import br.com.orlandoburli.core.vo.rh.ValeFuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ValeFuncionarioConsultaFacade extends BaseConsultaFlexFacade<ValeFuncionarioVO, ValeFuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	
	@Override
	protected void doBeforeFilter() {
		setPageSize(7);
		getFilter().setNomeFuncionario(getFiltro() + "%");
		StringBuilder sb = new StringBuilder();
		if (getDataInicial() != null) {
			sb.append(" DataValeFuncionario >= '" + getDataInicial().toString() + "' ");
		}
		if (getDataFinal() != null) {
			sb.append(sb.length()==0?"":" AND ");
			sb.append(" DataValeFuncionario <= '" + getDataFinal().toString() + "' ");
		}
		getDao().setSpecialCondition(sb.toString());
		
		setOrderFields("DataValeFuncionario DESC, CodigoValeFuncionario DESC");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ValeFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ValeFuncionarioVO.class;
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
}