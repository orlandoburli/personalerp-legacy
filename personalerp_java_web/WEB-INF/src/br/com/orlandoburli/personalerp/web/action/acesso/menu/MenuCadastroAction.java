package br.com.orlandoburli.personalerp.web.action.acesso.menu;

import java.util.List;

import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.model.acesso.menu.be.MenuBe;
import br.com.orlandoburli.personalerp.model.acesso.menu.dao.MenuDAO;
import br.com.orlandoburli.personalerp.model.acesso.menu.vo.MenuVO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.be.ObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;

public class MenuCadastroAction extends BaseCadastroAction<MenuVO, MenuDAO, MenuBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspCadastro() {
		return "web/pages/acesso/menu/menucadastro.jsp";
	}
	
	@IgnoreMethodAuthentication
	public void objeto() {
		ObjetoVO filter = new ObjetoVO();
		ObjetoBe objetoBe = new ObjetoBe();
		filter.setNomeObjeto("%" + getTerm() + "%");
		
		try {
			List<ObjetoVO> list = objetoBe.getList(filter, "NomeObjeto");
			
			write(Utils.voToJson(list));
		} catch (ListException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void menupai() {
		MenuVO filter = new MenuVO();
		MenuBe menuBe = new MenuBe();
		
		filter.setNomeMenu("%" + getTerm() + "%");
		
		try {
			List<MenuVO> list = menuBe.getList(filter, "NomeMenu");
			
			write(Utils.voToJson(list));
		} catch (ListException e) {
			e.printStackTrace();
		}
	}
}