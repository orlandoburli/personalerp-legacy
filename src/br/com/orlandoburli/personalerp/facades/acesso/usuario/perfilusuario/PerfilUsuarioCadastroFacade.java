package br.com.orlandoburli.personalerp.facades.acesso.usuario.perfilusuario;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.PerfilUsuarioDAO;
import br.com.orlandoburli.core.vo.acesso.PerfilUsuarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class PerfilUsuarioCadastroFacade extends BaseCadastroFlexFacade<PerfilUsuarioVO, PerfilUsuarioDAO> {

	private static final long	serialVersionUID	= 1L;

	@IgnoreMethodAuthentication
	public void execute() {
		try {
			if (dao.getList(getVo()).size() == 0) {
				inserir();
			} else {
				excluir();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PerfilUsuarioCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	public Class<?> getDAOClass() {
		return PerfilUsuarioDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return PerfilUsuarioVO.class;
	}
}