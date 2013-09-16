package br.com.orlandoburli.personalerp.facades.acesso.menu;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.core.dao.base.MenuDAO;
import br.com.orlandoburli.core.dao.base.ObjetoDAO;

public class MenuCadastroFacade extends BaseCadastroFlexFacade<MenuVO, MenuDAO> {

	private static final long serialVersionUID = 1L;

	private Integer CodigoMenuPaiPesquisa;
	private Integer CodigoObjetoPesquisa;
	
	public MenuCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		// Adiciona validadores
		this.addNewValidator(new NotEmptyValidator("NomeMenu", "Nome do Menu"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getCodigoMenu() != null && getVo().getCodigoMenu() == getVo().getCodigoMenuPai()) {
			this.messages.add(new MessageVO("Código do Menu Pai não pode ser ele mesmo!", "CodigoMenuPai"));
			return false;
		}
		return super.doBeforeSave();
	}
	
	@IgnoreMethodAuthentication
	public void menupai() {
		if (CodigoMenuPaiPesquisa != null) {
			MenuVO menupai = null;
			try {
				menupai = dao.get(CodigoMenuPaiPesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (menupai != null) {
				write(Utils.voToXml(menupai));
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void objeto() {
		if (CodigoObjetoPesquisa != null) {
			ObjetoVO objeto = null;
			try {
				objeto = new ObjetoDAO().get(CodigoObjetoPesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (objeto != null) {
				write(Utils.voToXml(objeto));
			}
		}
	}
	
	@Override
	public Class<?> getDAOClass() {
		return MenuDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return MenuVO.class;
	}

	public void setCodigoMenuPaiPesquisa(Integer codigoMenuPaiPesquisa) {
		CodigoMenuPaiPesquisa = codigoMenuPaiPesquisa;
	}

	public Integer getCodigoMenuPaiPesquisa() {
		return CodigoMenuPaiPesquisa;
	}

	public void setCodigoObjetoPesquisa(Integer codigoObjetoPesquisa) {
		CodigoObjetoPesquisa = codigoObjetoPesquisa;
	}

	public Integer getCodigoObjetoPesquisa() {
		return CodigoObjetoPesquisa;
	}
}