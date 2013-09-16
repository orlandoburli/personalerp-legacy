package br.com.orlandoburli.personalerp.facades.acesso.usuario.usuarioloja;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.UsuarioLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class UsuarioLojaCadastroFacade extends BaseCadastroFlexFacade<UsuarioLojaVO, UsuarioLojaDAO> {

	private static final long	serialVersionUID	= 1L;

	@IgnoreMethodAuthentication
	public void execute() {
		try {
			System.out.println(Utils.voToXml(getVo()));
			if (dao.getList(getVo()).size() == 0) {
				inserir();
			} else {
				excluir();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UsuarioLojaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	public Class<?> getDAOClass() {
		return UsuarioLojaDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return UsuarioLojaVO.class;
	}
}