package br.com.orlandoburli.personalerp.facades.acesso.perfil.permissao;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilAcaoObjetoDAO;
import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilObjetoDAO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.web.framework.facades.*;

public class PermissaoPerfilObjetoCadastroFacade extends BaseCadastroFlexFacade<PermissaoPerfilObjetoVO, PermissaoPerfilObjetoDAO>{

	private static final long serialVersionUID = 1L;
	
	public PermissaoPerfilObjetoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
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
	public boolean doBeforeDelete() throws SQLException {
		// Exclui as permissões de ação
		PermissaoPerfilAcaoObjetoVO filter = new PermissaoPerfilAcaoObjetoVO();
		filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filter.setCodigoLoja(getVo().getCodigoLoja());
		filter.setCodigoPerfil(getVo().getCodigoPerfil());
		filter.setCodigoObjeto(getVo().getCodigoObjeto());
		PermissaoPerfilAcaoObjetoDAO dao2 = new PermissaoPerfilAcaoObjetoDAO();
		
		try {
			List<PermissaoPerfilAcaoObjetoVO> list = dao2.getList(filter);
			for (PermissaoPerfilAcaoObjetoVO item : list) {
				dao2.remove(item);
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			return false;
		}
		
		return super.doBeforeDelete();
	}
	
	@Override
	public Class<?> getDAOClass() {
		return PermissaoPerfilObjetoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return PermissaoPerfilObjetoVO.class;
	}
}