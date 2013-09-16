package br.com.orlandoburli.personalerp.facades.acesso.perfil;


import br.com.orlandoburli.core.dao.acesso.PerfilDAO;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PerfilConsultaFacade extends BaseConsultaFlexFacade<PerfilVO, PerfilDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomePerfil("%" + getFiltro() + "%");
		setOrderFields("NomePerfil");
	}

	@Override
	protected Class<?> getDAOClass() {
		return PerfilDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PerfilVO.class;
	}
}