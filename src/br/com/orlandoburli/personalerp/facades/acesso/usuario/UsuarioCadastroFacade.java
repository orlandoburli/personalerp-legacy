package br.com.orlandoburli.personalerp.facades.acesso.usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.UsuarioDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.dao.acesso.PerfilDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class UsuarioCadastroFacade extends BaseCadastroFlexFacade<UsuarioVO, UsuarioDAO> {

	private static final long serialVersionUID = 1L;

	public UsuarioCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoEmpresa"));
		addNewValidator(new NotEmptyValidator("CodigoLoja"));
		addNewValidator(new NotEmptyValidator("NomeUsuario", "Nome do Usuário"));
		addNewValidator(new NotEmptyValidator("LoginUsuario", "Login do Usuário"));
		addNewValidator(new NotEmptyValidator("SenhaUsuario", "Senha"));
	}
	
	// Retorna todas as listas em um xml
	@IgnoreMethodAuthentication
	public void list() {
		List<Object> list = new ArrayList<Object>();
		
		List<LojaVO> listLoj;
		try {
			listLoj = new LojaDAO().getListLojasUsuarioSelecionadas(this.getVo());
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			return;
		}
		List<PerfilVO> listPer = null;
		try {
			listPer = new PerfilDAO().getListPerfisUsuarioSelecionado(this.getVo());
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		list.addAll(listLoj);
		list.addAll(listPer);
		
		write(Utils.voToXml(list));
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getSuperUsuario() == null) {
			getVo().setSuperUsuario(false);
		}
		return super.doBeforeSave();
	}
	
	@Override
	public Class<?> getDAOClass() {
		return UsuarioDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return UsuarioVO.class;
	}
}