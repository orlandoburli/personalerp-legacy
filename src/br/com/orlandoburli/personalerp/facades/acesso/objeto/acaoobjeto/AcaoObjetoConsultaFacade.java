package br.com.orlandoburli.personalerp.facades.acesso.objeto.acaoobjeto;


import java.sql.SQLException;

import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.vo.base.AcaoObjetoVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;
import br.com.orlandoburli.core.dao.base.AcaoObjetoDAO;
import br.com.orlandoburli.core.dao.base.ObjetoDAO;


public class AcaoObjetoConsultaFacade extends BaseConsultaFlexFacade<AcaoObjetoVO, AcaoObjetoDAO>{

	private static final long serialVersionUID = 1L;
	private ObjetoVO objeto;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeAcaoObjeto("%" + getFiltro() + "%");
		try {
			this.objeto = new ObjetoDAO().get(getFilter().getCodigoObjeto());
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return AcaoObjetoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return AcaoObjetoVO.class;
	}



	public void setObjeto(ObjetoVO objeto) {
		this.objeto = objeto;
	}



	public ObjetoVO getObjeto() {
		return objeto;
	}
}