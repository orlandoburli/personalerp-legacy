package br.com.orlandoburli.personalerp.facades.acesso.objeto;


import br.com.orlandoburli.core.dao.base.ObjetoDAO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ObjetoConsultaFacade extends BaseConsultaFlexFacade<ObjetoVO, ObjetoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoObjeto("%"+getFiltro()+"%");
		this.setOrderFields("DescricaoObjeto");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ObjetoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ObjetoVO.class;
	}
}