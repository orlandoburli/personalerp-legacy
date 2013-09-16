package br.com.orlandoburli.personalerp.facades.acesso.perfil;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.acesso.PerfilDAO;
import br.com.orlandoburli.core.dao.base.MenuDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PerfilCadastroFacade extends BaseCadastroFlexFacade<PerfilVO, PerfilDAO>{

	private static final long serialVersionUID = 1L;
	private LojaVO lojasessao;
	private Integer CodigoMenuPesquisa;
	
	public PerfilCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("CodigoMenu", "Menu"));
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		// Busca Permissoes de Acao de Objeto
		PermissaoPerfilAcaoObjetoVO permAcaoFilter = new PermissaoPerfilAcaoObjetoVO();
		permAcaoFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		permAcaoFilter.setCodigoLoja(getVo().getCodigoLoja());
		permAcaoFilter.setCodigoPerfil(getVo().getCodigoPerfil());
		
		List<IValueObject> listAcaoObj = getGenericDao().getList(permAcaoFilter);
		
		for (IValueObject i : listAcaoObj) {
			getGenericDao().remove(i);
		}
		
		// Busca Permissoes de Objeto
		PermissaoPerfilObjetoVO permObjFilter = new PermissaoPerfilObjetoVO();
		permObjFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		permObjFilter.setCodigoLoja(getVo().getCodigoLoja());
		permObjFilter.setCodigoPerfil(getVo().getCodigoPerfil());
		
		List<IValueObject> listPermObj = getGenericDao().getList(permObjFilter);
		
		for (IValueObject i : listPermObj) {
			getGenericDao().remove(i);
		}
		
		return super.doBeforeDelete();
	}
	
	@IgnoreMethodAuthentication
	public void menu() {
		if (CodigoMenuPesquisa != null) {
			MenuVO menu = null;
			try {
				menu = new MenuDAO().get(CodigoMenuPesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (menu != null) {
				write(Utils.voToXml(menu));
			}
		}
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoEmpresa(lojasessao.getCodigoEmpresa());
		this.getVo().setCodigoLoja(lojasessao.getCodigoLoja());
		return super.doBeforeInsert();
	}
	
	@Override
	public Class<?> getDAOClass() {
		return PerfilDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return PerfilVO.class;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public void setCodigoMenuPesquisa(Integer codigoMenuPesquisa) {
		CodigoMenuPesquisa = codigoMenuPesquisa;
	}

	public Integer getCodigoMenuPesquisa() {
		return CodigoMenuPesquisa;
	}
}