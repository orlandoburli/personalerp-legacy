package br.com.orlandoburli.personalerp.facades.acesso.usuario;

import br.com.orlandoburli.core.dao.acesso.UsuarioDAO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class UsuarioConsultaFacade extends BaseConsultaFlexFacade<UsuarioVO, UsuarioDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setPageSize(8);
		this.getFilter().setNomeUsuario("%" + getFiltro() + "%");
		this.setOrderFields("NomeUsuario");
	}

	@Override
	protected Class<?> getDAOClass() {
		return UsuarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return UsuarioVO.class;
	}

}