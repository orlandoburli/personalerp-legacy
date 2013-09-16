package br.com.orlandoburli.personalerp.facades.acesso.perfil.permissao.acao;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilAcaoObjetoDAO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public  class PermissaoPerfilAcaoObjetoCadastroFacade extends BaseCadastroFlexFacade<PermissaoPerfilAcaoObjetoVO, PermissaoPerfilAcaoObjetoDAO> {

	private static final long serialVersionUID = 1L;
	
	public PermissaoPerfilAcaoObjetoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	public void execute() {
		try {
			if (dao.getList(getVo()).size() == 0) {
				inserir();
			} else {
				excluir();
			}
		} catch (SQLException ex) {
			writeErrorMessage(ex.getMessage());
		}
	}
	
	
	@Override
	public Class<?> getDAOClass() {
		return PermissaoPerfilAcaoObjetoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return PermissaoPerfilAcaoObjetoVO.class;
	}
}